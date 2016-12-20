/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author adam
 */
public final class DatabaseUtils {
    
    public static void createDatabase() {
        Connection c = null;
        Statement stmt = null;
        try {
//          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          String sql = "CREATE TABLE IF NOT EXISTS WINE " +
                       "(ID INTEGER PRIMARY KEY     AUTOINCREMENT," +
                       " NAME           TEXT    NOT NULL, " + 
                       " PRODUCER       TEXT    NOT NULL, " + 
                       " VINTAGE        INT, " + 
                       " GRAPE         TEXT)"; 
          stmt.executeUpdate(sql);
          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        System.out.println("Table created successfully");
    }
    
    
}
