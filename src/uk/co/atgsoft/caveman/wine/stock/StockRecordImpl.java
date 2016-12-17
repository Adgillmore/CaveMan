/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import uk.co.atgsoft.caveman.Wine.BottleSize;
import uk.co.atgsoft.caveman.Wine.Wine;

/**
 *
 * @author adam
 */
public class StockRecordImpl implements StockRecord {

    private final Wine mWine;
    
    private final Map<BottleSize, Integer> mStock;

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
    public int getNumberOfBottles() {
        int count = 0;
        for (Entry<BottleSize, Integer> e : mStock.entrySet()) {
            count += e.getValue();
        }
        return count;
    }

    @Override
    public float getNumberOfStandardBottles() {
        int count = 0;
        for (Entry<BottleSize, Integer> e : mStock.entrySet()) {
            count += e.getValue() * e.getKey().getMultiplier();
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
        int existingStock = 0;
        if (mStock.containsKey(size)) existingStock = mStock.get(size); 
        mStock.put(size, existingStock + quantity);
    }

    @Override
    public void depleteStock(final BottleSize size, final int quantity) {
        if (size == null || quantity < 1) throw new IllegalArgumentException("Illegal size or quantity");
        if (!mStock.containsKey(size)) throw new IllegalArgumentException("No stock existing");
        int existingStock = mStock.get(size);
        if (quantity > existingStock) throw new IllegalArgumentException("Not quantity exceeds stock");
        mStock.put(size, existingStock - quantity);
    }
    
}
