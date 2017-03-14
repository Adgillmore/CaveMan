/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.tasting;

import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.DatedRecordEntryImpl;

/**
 *
 * @author adam
 */
public class TastingEntryImpl extends DatedRecordEntryImpl implements TastingEntry {

    private final String mReviewer;
    
    private final String mLocation;
    
    private final float mRating;
    
    private final String mNotes;

    public TastingEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size, final LocalDate date, final String reviewer, final String location,
            final float rating, final String notes) {
        super(id, wine, quantity, size, date);
        mReviewer = reviewer;
        mLocation = location;
        mRating = rating;
        mNotes = notes;
    }
    
    @Override
    public String getReviewer() {
        return mReviewer;
    }

    @Override
    public String getLocation() {
        return mLocation;
    }

    @Override
    public float getRating() {
        return mRating;
    }

    @Override
    public String getNotes() {
        return mNotes;
    }
    
}
