/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.DateEntry;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.QuantityEntryImpl;
import uk.co.atgsoft.caveman.wine.record.WineEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntryImpl;

/**
 *
 * @author adam
 */
public class PurchaseEntryImpl implements WineEntry, QuantityEntry, PurchaseEntry, DateEntry {
    
    private final WineEntry mWineEntry;
    
    private final QuantityEntry mQuantity;
    
    private BigDecimal mPrice;
    
    private String mVendor;
    
    private LocalDate mDate;

    public PurchaseEntryImpl(final String id, final Wine wine, 
            final BigDecimal price, final int quantity, 
            final BottleSize size, final String vendor, final LocalDate date) {
        mWineEntry = new WineEntryImpl(id, wine);
        mQuantity = new QuantityEntryImpl(quantity, size);
        mPrice = price;
        mVendor = vendor;
        mDate = date;
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

    @Override
    public void setDate(LocalDate date) {
        mDate = date;
    }

    @Override
    public LocalDate getDate() {
        return mDate;
    }

    @Override
    public void setId(String id) {
        mWineEntry.setId(id);
    }

    @Override
    public String getId() {
        return mWineEntry.getId();
    }

    @Override
    public void setWine(Wine wine) {
        mWineEntry.setWine(wine);
    }

    @Override
    public Wine getWine() {
        return mWineEntry.getWine();
    }
    
    @Override
    public void setQuantity(int quantity) {
        mQuantity.setQuantity(quantity);
    }

    @Override
    public int getQuantity() {
        return mQuantity.getQuantity();
    }

    @Override
    public void setBottleSize(BottleSize size) {
        mQuantity.setBottleSize(size);
    }

    @Override
    public BottleSize getBottleSize() {
        return mQuantity.getBottleSize();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(mWineEntry, mPrice, mVendor, mDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof PurchaseEntryImpl)) {
            return false;
        }
        PurchaseEntryImpl other = (PurchaseEntryImpl) obj;
        return Objects.equals(mWineEntry.getId(), other.getId()) &&
                Objects.equals(mWineEntry.getWine(), other.getWine()) &&
                Objects.equals(mDate, other.getDate()) &&
//                Objects.equals(mPrice, other.getPrice()) &&
                Objects.equals(mVendor, other.getVendor());
    }
}
