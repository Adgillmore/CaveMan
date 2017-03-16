/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.util.Map;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.record.WineRecord;

/**
 *
 * @author adam
 */
public interface StockRecord extends WineRecord {
    
    Map<BottleSize, StockEntryImpl> getStock();
    
    void addStockEntry(StockEntryImpl entry);
}
