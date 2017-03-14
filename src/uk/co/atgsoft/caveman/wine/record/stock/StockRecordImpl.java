/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.util.HashMap;
import java.util.Map;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.WineRecordImpl;

/**
 *
 * @author adam
 */
public class StockRecordImpl extends WineRecordImpl implements StockRecord {

    private final Map<BottleSize, StockEntry> mStock;
    
    public StockRecordImpl(final Wine wine) {
        super(wine);
        mStock = new HashMap<>();
    }

    @Override
    public Map<BottleSize, StockEntry> getStock() {
        return mStock;
    }

    @Override
    public void addStockEntry(StockEntry entry) {
        if (entry == null) throw new IllegalArgumentException("Entry cannot be null");
        mStock.put(entry.getBottleSize(), entry);
    }
    
}
