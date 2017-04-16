/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseSummary;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseSummaryImpl;

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
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO PURCHASE (ID, WINE_ID, PRICE, DATE, QUANTITY, VENDOR, SIZE) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, purchase.getId());
            ps.setString(2, purchase.getWine().getId());
            ps.setString(3, DatabaseUtils.PriceToString(purchase.getPrice()));
            ps.setString(4, purchase.getDate().toString());
            ps.setInt(5, purchase.getQuantity());
            ps.setString(6, purchase.getVendor());
            ps.setString(7, purchase.getBottleSize().toString());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<PurchaseEntry> getPurchases(final Wine wine) {
        final List<PurchaseEntry> records = new ArrayList<>();
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM PURCHASE WHERE WINE_ID = ?");
            ps.setString(1, wine.getId());
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                records.add(DatabaseUtils.createPurchaseRecord(wine, rs));
            }
            rs.close();
            ps.close();
            conn.close();
        } catch ( SQLException ex ) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return records;
    }
    
    @Override
    public Map<BottleSize, PurchaseSummary> getPurchaseSummary(Wine wine) {
        Map<BottleSize, PurchaseSummary> allAdditions = new HashMap<>();

        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
                    "SELECT PURCHASE.WINE_ID, NAME, PRODUCER, VINTAGE, REGION, COUNTRY, COLOUR, STYLE, GRAPE, PURCHASE.SIZE " 
                    + ", avg(PURCHASE.PRICE) as AVG_PRICE, sum(PURCHASE.QUANTITY) as ADDED " 
                    + "FROM PURCHASE " 
                    + "JOIN WINE ON PURCHASE.WINE_ID = WINE.ID " 
                    + "WHERE PURCHASE.WINE_ID = ? " 
                    + "GROUP BY PURCHASE.SIZE;");
            ps.setString(1, wine.getId());
            final ResultSet rs = ps.executeQuery();
          
            while (rs.next()) {
                final BottleSize size = BottleSize.valueOf(rs.getString("size"));
                final PurchaseSummary purchases = new PurchaseSummaryImpl(
                        wine, new BigDecimal(rs.getFloat("avg_price")), rs.getInt("added"), size);
                allAdditions.put(size, purchases);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return allAdditions;
        
    }
   
    @Override
    public void removePurchase(PurchaseEntry purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePurchase(PurchaseEntry purchase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
