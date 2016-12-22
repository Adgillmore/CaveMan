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
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineImpl;

/**
 *
 * @author adam.gillmore
 */
public class WineDaoImpl implements WineDao {
    
    @Override
    public void insertWine(final Wine wine) {
        Connection c = null;
        Statement stmt = null;
    try {
//      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "INSERT INTO WINE (NAME,PRODUCER,VINTAGE,GRAPE) " +
                   "VALUES (" 
              + "'" + wine.getName() + "', " 
              + "'" + wine.getProducer() + "', "
              + wine.getVintage() + ", "
              + "'" + wine.getGrape() + "');"; 
      stmt.executeUpdate(sql);

      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    System.out.println("Records created successfully");
    }
    
    @Override
    public List<Wine> getAllWines() {
        final List<Wine> wines = new ArrayList<>();
        
        Connection c = null;
        Statement stmt = null;
        try {
//          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM WINE;" );
          while ( rs.next() ) {
             final Wine wine = new WineImpl(
                rs.getString("name"),
                rs.getString("producer"),
                rs.getInt("vintage"),
                rs.getString("grape"));
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
        Connection c = null;
        Statement stmt = null;
    try {
//      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:test.db");
      System.out.println("Opened database successfully");

      stmt = c.createStatement();
      String sql = "DELETE FROM WINE WHERE NAME = '" +
                   wine.getName() + "';"; 
      stmt.executeUpdate(sql);
      stmt.close();
      c.commit();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
    }
    System.out.println("Record deleted successfully");
    }
    
}
