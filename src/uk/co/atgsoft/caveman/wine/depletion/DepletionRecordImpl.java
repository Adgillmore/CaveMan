/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.depletion;

import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.WineRecordImpl;

/**
 *
 * @author adam
 */
public class DepletionRecordImpl extends WineRecordImpl implements DepletionRecord {

    private float mRating;
    
    private String mReview;

    public DepletionRecordImpl(final String id, final Wine wine, final int quantity, 
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
    
}
