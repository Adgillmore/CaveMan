/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

import java.math.BigDecimal;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import uk.co.atgsoft.caveman.wine.record.WineCompositionImpl;

/**
 *
 * @author adam
 */
public class WineImplTest {
    
    @Test
    public void equals() {
        final WineOriginImpl origin1 = new WineOriginImpl("Chave", "Rhone, Hermitage", "France");
        final WineComposition composition1 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Syrah");
        final Wine wine1 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final Wine wine2 = new WineImpl("some_id", "Grand cru", origin1, composition1, 2012, 14.5F, new BigDecimal(36.10));
        
        final WineOriginImpl origin2 = new WineOriginImpl("Ch. Margaux", "Bordeaux, Margaux", "France");
        final WineComposition composition2 = new WineCompositionImpl(WineColour.RED, WineStyle.DRY, "Cabernet Sauvignon");
        final Wine wine3 = new WineImpl("some_id2", "Very Grand cru", origin2, composition2, 1996, 13.5F, new BigDecimal(146.10));
        
        assertEquals(wine1, wine2);
        assertNotEquals(wine1, wine3);
    }
}
