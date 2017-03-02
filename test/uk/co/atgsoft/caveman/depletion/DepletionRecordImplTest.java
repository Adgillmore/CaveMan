/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.depletion;

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
import uk.co.atgsoft.caveman.wine.record.WineCompositionImpl;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionRecord;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionRecordImpl;

/**
 *
 * @author adam
 */
public class DepletionRecordImplTest {
    
    @Test
    public void equals() {
        final WineOriginImpl origin1 = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition1 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        final Wine wine1 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final WineOriginImpl origin2 = new WineOriginImpl("Ch. Margaux", "Bordeaux, Margaux", "France");
        final WineComposition composition2 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Cabernet Sauvignon");
        final Wine wine2 = new WineImpl("some_id2", "Very Grand cru", origin2, composition2, 1996, 13.5F, new BigDecimal(146.10));
        
        final LocalDate date1 = LocalDate.now().minusMonths(5).minusDays(12);
        final LocalDate date2 = LocalDate.now().minusMonths(5).minusDays(14);
        
        final DepletionRecord record1 = new DepletionRecordImpl("id1", wine1, 1, BottleSize.STANDARD, date1, 4, "Very nice");
        final DepletionRecord record2 = new DepletionRecordImpl("id1", wine1, 1, BottleSize.STANDARD, date1, 4, "Very nice");
        final DepletionRecord record3 = new DepletionRecordImpl("id2", wine2, 2, BottleSize.STANDARD, date2, 4.5f, "Lush");
        
        assertEquals(record1, record2);
        assertNotEquals(record1, record3);
    }
}
