/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public class PurchaseRecordImpl implements PurchaseRecord {
    
    private String mId;

    private Wine mWine;
    
    private BigDecimal mPrice;
    
    private int mQuantity;
    
    private BottleSize mSize;
    
    private String mVendor;
    
    private LocalDate mDate;

    public PurchaseRecordImpl(final Wine wine, final BigDecimal price, final int quantity, final BottleSize size, 
            final String vendor, final LocalDate date) {
        mWine = wine;
        mPrice = price;
        mQuantity = quantity;
        mSize = size;
        mVendor = vendor;
        mDate = date;
    }
    
    
    
    @Override
    public void setWine(final Wine wine) {
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
        mWine = wine;
    }

    @Override
    public Wine getWine() {
        return mWine;
    }

    @Override
    public void setPrice(final BigDecimal price) {
        if (price == null || price.floatValue() < 0) throw new IllegalArgumentException("Invalid price");
        mPrice = price;
    }

    @Override
    public BigDecimal getPrice() {
        return mPrice;
    }

    @Override
    public void setQuantity(final int quantity) {
        if (quantity < 1) throw new IllegalArgumentException("Quantity must be at least 1");
        mQuantity = quantity;
    }

    @Override
    public int getQuantity() {
        return mQuantity;
    }

    @Override
    public void setBottleSize(final BottleSize size) {
        if (size == null) throw new IllegalArgumentException("Size cannot be null");
        mSize = size;
    }

    @Override
    public BottleSize getBottleSize() {
        return mSize;
    }

    @Override
    public void setVendor(final String vendor) {
        if (vendor == null || vendor.isEmpty()) throw new IllegalArgumentException("Vendor cannot be null or empty");
        mVendor = vendor;
    }

    @Override
    public String getVendor() {
        return mVendor;
    }

    @Override
    public void setDate(final LocalDate date) {
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        mDate = date;
    }

    @Override
    public LocalDate getDate() {
        return mDate;
    }

    @Override
    public void setId(String id) {
        mId = id;
    }

    @Override
    public String getId() {
        return mId;
    }
    
}
