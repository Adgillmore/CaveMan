/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineComposition;
import uk.co.atgsoft.caveman.wine.WineImpl;
import uk.co.atgsoft.caveman.wine.WineOriginImpl;
import uk.co.atgsoft.caveman.wine.WineStyle;
import uk.co.atgsoft.caveman.wine.WineCompositionImpl;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntryImpl;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;

/**
 *
 * @author adam
 */
public class PurchaseRecordImplTest {
    
    private static final String BBR = "Berry Brothers and Rudd";
    
    @Test
    public void equals() {
        final WineOriginImpl origin1 = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition1 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        final Wine wine1 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final WineOriginImpl origin2 = new WineOriginImpl("Ch. Margaux", "Bordeaux, Margaux", "France");
        final WineComposition composition2 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Cabernet Sauvignon");
        final Wine wine2 = new WineImpl("some_id2", "Very Grand cru", origin2, composition2, 1996, 13.5F, new BigDecimal(146.10));
        
        final LocalDate date = LocalDate.now().minusMonths(5).minusDays(12);
        
        final PurchaseEntry record1 = new PurchaseEntryImpl("id1", wine1, new BigDecimal(14.65), 6, BottleSize.STANDARD, BBR, LocalDate.MAX);
        final PurchaseEntry record2 = new PurchaseEntryImpl("id1", wine1, new BigDecimal(14.65), 6, BottleSize.STANDARD, BBR, LocalDate.MAX);
        final PurchaseEntry record3 = new PurchaseEntryImpl("id3", wine2, new BigDecimal(25.99), 12, BottleSize.STANDARD, BBR, LocalDate.MAX);
        
        assertEquals(record1, record2);
        assertNotEquals(record1, record3);
        assertNotEquals(record1, null);
    }
}
