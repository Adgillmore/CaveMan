/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.junit.After;
import static org.junit.Assert.assertEquals;
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
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseRecord;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseRecordImpl;
import uk.co.atgsoft.caveman.wine.record.stock.StockEntry;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecord;

/**
 *
 * @author adam.gillmore
 */
public class StockDaoImplTest {
    
    private static final String SUFFIX = ".db";
    
    private static final String DB_NAME = "test_data";
    
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
        populateDatabase();
    }
    
    @After
    public void teardown() {
        cleanUpDb();
    }
    
    @Test
    public void getStock() {
        final StockDao sDao = new StockDaoImpl(DB_NAME);
        final StockRecord record = sDao.getStockRecord(wine1);
        assertEquals(wine1, record.getWine());
        assertEquals(13, record.getNumberOfBottles());
        final Map<BottleSize, StockEntry> stock = record.getStockEntries();
        assertEquals(1, stock.size());
        assertEquals(13, stock.get(BottleSize.STANDARD).getQuantity());
    }
    
    
    //////////// Helpers //////////////
    
    private void populateDatabase() {
        addWine();
        addPurchases();
        addDepletions();
    }
    
    private void addWine() {
        final WineDao wDao = new WineDaoImpl(DB_NAME);
        wDao.insertWine(wine1);
    }
    
    private void addPurchases() {
        final PurchaseDao pDao = new PurchaseDaoImpl(DB_NAME);
        final PurchaseRecord pRecord1 = new PurchaseRecordImpl("1", wine1, new BigDecimal(24.5), 6, BottleSize.STANDARD, 
                BBR, LocalDate.of(2012, 5, 12));
        final PurchaseRecord pRecord2 = new PurchaseRecordImpl("2", wine1, new BigDecimal(28.5), 12, BottleSize.STANDARD, 
                BBR, LocalDate.of(2013, 8, 24));
        pDao.addPurchase(pRecord1);
        pDao.addPurchase(pRecord2);
    }
    
    private void addDepletions() {
        final DepletionDao dDao = new DepletionDaoImpl(DB_NAME);
        final DepletionRecord dRecord1 = new DepletionRecordImpl("1", wine1, 2, BottleSize.STANDARD, 
                LocalDate.now().minusDays(2), 3.5f, "Very nice");
        final DepletionRecord dRecord2 = new DepletionRecordImpl("2", wine1, 3, BottleSize.STANDARD, 
                LocalDate.now(), 4.5f, "Really very nice");
        dDao.addDepletion(dRecord1);
        dDao.addDepletion(dRecord2);
    }
    
    private void cleanUpDb() {
        final File f = new File(DB_NAME + SUFFIX);
        if (f.exists()) f.delete();
    }
}
