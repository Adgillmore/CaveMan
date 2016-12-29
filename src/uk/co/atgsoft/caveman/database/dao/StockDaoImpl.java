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
import java.util.Map.Entry;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.stock.StockEntry;
import uk.co.atgsoft.caveman.wine.stock.StockRecord;
import uk.co.atgsoft.caveman.wine.stock.StockRecordImpl;

/**
 *
 * @author adam.gillmore
 */
public class StockDaoImpl implements StockDao {
    
    

    public StockDaoImpl() {
        DatabaseUtils.createTable("CREATE TABLE IF NOT EXISTS STOCK " +
           "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
           " WINE_ID INTEGER, " +
           " QUANTITY INTEGER, " + 
           " SIZE TEXT, " + 
           " FOREIGN KEY(WINE_ID) REFERENCES WINE(ID))");
    }
    
    @Override
    public void addStock(StockRecord stock) {
        for (Entry<BottleSize, StockEntry> entry : stock.getStock().entrySet()) {
            DatabaseUtils.executeStatement(
            "INSERT INTO STOCK (WINE_ID, QUANTITY, SIZE)"
                    + "VALUES ("
                    + "'" + stock.getWine().getId() + "', "
                    + "'" + entry.getValue().getQuantity() + "', "
                    + "'" + entry.getValue().getBottleSize() + "');");
        }
        
        
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
    public List<StockRecord> getAllStock() {
        final List<StockRecord> stockRecords = new ArrayList<>();
        
        Connection c = null;
        Statement stmt = null;
        try {
//          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM STOCK;" );
          while ( rs.next() ) {
             final StockRecord stock = new StockRecordImpl(
                (Wine) rs.getRef("wine").getObject(), 
                BottleSize.valueOf(rs.getString("size")),
                rs.getInt("quantity")
             );
             stockRecords.add(stock);
          }
          rs.close();
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Operation done successfully");
        return stockRecords;
    }
    
}
