/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseRecord;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseRecordImpl;

/**
 *
 * @author adam
 */
public class PurchaseDaoImplTest {
    
    private static final String SUFFIX = ".db";
    
    private static final String DB_NAME = "test_db";
    
    private static final String BBR = "Berry Brothers and Rudd";
    
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
    
    private void cleanUpDb() {
        final File f = new File(DB_NAME + SUFFIX);
        if (f.exists()) f.delete();
    }
    
    @Test
    public void constructor() {
        new PurchaseDaoImpl(DB_NAME);
        final File f = new File(DB_NAME + SUFFIX);
        assertTrue(f.exists());
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorNullDb() {
        final PurchaseDao dao = new PurchaseDaoImpl(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void constructorEmptyDb() {
        final PurchaseDao dao = new PurchaseDaoImpl("");
    }
    
    @Test
    public void addGetPurchase() {
        final PurchaseDao dao = new PurchaseDaoImpl(DB_NAME);
        
        final LocalDate date = LocalDate.now().minusYears(2);
        final PurchaseRecord record = 
                new PurchaseRecordImpl("id1", wine1, new BigDecimal(19.99), 6, BottleSize.STANDARD, BBR, date);
        
        dao.addPurchase(record);
        
        final List<PurchaseRecord> records = dao.getPurchases(wine1);
        assertNotNull(records);
        assertEquals(1, records.size());
        assertEquals(record, records.get(0));
    }
    
    @Test
    public void addGetAllPurchases() {
        final PurchaseDao dao = new PurchaseDaoImpl(DB_NAME);
        
        final LocalDate date1 = LocalDate.now().minusYears(2);
        final LocalDate date2 = LocalDate.now().minusYears(3).minusMonths(3);
        final PurchaseRecord record1 = 
                new PurchaseRecordImpl("id1", wine1, new BigDecimal(19.99), 6, BottleSize.STANDARD, BBR, date1);
        final PurchaseRecord record2 = 
                new PurchaseRecordImpl("id2", wine1, new BigDecimal(15.99), 12, BottleSize.STANDARD, BBR, date2);
        final PurchaseRecord record3 = 
                new PurchaseRecordImpl("id3", wine2, new BigDecimal(26.99), 3, BottleSize.STANDARD, BBR, date2);
        
        dao.addPurchase(record1);
        dao.addPurchase(record2);
        dao.addPurchase(record3);
        
        final List<PurchaseRecord> records1 = dao.getPurchases(wine1);
        assertNotNull(records1);
        assertEquals(2, records1.size());
        assertTrue(records1.contains(record1));
        assertTrue(records1.contains(record2));
        
        
        final List<PurchaseRecord> records2 = dao.getPurchases(wine2);
        assertNotNull(records2);
        assertEquals(1, records2.size());
        assertTrue(records2.contains(record3));
    }
}
