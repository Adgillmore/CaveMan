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
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;

/**
 *
 * @author adam.gillmore
 */
public class PurchaseDaoImpl implements PurchaseDao {

    private final String mDatabaseName;
    
    public PurchaseDaoImpl(final String databaseName) {
        if (databaseName == null || databaseName.isEmpty()) throw new IllegalArgumentException(
                "Database name cannot be null or empty");
        mDatabaseName = databaseName;
        DatabaseUtils.createTable(databaseName, "CREATE TABLE IF NOT EXISTS PURCHASE " +
           "(ID TEXT PRIMARY KEY," +
           " WINE_ID TEXT, " +
           " PRICE INTEGER    NOT NULL, " + 
           " DATE TEXT    NOT NULL, " +
           " VENDOR TEXT NOT NULL, " +
           " QUANTITY INTEGER NOT NULL, " + 
           " SIZE TEXT NOT NULL, " + 
           " FOREIGN KEY(WINE_ID) REFERENCES WINE(ID))");
    }

    
    
    @Override
    public void addPurchase(PurchaseEntry purchase) {
        DatabaseUtils.executeStatement(mDatabaseName, 
        "INSERT INTO PURCHASE (ID, WINE_ID, PRICE, DATE, QUANTITY, VENDOR, SIZE)"
                + "VALUES ("
                + "'" + purchase.getId() + "', "
                + "'" + purchase.getWine().getId() + "', "
                + "'" + purchase.getPrice() + "', "
                + "'" + purchase.getDate() + "', "
                + "'" + purchase.getQuantity() + "', "
                + "'" + purchase.getVendor() + "', "
                + "'" + purchase.getBottleSize() + "');");
    }

    @Override
    public void removePurchase(PurchaseEntry purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePurchase(PurchaseEntry purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PurchaseEntry> getPurchases(final Wine wine) {
        final List<PurchaseEntry> records = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        
        try {
          c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM PURCHASE WHERE WINE_ID = '" + wine.getId() + "';" );
          
          while (rs.next()) {
             records.add(DatabaseUtils.createPurchaseRecord(wine, rs));
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Purchases retrieved successfully");
        return records;
    }
    
   
    
}
