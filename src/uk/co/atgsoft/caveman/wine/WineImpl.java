/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public class WineImpl implements Wine {

    private String mName;
    
    private String mProducer;
    
    private int mVintage;
    
    private String mGrape;

    public WineImpl() {
    }

    public WineImpl(String name, String producer, int vintage, String grape) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (producer == null) throw new IllegalArgumentException("Producer cannot be null");
        if (vintage < 1800 || vintage > 2200) throw new IllegalArgumentException("Vintage is out of range");
        if (grape == null) throw new IllegalArgumentException("Grape cannot be null");
        mName = name;
        mProducer = producer;
        mVintage = vintage;
        mGrape = grape;
    }
    
    
    
    @Override
    public void setName(final String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        mName = name;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void setProducer(final String producer) {
        if (producer == null) throw new IllegalArgumentException("Producer cannot be null");
        mProducer= producer;
    }

    @Override
    public String getProducer() {
        return mProducer;
    }

    @Override
    public int getVintage() {
        return mVintage;
    }

    @Override
    public void setVintage(final int vintage) {
        if (vintage < 1800 || vintage > 2200) throw new IllegalArgumentException("Vintage is out of range");
        mVintage = vintage;
    }

    @Override
    public String getGrape() {
        return mGrape;
    }

    @Override
    public void setGrape(final String grape) {
        if (grape == null) throw new IllegalArgumentException("Grape cannot be null");
        mGrape = grape;
    }
    
}
