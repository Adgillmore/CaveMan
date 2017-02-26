/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineComposition;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineOriginImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;
import uk.co.atgsoft.caveman.wine.record.WineCompositionImpl;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionRecord;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionRecordImpl;

/**
 *
 * @author adam.gillmore
 */
public class DepletionDaoImplTest {
    
    private static final String SUFFIX = ".db";
    
    private static final String DB_NAME = "test_db";
    
    private Wine wine1;
    
    private Wine wine2;
    
    @Before
    public void setup() {
        final WineOriginImpl origin1 = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition1 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        wine1 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final WineOriginImpl origin2 = new WineOriginImpl("Ch. Margaux", "Bordeaux, Margaux", "France");
        final WineComposition composition2 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Cabernet Sauvignon");
        wine2 = new WineImpl("some_id2", "Very Grand cru", origin2, composition2, 1996, 13.5F, new BigDecimal(146.10));
        cleanUpDb();
    }
    
    @After
    public void teardown() {
        cleanUpDb();
    }
    
    @Test
    public void constructor() {
        new DepletionDaoImpl(DB_NAME);
        final File f = new File(DB_NAME + SUFFIX);
        assertTrue(f.exists());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorNullDb() {
        final DepletionDao dao = new DepletionDaoImpl(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorEmptyDb() {
        final DepletionDao dao = new DepletionDaoImpl("");
    }
    
    @Test
    public void addGetDepletion() {
        final DepletionDao dao = new DepletionDaoImpl(DB_NAME);
        final LocalDate date1 = LocalDate.of(2003, Month.MARCH, 12);
        final float rating1 = 3.5F;
        final String review1 = "Good aroma. Plenty of cassis and oak on the palate with a long peppery finish.";
        final DepletionRecord record1 = new DepletionRecordImpl("1", wine1, 1, BottleSize.STANDARD, date1, rating1, 
                review1);
        dao.addDepletion(record1);
        
        final List<DepletionRecord> results = dao.getDepletions(wine1);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        
        final DepletionRecord actualRecord1 = results.get(0);
        assertEquals(record1, actualRecord1);
    }
    
    @Test
    public void addGetMultipleDepletions() {
        final DepletionDao dao = new DepletionDaoImpl(DB_NAME);
        final LocalDate date1 = LocalDate.of(2003, Month.MARCH, 12);
        final float rating1 = 3.5F;
        final String review1 = "Good aroma. Plenty of cassis and oak on the palate with a long peppery finish.";
        final DepletionRecord record1 = new DepletionRecordImpl("1", wine1, 1, BottleSize.STANDARD, date1, rating1, 
                review1);
        dao.addDepletion(record1);
        
        final LocalDate date2 = LocalDate.of(2005, Month.NOVEMBER, 15);
        final float rating2 = 4.5F;
        final String review2 = "Good aroma. More complex than last tasting, softer tannins and more smokiness.";
        final DepletionRecord record2 = new DepletionRecordImpl("2", wine1, 3, BottleSize.STANDARD, date2, rating2, 
                review2);
        dao.addDepletion(record2);
        
        final List<DepletionRecord> results = dao.getDepletions(wine1);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        
        final DepletionRecord actualRecord1 = results.get(0);
        assertEquals(record1, actualRecord1);
        final DepletionRecord actualRecord2 = results.get(1);
        assertEquals(record2, actualRecord2);
    }
    
    
    //////////// Helpers //////////////
    
    private void cleanUpDb() {
        final File f = new File(DB_NAME + SUFFIX);
        if (f.exists()) f.delete();
    }
}
