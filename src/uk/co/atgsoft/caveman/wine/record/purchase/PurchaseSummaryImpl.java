/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.QuantityEntryImpl;
import uk.co.atgsoft.caveman.wine.record.WineEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntryImpl;

/**
 * A summary of all purchases for a given bottle size.
 * @author adam.gillmore
 */
public class PurchaseSummaryImpl implements PurchaseSummary {

    private WineEntry mWineEntry;
    private QuantityEntry mQuantityEntry;
    private BigDecimal mAvgPrice;
    
    public PurchaseSummaryImpl(final Wine wine, 
            final BigDecimal avgPrice, final int quantity, 
            final BottleSize size) {
        mWineEntry = new WineEntryImpl(wine.getId(), wine);
        mQuantityEntry = new QuantityEntryImpl(quantity, size);
        mAvgPrice = avgPrice;
    }
    
    @Override
    public BigDecimal getAvgPrice() {
        return mAvgPrice;
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
        mQuantityEntry.setQuantity(quantity);
    }

    @Override
    public int getQuantity() {
        return mQuantityEntry.getQuantity();
    }

    @Override
    public void setBottleSize(BottleSize size) {
        mQuantityEntry.setBottleSize(size);
    }

    @Override
    public BottleSize getBottleSize() {
        return mQuantityEntry.getBottleSize();
    }
    
}
