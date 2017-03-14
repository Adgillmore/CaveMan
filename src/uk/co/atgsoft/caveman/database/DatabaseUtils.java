/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineOriginImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;
import uk.co.atgsoft.caveman.wine.record.WineCompositionImpl;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionEntryImpl;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntryImpl;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionEntry;

/**
 *
 * @author adam
 */
public final class DatabaseUtils {
    
    private final static StringBuilder sb = new StringBuilder();
    private static final String JDBC_PREFIX = "jdbc:sqlite:";
    private static final String DB_SUFFIX = ".db";
    
    public static Connection getConnection(final String databaseName) throws SQLException {
        sb.setLength(0);
        sb.append(JDBC_PREFIX).append(databaseName).append(DB_SUFFIX);
        Connection conn = null;
        conn = DriverManager.getConnection(sb.toString());
        sb.setLength(0);
        return conn;
    }

    
    public static void createTable(final String databaseName, final String statement) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate(statement);
            stmt.close();
            c.close();
        }   catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
    }
    
    public static void executeStatement(final String databaseName, final String statement) {
        Connection c = null;
        Statement stmt = null;
        try {
          c = DriverManager.getConnection("jdbc:sqlite:" + databaseName + ".db");
          stmt = c.createStatement();
          stmt.executeUpdate(statement);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    
    public static Wine createWine(final ResultSet rs) throws SQLException {
        final Wine wine = new WineImpl(
                rs.getString("wine_id"), 
                rs.getString("name"), 
                new WineOriginImpl(rs.getString("producer"), rs.getString("region"), rs.getString("country")),
                new WineCompositionImpl(WineColour.valueOf(rs.getString("colour").toUpperCase()), 
                        WineStyle.valueOf(rs.getString("style").toUpperCase()), rs.getString("grape")),
                rs.getInt("vintage"),
                rs.getFloat("alcohol"),
                rs.getBigDecimal("price")
             );
        return wine;
    }
    
    public static PurchaseEntry createPurchaseRecord(final Wine wine, final ResultSet rs) throws SQLException {
        return new PurchaseEntryImpl(rs.getString("id"),
                wine, 
                new BigDecimal(rs.getFloat("price")), 
                rs.getInt("quantity"), 
                BottleSize.valueOf(rs.getString("size")), 
                rs.getString("vendor"), 
                LocalDate.parse(rs.getString("date"))
                );
    }
    
    public static DepletionEntry createDepletionRecord(final Wine wine, final ResultSet rs) throws SQLException {
        return new DepletionEntryImpl(rs.getString("id"), 
                wine, 
                rs.getInt("quantity"), 
                BottleSize.valueOf(rs.getString("size")), 
                LocalDate.parse(rs.getString("date")), 
                rs.getFloat("rating"), 
                rs.getString("review"));
    }

    private static void check(final String s) {
        if (s == null || s.isEmpty()) throw new IllegalArgumentException();
    }
    
    private static void check(final String... strings) {
        for (String s : strings) {
            check(s);
        }
    }
}
