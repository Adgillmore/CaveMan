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
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
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
    public void addStock(StockRecord stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeStock(StockRecord stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStock(StockRecord stock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StockRecord getStockRecord(final Wine wine) {
        Connection c = null;
        Statement stmt = null;
        StockRecord stock = null;
        try {
          c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM PURCHASE JOIN WINE ON PURCHASE.WINE_ID = WINE.ID WHERE WINE_ID = '" + wine.getId() + "';" );
          
          while (rs.next()) {
             if (stock == null) {
//                 stock = new StockRecordImpl(DatabaseUtils.createWine(rs));
                    stock = new StockRecordImpl(wine);
             }
             stock.addStock(BottleSize.valueOf(rs.getString("size")), rs.getInt("quantity"));
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Stock retrieved successfully");
        return stock;
    }

    @Override
    public List<StockRecord> getAllStockRecords() {
        final List<StockRecord> records = new ArrayList<>();
        Connection c = null;
        Statement stmt = null;
        
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + mDatabaseName + ".db");
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT WINE_ID, NAME, PRODUCER, REGION, COUNTRY, VINTAGE, ALCOHOL, "
                    + "COLOUR, WINE.PRICE, STYLE, GRAPE, SIZE, QUANTITY, SUM(QUANTITY) AS TOTAL "
                    + "FROM PURCHASE JOIN WINE ON PURCHASE.WINE_ID = WINE.ID GROUP BY WINE_ID;" );
            String lastWineId = null;
            StockRecord stock = null;
            while (rs.next()) {
                final Wine wine = DatabaseUtils.createWine(rs);
                if (!wine.getId().equals(lastWineId)) {
                    stock = new StockRecordImpl(wine);
                    records.add(stock);
                }
                stock.addStock(BottleSize.valueOf(rs.getString("size")), rs.getInt("total"));
            }
            rs.close();
            stmt.close();
            c.close();
        }   catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("All stock retrieved successfully");
        return records;
    }
    
}
