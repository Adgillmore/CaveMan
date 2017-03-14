/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public abstract class BaseRecordEntryImpl implements RecordEntry {

    private String mId;
    private int mQuantity;
    private BottleSize mSize;
    private Wine mWine;

    public BaseRecordEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Id cannot be null or empty");
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be less than 0");
        if (size == null) throw new IllegalArgumentException("Size cannot be null");
           
        mId = id;
        mWine = wine;
        mQuantity = quantity;
        mSize = size;
    }
    
    @Override
    public BottleSize getBottleSize() {
        return mSize;
    }
    
    @Override
    public void setBottleSize(final BottleSize size) {
        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }
        mSize = size;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public void setId(String id) {
        mId = id;
    }

    @Override
    public int getQuantity() {
        return mQuantity;
    }
    
    @Override
    public void setQuantity(final int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }
        mQuantity = quantity;
    }

    @Override
    public Wine getWine() {
        return mWine;
    }
    
    @Override
    public void setWine(final Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        mWine = wine;
    }

}
