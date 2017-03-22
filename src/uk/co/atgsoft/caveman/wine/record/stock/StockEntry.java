/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntry;

/**
 *
 * @author adam.gillmore
 */
public interface StockEntry extends WineEntry, QuantityEntry  {
    
    BigDecimal getAvgPrice();
    
    float getAvgRating();

}
