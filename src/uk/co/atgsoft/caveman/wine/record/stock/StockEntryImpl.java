/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.QuantityEntryImpl;
import uk.co.atgsoft.caveman.wine.record.WineEntryImpl;

/**
 *
 * @author adam.gillmore
 */
public class StockEntryImpl implements StockEntry {

    private final WineEntryImpl mWine;
    
    private final QuantityEntry mQuantity;
    
    private final BigDecimal mAvgPrice;
    
    private final float mAvgRating;

    public StockEntryImpl(final String id, final Wine wine, final int quantity, final BottleSize size, 
            final BigDecimal avgPrice, final float rating) {
        mWine = new WineEntryImpl(id, wine);
        mQuantity = new QuantityEntryImpl(quantity, size);
        if (rating < 0) throw new IllegalArgumentException("Rating cannot be less than 0");
        mAvgPrice = avgPrice;
        mAvgRating = rating;
    }

    @Override
    public float getAvgRating() {
        return mAvgRating;
    }

    @Override
    public BigDecimal getAvgPrice() {
        return mAvgPrice;
    }

    @Override
    public void setId(String id) {
        mWine.setId(id);
    }

    @Override
    public String getId() {
        return mWine.getId();
    }

    @Override
    public void setWine(Wine wine) {
        mWine.setWine(wine);
    }

    @Override
    public Wine getWine() {
        return mWine.getWine();
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

    
}
