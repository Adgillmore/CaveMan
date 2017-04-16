/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntry;

/**
 * A summary of all purchases for a given bottle size.
 * @author adam.gillmore
 */
public interface PurchaseSummary extends WineEntry, QuantityEntry {
    
    BigDecimal getAvgPrice();
}
