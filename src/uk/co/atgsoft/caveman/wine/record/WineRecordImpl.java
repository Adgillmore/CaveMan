/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public class WineRecordImpl implements WineRecord {

    private final Wine mWine;

    public WineRecordImpl(final Wine wine) {
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
        mWine = wine;
    }

    @Override
    public Wine getWine() {
        return mWine;
    }
    
}
