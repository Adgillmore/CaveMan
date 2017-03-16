/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import uk.co.atgsoft.caveman.wine.BottleSize;

/**
 *
 * @author adam
 */
public class QuantityEntryImpl implements QuantityEntry {

    private int mQuantity;
    
    private BottleSize mSize;
    
    public QuantityEntryImpl(final int quantity, final BottleSize size) {
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be less than 0");
        if (size == null) throw new IllegalArgumentException("Size cannot be null");
        mQuantity = quantity;
        mSize = size;
    }
    
    @Override
    public void setQuantity(int quantity) {
        mQuantity = quantity;
    }

    @Override
    public int getQuantity() {
        return mQuantity;
    }

    @Override
    public void setBottleSize(BottleSize size) {
        mSize = size;
    }

    @Override
    public BottleSize getBottleSize() {
        return mSize;
    }
    
}
