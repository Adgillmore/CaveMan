/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineComposition;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineOriginImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;
import uk.co.atgsoft.caveman.wine.WineCompositionImpl;

/**
 *
 * @author adam.gillmore
 */
public class WineDaoImplTest {
    
    private static final String SUFFIX = ".db";
    
    private static final String DB_NAME = "test_data";
    
    
    private Wine wine1;
    
    private Wine wine2;
    
    private Wine wine3;
    
    @Before
    public void setup() {
        final WineOriginImpl origin1 = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition1 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        wine1 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final WineOriginImpl origin2 = new WineOriginImpl("Ch. Margaux", "Bordeaux, Margaux", "France");
        final WineComposition composition2 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Cabernet Sauvignon");
        wine2 = new WineImpl("some_id2", "Very Grand cru", origin2, composition2, 1996, 13.5F, new BigDecimal(146.10));
        
        wine3 = new WineImpl("some_id3", "Very Grand cru", origin2, composition2, 1985, 13.0F, new BigDecimal(290.10));
        cleanUpDb();
    }
    
    @After
    public void teardown() {
        cleanUpDb();
    }
    
    @Test
    public void insertGetWine() {
        final WineDao dao = new WineDaoImpl(DB_NAME);
        dao.insertWine(wine1);
        final Wine actual = dao.getAllWines().get(0);
        assertEquals(wine1, actual);
    }
    
    @Test
    public void insertGetWines() {
        final WineDao dao = new WineDaoImpl(DB_NAME);
        dao.insertWine(wine1);
        dao.insertWine(wine2);
        dao.insertWine(wine3);
        
        final List<Wine> wines = dao.getAllWines();
        assertNotNull(wines);
        assertEquals(3, wines.size());
        assertTrue(wines.contains(wine1));
        assertTrue(wines.contains(wine2));
        assertTrue(wines.contains(wine3));
    }
    
    
    private void cleanUpDb() {
        final File f = new File(DB_NAME + SUFFIX);
        if (f.exists()) f.delete();
    }
}
