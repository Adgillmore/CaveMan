/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.QuantityEntryImpl;
import uk.co.atgsoft.caveman.wine.record.WineEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntryImpl;

/**
 *
 * @author adam.gillmore
 */
public class DepletionSummaryImpl implements DepletionSummary {

    private WineEntry mWineEntry;
    private QuantityEntry mQuantityEntry;
    
    public DepletionSummaryImpl(final Wine wine, final int quantity, 
            final BottleSize size) {
        mWineEntry = new WineEntryImpl(wine.getId(), wine);
        mQuantityEntry = new QuantityEntryImpl(quantity, size);
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
