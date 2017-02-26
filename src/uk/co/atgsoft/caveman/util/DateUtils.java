/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.util;

import java.time.LocalDate;

/**
 *
 * @author adam.gillmore
 */
public final class DateUtils {

    /**
     * Private constructor.
     */
    private DateUtils() {
        // utility class
    }
    
    public static LocalDate getDate() {
        int x = 1;
//        byte b1 = x;
//        short b = x;
        
        return LocalDate.now();
    }
    
    public static void test() {
        String lion = "Lion";
        String tiger = "Tiger";
        String result = 700 > 338 ? lion += "bob" : tiger;
        System.out.println(lion);
        
        LocalDate date = LocalDate.MAX;
        Exception e = new Exception();
        RuntimeException re = new RuntimeException();
        if (re == e) {
            
        }
    }
    
    
    
    
    
}
