/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.stock.StockEntry;
import uk.co.atgsoft.caveman.wine.record.stock.StockEntryImpl;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecord;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecordImpl;

/**
 *
 * @author adam.gillmore
 */
public class StockDaoImpl implements StockDao {

    private final String mDatabaseName;
    
    public StockDaoImpl(final String databaseName) {
        mDatabaseName = databaseName;
    }
    
    @Override
    public StockRecord getStockRecord(final Wine wine) {
        final Map<BottleSize, AdditionSummary> allAdditions = getAdditions(wine);
        final Map<BottleSize, DepletionSummary> allDepletions = getDepletions(wine);
        return calculateStock(allAdditions, allDepletions);
    }
    
    private Map<BottleSize, AdditionSummary> getAdditions(final Wine wine) {
        Connection c = null;
        Statement stmt = null;
        StockRecord stock = null;
        Map<BottleSize, AdditionSummary> allAdditions = new HashMap<>();
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
            stmt = c.createStatement();
            final ResultSet rs = stmt.executeQuery(
                "SELECT PURCHASE.WINE_ID, NAME, PRODUCER, VINTAGE, REGION, COUNTRY, COLOUR, STYLE, GRAPE, PURCHASE.SIZE " 
                    + ", avg(PURCHASE.PRICE) as AVG_PRICE, sum(PURCHASE.QUANTITY) as ADDED " 
                    + "FROM PURCHASE " 
                    + "JOIN WINE ON PURCHASE.WINE_ID = WINE.ID " 
                    + "WHERE PURCHASE.WINE_ID = '" + wine.getId() + "' " 
                    + "GROUP BY PURCHASE.SIZE;" );
          
            while (rs.next()) {
                if (stock == null) {
                    
                    final AdditionSummary additions = new AdditionSummary();
                    additions.mWine = wine;
                    additions.mAvgPrice = new BigDecimal(rs.getFloat("avg_price"));
                    additions.mQuantity = rs.getInt("added");
                    allAdditions.put(BottleSize.valueOf(rs.getString("size")), additions);
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return allAdditions;
    }
    
    private Map<BottleSize, DepletionSummary> getDepletions(final Wine wine) {
        final Map<BottleSize, DepletionSummary> allDepletions = new HashMap<>();
        return allDepletions;
    }
    
    private StockRecord calculateStock(final Map<BottleSize, AdditionSummary> allAdditions, 
            final Map<BottleSize, DepletionSummary> allDepletions) {
        final StockRecord record = new StockRecordImpl(allAdditions.get(BottleSize.STANDARD).mWine);
        for (Entry<BottleSize, AdditionSummary> entry : allAdditions.entrySet()) {
            
            final BottleSize size = entry.getKey();
            final int added = entry.getValue().mQuantity;
            final DepletionSummary depletions = allDepletions.get(size);
            final int remaining = added - depletions.mQuantity;
            final StockEntry stockEntry = new StockEntryImpl(entry.getValue().mWine, size, remaining, 
                    entry.getValue().mAvgPrice, depletions.mAvgRating);
            record.addStockEntry(stockEntry);
        }
        
        return record;
    }

    

//    @Override
//    public List<StockRecord> getAllStockRecords() {
//        final List<StockRecord> records = new ArrayList<>();
//        Connection c = null;
//        Statement stmt = null;
//        
//        try {
//            c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery( "SELECT WINE_ID, NAME, PRODUCER, REGION, COUNTRY, VINTAGE, ALCOHOL, "
//                    + "COLOUR, WINE.PRICE, STYLE, GRAPE, SIZE, QUANTITY, SUM(QUANTITY) AS TOTAL "
//                    + "FROM PURCHASE JOIN WINE ON PURCHASE.WINE_ID = WINE.ID GROUP BY WINE_ID;" );
//            String lastWineId = null;
//            StockRecord stock = null;
//            while (rs.next()) {
//                final Wine wine = DatabaseUtils.createWine(rs);
//                if (!wine.getId().equals(lastWineId)) {
//                    stock = new StockRecordImpl(wine);
//                    records.add(stock);
//                }
//                stock.addStock(BottleSize.valueOf(rs.getString("size")), rs.getInt("total"));
//            }
//            rs.close();
//            stmt.close();
//            c.close();
//        }   catch ( Exception e ) {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//        System.out.println("All stock retrieved successfully");
//        return records;
//    }
    
    
//    private void nearlyThere(final Wine wine) {
//        Connection c = null;
//        Statement stmt = null;
//        StockRecord stock = null;
//        try {
//          c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
//
//          stmt = c.createStatement();
//        final ResultSet rs = stmt.executeQuery(
//                "SELECT PURCHASE.WINE_ID, NAME, PRODUCER, VINTAGE, REGION, COUNTRY, COLOUR, STYLE, GRAPE, PURCHASE.SIZE, DEPLETION.SIZE " 
//                        + ", avg(PURCHASE.PRICE) as AVG_PRICE, sum(PURCHASE.QUANTITY) as ADDED " 
//                        + ", sum(DEPLETION.QUANTITY) as DEPLETED, avg(DEPLETION.RATING) as AVG_RATING " 
//                        + "FROM PURCHASE " 
//                        + "JOIN WINE ON PURCHASE.WINE_ID = WINE.ID " 
//                        + "JOIN DEPLETION ON PURCHASE.WINE_ID = DEPLETION.WINE_ID " 
//                        
//                        + "WHERE PURCHASE.WINE_ID = '" + wine.getId() + "' " 
//                        + "GROUP BY PURCHASE.SIZE;" );
//        rs.close();
//          stmt.close();
//          c.close();
//        } catch ( Exception e ) {
//          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//        }
//    }
    
    private class AdditionSummary {
        private Wine mWine;
        private int mQuantity;
        private BigDecimal mAvgPrice;
    }
    
    private class DepletionSummary {
        private Wine mWine;
        private int mQuantity;
        private float mAvgRating;
        private String review;
    }
}
