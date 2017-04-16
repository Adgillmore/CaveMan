/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineCompositionImpl;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineOriginImpl;
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
        
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO WINE (ID,NAME,PRODUCER,REGION,COUNTRY,VINTAGE,ALCOHOL,COLOUR,PRICE,STYLE,GRAPE) " 
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" );
            ps.setString(1, wine.getId());
            ps.setString(2, wine.getName());
            ps.setString(3, wine.getProducer());
            ps.setString(4, wine.getRegion());
            ps.setString(5, wine.getCountry());
            ps.setInt(6, wine.getVintage());
            ps.setFloat(7, wine.getAlcohol());
            ps.setString(8, wine.getWineColour().toString());
            ps.setString(9, DatabaseUtils.PriceToString(wine.getPrice()));
            ps.setString(10, wine.getStyle().toString());
            ps.setString(11, wine.getGrape());
            
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(WineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Override
    public List<Wine> getAllWines() {
        final List<Wine> wines = new ArrayList<>();
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement("SELECT * FROM WINE;");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
             final Wine wine = new WineImpl(
                rs.getString("id"), 
                rs.getString("name"), 
                new WineOriginImpl(rs.getString("producer"), rs.getString("region"), rs.getString("country")),
                new WineCompositionImpl(WineColour.valueOf(rs.getString("colour").toUpperCase()), 
                        WineStyle.valueOf(rs.getString("style").toUpperCase()), rs.getString("grape")),
                rs.getInt("vintage"),
                rs.getFloat("alcohol"),
                rs.getBigDecimal("price")
             );
             wines.add(wine);
             
          }
          rs.close();
          ps.close();
          conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(WineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wines;
    }

    @Override
    public void removeWine(final Wine wine) {
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement("DELETE FROM WINE WHERE ID = ?;");
            ps.setString(1, wine.getId());
        } catch (SQLException ex) {
            Logger.getLogger(WineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateWine(final Wine wine) {
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
                "UPDATE WINE SET NAME = ?, " 
              + "PRODUCER = ?, "
              + "REGION = ?, "
              + "COUNTRY = ?, "
              + "VINTAGE = ?, "
              + "ALCOHOL = ?, "
              + "COLOUR = ?, "
              + "PRICE = ?, "
              + "GRAPE = ?"
              + " WHERE ID =?;");
            ps.setString(1, wine.getName());
            ps.setString(1, wine.getProducer());
            ps.setString(1, wine.getRegion());
            ps.setString(1, wine.getCountry());
            ps.setInt(1, wine.getVintage());
            ps.setFloat(1, wine.getAlcohol());
            ps.setString(1, wine.getWineColour().toString());
            ps.setString(1, DatabaseUtils.PriceToString(wine.getPrice()));
            ps.setString(1, wine.getGrape());
            ps.setString(1, wine.getStyle().toString());
            ps.setString(1, wine.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(WineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
}
