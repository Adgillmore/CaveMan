/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;

/**
 *
 * @author adam.gillmore
 */
public class WineDaoImpl implements WineDao {

    private final String mDatabaseName;
    
    public WineDaoImpl(final String databaseName) {
        mDatabaseName = databaseName;
        DatabaseUtils.createTable(mDatabaseName, "CREATE TABLE IF NOT EXISTS WINE " +
           "(ID TEXT PRIMARY KEY," +
           " NAME           TEXT    NOT NULL, " + 
           " PRODUCER       TEXT    NOT NULL, " + 
           " REGION       TEXT    NOT NULL, " + 
           " COUNTRY       TEXT    NOT NULL, " + 
           " VINTAGE        INT, " + 
           " ALCOHOL        REAL, " + 
           " COLOUR         TEXT, " + 
           " PRICE          BIGDECIMAL, " +
           " STYLE          TEXT, " +
           " GRAPE         TEXT)");
    }
    
    
    
    @Override
    public void insertWine(final Wine wine) {
    DatabaseUtils.executeStatement(mDatabaseName,
          "INSERT INTO WINE (ID,NAME,PRODUCER,REGION,COUNTRY,VINTAGE,ALCOHOL"
                  + ",COLOUR,PRICE,STYLE,GRAPE) " +
               "VALUES (" 
          + "'" + wine.getId() + "', "
          + "'" + wine.getName() + "', " 
          + "'" + wine.getProducer() + "', "
          + "'" + wine.getRegion() + "', "
          + "'" + wine.getCountry() + "', "
          + wine.getVintage() + ", "
          + wine.getAlcohol() + ", "
          + "'" + wine.getWineColour() + "', "
//          + wine.getPrice().floatValue() + ", "
          + "'" + wine.getStyle().toString() + "', "
          + "'" + wine.getGrape() + "');");
    System.out.println("Wine added successfully");
    }
    
    public int getId(final Wine wine) {
        Connection c = null;
        Statement stmt = null;
        int id = 0;
        try {
          c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery(
                  "SELECT ID FROM WINE WHERE NAME='" 
                  + wine.getName() + "' AND PRODUCER='"
                  + wine.getProducer() + "' AND VINTAGE="
                  + wine.getVintage() + ";");
          rs.next();
          id = rs.getInt("ID");
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
        return id;
    }
    
    @Override
    public List<Wine> getAllWines() {
        final List<Wine> wines = new ArrayList<>();
        
        Connection c = null;
        Statement stmt = null;
        try {
          c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM WINE;" );
          while ( rs.next() ) {
             final Wine wine = new WineImpl(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("producer"),
                rs.getString("region"),
                rs.getString("country"),
                rs.getInt("vintage"),
                rs.getFloat("alcohol"),
                rs.getBigDecimal("price"),
                WineColour.valueOf(rs.getString("colour").toUpperCase()),
                WineStyle.valueOf(rs.getString("style").toUpperCase()),
                rs.getString("grape")
             );
             wines.add(wine);
             
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
  
        
        return wines;
    }

    @Override
    public void removeWine(final Wine wine) {
        DatabaseUtils.executeStatement(mDatabaseName, 
           "DELETE FROM WINE WHERE NAME = '" + wine.getName() + "';");
        System.out.println("Wine deleted successfully");
    }

    @Override
    public void updateWine(final Wine wine) {
        DatabaseUtils.executeStatement(mDatabaseName,
            "UPDATE WINE SET "
                    + "NAME = '" + wine.getName() + "', " 
          + "PRODUCER = '" + wine.getProducer() + "', "
          + "REGION = '" + wine.getRegion() + "', "
          + "COUNTRY = '" + wine.getCountry() + "', "
          + "VINTAGE = " + wine.getVintage() + ", "
          + "ALCOHOL = " + wine.getAlcohol() + ", "
          + "COLOUR = '" + wine.getWineColour() + "', "
          + "PRICE = " + wine.getPrice().floatValue() + ", "
          + "GRAPE = '" + wine.getGrape() + "'"
          + " WHERE ID ='" + wine.getId() + "';");
    }
    
}
