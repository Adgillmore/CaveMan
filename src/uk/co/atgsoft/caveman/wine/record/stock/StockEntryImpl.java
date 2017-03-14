/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.BaseRecordEntryImpl;

/**
 *
 * @author adam.gillmore
 */
public class StockEntryImpl extends BaseRecordEntryImpl implements StockEntry {

    private final float mAvgRating;
    
    private final String mNotes;
    
    private final BigDecimal mAvgPrice;

    public StockEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size, final BigDecimal avgPrice, final float rating, final String TastingNotes) {
        super(id, wine, quantity, size);
        if (rating < 0) throw new IllegalArgumentException("Rating cannot br less than 0");
        if (TastingNotes == null || TastingNotes.isEmpty()) throw new IllegalArgumentException("Review can't be null or empty");
        mAvgRating = rating;
        mNotes = TastingNotes;
        mAvgPrice = avgPrice;
    }

    @Override
    public float getRating() {
        return mAvgRating;
    }

    @Override
    public String getTastingNotes() {
        return mNotes;
    }

    @Override
    public BigDecimal getAvgPrice() {
        return mAvgPrice;
    }
    
}
