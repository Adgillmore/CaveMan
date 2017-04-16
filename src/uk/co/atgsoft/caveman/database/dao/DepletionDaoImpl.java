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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.atgsoft.caveman.database.DatabaseUtils;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionEntry;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionSummary;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionSummaryImpl;

/**
 *
 * @author adam.gillmore
 */
public class DepletionDaoImpl implements DepletionDao {

    private final String mDatabaseName;

    public DepletionDaoImpl(final String databaseName) {
        if (databaseName == null || databaseName.isEmpty()) throw new IllegalArgumentException(
                "Database name cannot be null or empty");
        mDatabaseName = databaseName;
        DatabaseUtils.createTable(databaseName, "CREATE TABLE IF NOT EXISTS DEPLETION " +
           "(ID TEXT PRIMARY KEY," +
           " WINE_ID TEXT, " +
           " DATE TEXT NOT NULL, " +
           " QUANTITY INTEGER NOT NULL, " + 
           " SIZE TEXT NOT NULL, " + 
           " FOREIGN KEY(WINE_ID) REFERENCES WINE(ID))");
    }
    
    @Override
    public void addDepletion(final DepletionEntry depletion) {
        try {
            Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO DEPLETION (ID, WINE_ID, DATE, QUANTITY, SIZE) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, depletion.getId());
            ps.setString(2, depletion.getWine().getId());
            ps.setString(3, depletion.getDate().format(DateTimeFormatter.ISO_DATE));
            ps.setInt(4, depletion.getQuantity());
            ps.setString(5, depletion.getBottleSize().toString());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    @Override
    public List<DepletionEntry> getDepletions(Wine wine) {
        final List<DepletionEntry> records = new ArrayList<>();
        try {
            Connection c = DatabaseUtils.getConnection(mDatabaseName);
            PreparedStatement ps = c.prepareStatement("SELECT * FROM DEPLETION WHERE WINE_ID = ?;");
            ps.setString(1, wine.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                records.add(DatabaseUtils.createDepletionRecord(wine, rs));
            }
            rs.close();
            ps.close();
            c.close();
        } catch (SQLException e) {
            Logger.getLogger(DepletionDaoImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return records;
    }
    
    @Override
    public Map<BottleSize, DepletionSummary> getDepletionSummary(Wine wine) {
        final Map<BottleSize, DepletionSummary> allDepletions = new HashMap<>();
        
        try {
            final Connection conn = DatabaseUtils.getConnection(mDatabaseName);
            final PreparedStatement ps = conn.prepareStatement(
                    "SELECT DEPLETION.WINE_ID, NAME, PRODUCER, VINTAGE, REGION, COUNTRY, COLOUR, STYLE, GRAPE, DEPLETION.SIZE " 
                    + ", sum(DEPLETION.QUANTITY) as REMOVED " 
                    + "FROM DEPLETION " 
                    + "JOIN WINE ON DEPLETION.WINE_ID = WINE.ID " 
                    + "WHERE DEPLETION.WINE_ID = ? " 
                    + "GROUP BY DEPLETION.SIZE;");
            ps.setString(1, wine.getId());
            final ResultSet rs = ps.executeQuery();
          
            while (rs.next()) {
                final BottleSize size = BottleSize.valueOf(rs.getString("size"));
                final DepletionSummary depletions = new DepletionSummaryImpl(
                        wine, rs.getInt("removed"), size);
                allDepletions.put(size, depletions);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return allDepletions;
    }
    
    @Override
    public void removeDepletion(DepletionEntry depletion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDepletion(DepletionEntry depletion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
