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
    
    public static void createTable(final String statement) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
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
    
    public static void executeStatement(final String statement) {
        Connection c = null;
        Statement stmt = null;
        try {
          c = DriverManager.getConnection("jdbc:sqlite:test.db");
          stmt = c.createStatement();
          stmt.executeUpdate(statement);
          stmt.close();
          c.commit();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
        
}
