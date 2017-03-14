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
import uk.co.atgsoft.caveman.wine.record.DatedRecordEntryImpl;

/**
 *
 * @author adam
 */
public class DepletionEntryImpl extends DatedRecordEntryImpl implements DepletionEntry {
    
    private float mRating;
    
    private String mReview;

    public DepletionEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size, final LocalDate date, final float rating, 
            final String review) {
        super(id, wine, quantity, size, date);
        mRating = rating;
        mReview = review;
    }
    
    @Override
    public void setRating(final float rating) {
        mRating = rating;
    }

    @Override
    public float getRating() {
        return mRating;
    }

    @Override
    public void setReview(final String review) {
        mReview = review;
    }

    @Override
    public String getReview() {
        return mReview;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWine(), getDate(), getBottleSize(), getQuantity(), mRating, mReview);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof DepletionEntryImpl)) {
            return false;
        }
        DepletionEntryImpl other = (DepletionEntryImpl) obj;
        return Objects.equals(mRating, other.getRating()) &&
                Objects.equals(mReview, other.getReview()) &&
                Objects.equals(getId(), other.getId()) &&
                Objects.equals(getDate(), other.getDate()) &&
                Objects.equals(getWine(), other.getWine()) &&
                Objects.equals(getBottleSize(), other.getBottleSize()) &&
                Objects.equals(getQuantity(), other.getQuantity());
    }
    
    
    
}
