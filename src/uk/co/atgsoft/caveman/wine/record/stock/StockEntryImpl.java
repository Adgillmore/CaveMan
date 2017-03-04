/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam.gillmore
 */
public class StockEntryImpl implements StockEntry {

    private Wine mWine;
    
    private BottleSize mSize;
    
    private int mQuantity;
    
    private BigDecimal mAvgPrice;
    
    private float mAvgRating;

    public StockEntryImpl(final Wine wine, final BottleSize size, final int quantity,
            final BigDecimal avgPrice, final float avgRating) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be at least 0");
        if (size == null) throw new IllegalArgumentException("BottleSize cannot be null");
        mSize = size;
        mQuantity = quantity;
        mWine = wine;
        mAvgPrice = avgPrice;
        mAvgRating = avgRating;
    }
    
    @Override
    public BottleSize getBottleSize() {
        return mSize;
    }

    @Override
    public int getQuantity() {
        return mQuantity;
    }

    @Override
    public Wine getWine() {
        return mWine;
    }

    @Override
    public BigDecimal getAveragePrice() {
        return mAvgPrice;
    }

    @Override
    public float getAverageRating() {
        return mAvgRating;
    }
    
}
