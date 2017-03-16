/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.tasting;

import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.DateEntry;
import uk.co.atgsoft.caveman.wine.record.DateEntryImpl;
import uk.co.atgsoft.caveman.wine.record.WineEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntryImpl;

/**
 *
 * @author adam
 */
public class TastingEntryImpl implements WineEntry, TastingEntry, DateEntry {

    private final WineEntry mWine;
    
    private final DateEntry mDate;
    
    private final String mReviewer;
    
    private final String mLocation;
    
    private final float mRating;
    
    private final String mNotes;

    public TastingEntryImpl(final String id, final Wine wine, final LocalDate date, final String reviewer, 
            final String location, final float rating, final String notes) {
        mWine = new WineEntryImpl(id, wine);
        mDate = new DateEntryImpl(date);
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

    @Override
    public void setDate(LocalDate date) {
        mDate.setDate(date);
    }

    @Override
    public LocalDate getDate() {
        return mDate.getDate();
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
    
}
