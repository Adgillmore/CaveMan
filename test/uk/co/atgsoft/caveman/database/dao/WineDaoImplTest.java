/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.math.BigDecimal;
import org.junit.Test;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;

/**
 *
 * @author adam.gillmore
 */
public class WineDaoImplTest {
    
    private final String DATABASE_NAME = "junit_test"; 
    
    @Test
    public void constructor() {
        final WineDao dao = new WineDaoImpl(DATABASE_NAME);
        
        final Wine wine = new WineImpl("the_id", 
                "the_name", "the_producer", 
                "the_region", "the_country", 
                2012, 15, BigDecimal.ONE, WineColour.ROSE, 
                WineStyle.DRY, "semillon");
        
        dao.insertWine(wine);
    }
}
