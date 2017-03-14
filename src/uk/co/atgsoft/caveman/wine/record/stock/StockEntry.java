/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.record.RecordEntry;

/**
 *
 * @author adam.gillmore
 */
public interface StockEntry extends RecordEntry {
    
    BigDecimal getAvgPrice();
    
    float getRating();
    
    String getTastingNotes();
}
