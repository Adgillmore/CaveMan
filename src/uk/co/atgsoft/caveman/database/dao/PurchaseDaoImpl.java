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
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecord;

/**
 *
 * @author adam.gillmore
 */
public class PurchaseDaoImpl implements PurchaseDao {

    public PurchaseDaoImpl() {
        DatabaseUtils.createTable("CREATE TABLE IF NOT EXISTS PURCHASE " +
           "(ID TEXT PRIMARY KEY," +
           " WINE_ID TEXT, " +
           " PRICE INTEGER    NOT NULL, " + 
           " DATE TEXT    NOT NULL, " +
           " VENDOR TEXT NOT NULL, " +
           " QUANTITY INTEGER, " + 
           " SIZE TEXT, " + 
           " FOREIGN KEY(WINE_ID) REFERENCES WINE(ID))");
    }

    
    
    @Override
    public void addPurchase(PurchaseRecord purchase) {
        DatabaseUtils.executeStatement(
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
    public void removePurchase(PurchaseRecord purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePurchase(PurchaseRecord purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PurchaseRecord> getAllPurchases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PurchaseRecord> getPurchases(final Wine wine) {
        final List<PurchaseRecord> records = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        
        try {
          c = DriverManager.getConnection("jdbc:sqlite:test.db");

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
