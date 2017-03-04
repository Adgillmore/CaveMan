/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public class StockRecordImpl implements StockRecord {

    private final Wine mWine;
    
    private final Map<BottleSize, StockEntry> mStock;
    
    public StockRecordImpl(final Wine wine) {
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
        mWine = wine;
        mStock = new HashMap<>();
    }
        
    @Override
    public Wine getWine() {
        return mWine;
    }

    @Override
    public Map<BottleSize, StockEntry> getStock() {
        return mStock;
    }

    @Override
    public StockEntry getStockentry(BottleSize size) {
        return mStock.get(size);
    }

    @Override
    public void addStockEntry(StockEntry entry) {
        if (entry == null) throw new IllegalArgumentException("Entry cannot be null");
        mStock.put(entry.getBottleSize(), entry);
    }
    
}
