/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.stock;

import uk.co.atgsoft.caveman.wine.BottleSize;

/**
 *
 * @author adam.gillmore
 */
public class StockEntryImpl implements StockEntry {

    private BottleSize mSize;
    
    private int mQuantity;

    public StockEntryImpl() {
        this(BottleSize.STANDARD, 0);
    }

    public StockEntryImpl(final BottleSize size, final int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be at least 0");
        if (size == null) throw new IllegalArgumentException("BottleSize cannot be null");
        mSize = size;
        mQuantity = quantity;
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
    public void setQuantity(final int quantity) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be at least 0");
        mQuantity = quantity;
    }

    @Override
    public void setBottleSize(final BottleSize size) {
        if (size == null) throw new IllegalArgumentException("BottleSize cannot be null");
        mSize = size;
    }
    
}
