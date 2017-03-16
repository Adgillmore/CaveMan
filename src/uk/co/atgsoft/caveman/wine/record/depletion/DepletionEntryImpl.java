/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

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
public class DepletionEntryImpl implements WineEntry, QuantityEntry, DepletionEntry, DateEntry {
    
    private final WineEntry mWineEntry;
    
    private final QuantityEntryImpl mQuantityEntry;
    
    private LocalDate mDate;

    public DepletionEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size, final LocalDate date) {
        mWineEntry = new WineEntryImpl(id, wine);
        mQuantityEntry = new QuantityEntryImpl(quantity, size);
        mDate = date;
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
    public void setDate(LocalDate date) {
        mDate = date;
    }

    @Override
    public LocalDate getDate() {
        return mDate;
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

    @Override
    public int hashCode() {
        return Objects.hash(mWineEntry, mQuantityEntry, mDate);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DepletionEntryImpl)) {
            return false;
        }
        DepletionEntryImpl other = (DepletionEntryImpl) obj;
        return Objects.equals(mWineEntry.getWine(), other.getWine()) &&
                Objects.equals(mWineEntry.getId(), other.getId()) &&
                Objects.equals(getDate(), other.getDate()) &&
                Objects.equals(getBottleSize(), other.getBottleSize()) &&
                Objects.equals(getQuantity(), other.getQuantity());
    }
}
