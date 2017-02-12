/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.WineRecordImpl;

/**
 *
 * @author adam
 */
public class PurchaseRecordImpl extends WineRecordImpl implements PurchaseRecord {
    
    private BigDecimal mPrice;
    
    private String mVendor;

    public PurchaseRecordImpl(final String id, final Wine wine, 
            final BigDecimal price, final int quantity, 
            final BottleSize size, final String vendor, final LocalDate date) {
        super(id, wine, quantity, size, date);
        mPrice = price;
        mVendor = vendor;
    }
    
    @Override
    public void setPrice(final BigDecimal price) {
        if (price == null || price.floatValue() < 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        mPrice = price;
    }

    @Override
    public BigDecimal getPrice() {
        return mPrice;
    }
    
    @Override
    public void setVendor(final String vendor) {
        if (vendor == null || vendor.isEmpty()) {
            throw new IllegalArgumentException("Vendor cannot be null or empty");
        }
        mVendor = vendor;
    }

    @Override
    public String getVendor() {
        return mVendor;
    }

}
