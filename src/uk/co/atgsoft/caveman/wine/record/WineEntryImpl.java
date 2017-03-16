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
public class WineEntryImpl implements WineEntry {

    private String mId;
    private Wine mWine;

    public WineEntryImpl(final String id, final Wine wine) {
        if (id == null || id.isEmpty()) throw new IllegalArgumentException("Id cannot be null or empty");
        if (wine == null) throw new IllegalArgumentException("Wine cannot be null");
           
        mId = id;
        mWine = wine;
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public void setId(String id) {
        mId = id;
    }

    @Override
    public Wine getWine() {
        return mWine;
    }
    
    @Override
    public void setWine(final Wine wine) {
        if (wine == null) {
            throw new IllegalArgumentException("Wine cannot be null");
        }
        mWine = wine;
    }

}
