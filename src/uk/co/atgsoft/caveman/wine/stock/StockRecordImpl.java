/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.stock;

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
        this(wine, 0);
    }
    
    public StockRecordImpl(final Wine wine, int stock) {
        this(wine, BottleSize.STANDARD, stock);
    }
    
    public StockRecordImpl(final Wine wine, final BottleSize size, final int stock) {
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
        mWine = wine;
        mStock = new HashMap<>();
        mStock.put(size, new StockEntryImpl(size, stock));
    }
        
    @Override
    public Wine getWine() {
        return mWine;
    }

    @Override
    public int getNumberOfBottles() {
        int count = 0;
        for (Entry<BottleSize, StockEntry> e : mStock.entrySet()) {
            count += e.getValue().getQuantity();
        }
        return count;
    }

    @Override
    public float getNumberOfStandardBottles() {
        int count = 0;
        for (Entry<BottleSize, StockEntry> e : mStock.entrySet()) {
            count += e.getValue().getQuantity() * e.getKey().getMultiplier();
        }
        return count;
    }

    @Override
    public float getVolume() {
        return getNumberOfStandardBottles() * 0.75f;
    }

    @Override
    public void addStock(final BottleSize size, final int quantity) {
        if (size == null || quantity < 1) throw new IllegalArgumentException("Illegal size or quantity");
        if (mStock.containsKey(size)) {
            final StockEntry entry = mStock.get(size);
            entry.setQuantity(quantity + entry.getQuantity());
        } else {
            mStock.put(size, new StockEntryImpl(size, quantity));
        }
        
    }

    @Override
    public void depleteStock(final BottleSize size, final int quantity) {
        if (size == null || quantity < 1) throw new IllegalArgumentException("Illegal size or quantity");
        if (!mStock.containsKey(size)) throw new IllegalArgumentException("No stock existing");
        final StockEntry entry = mStock.get(size);
        if (quantity > entry.getQuantity()) throw new IllegalArgumentException("Not quantity exceeds stock");
        entry.setQuantity(entry.getQuantity() - quantity);
    }

    @Override
    public Map<BottleSize, StockEntry> getStock() {
        return mStock;
    }
    
}
