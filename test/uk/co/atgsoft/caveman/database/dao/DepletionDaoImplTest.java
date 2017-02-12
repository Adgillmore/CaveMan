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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author adam.gillmore
 */
public class DepletionDaoImplTest {
    
    private static final String SUFFIX = ".db";
    
    private static final String DB_NAME = "test_db";
    
    private Wine wine;
    
    @Before
    public void setup() {
        final WineOriginImpl origin = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        wine = new WineImpl("some_id", "Grand cru", origin, composition, 2012, 14.5F, new BigDecimal(36.10));
        cleanUpDb();
    }
    
    @After
    public void teardown() {
        cleanUpDb();
    }
    
    @Test
    public void constructor() {
        final DepletionDao dao = new DepletionDaoImpl(DB_NAME);
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
        final LocalDate date = LocalDate.of(2003, Month.MARCH, 12);
        final float rating = 3.5F;
        final String review = "Good aroma. Plenty of cassis and oak on the palate with a long peppery finish.";
        final DepletionRecord record = new DepletionRecordImpl("1", wine, 1, BottleSize.STANDARD, date, rating, review);
        dao.addDepletion(record);
        
        final List<DepletionRecord> results = dao.getDepletions(wine);
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        
        final DepletionRecord actualRecord = results.get(0);
        assertEquals(actualRecord, record);
    }
    
    
    //////////// Helpers //////////////
    
    private void cleanUpDb() {
        final File f = new File(DB_NAME + SUFFIX);
        if (f.exists()) f.delete();
    }
}
