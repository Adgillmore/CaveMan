/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

/**
 *
 * @author adam.gillmore
 */
public class WineOriginImpl implements WineOrigin {
    
    private String mProducer;
    
    private String mCountry;
    
    private String mRegion;

    public WineOriginImpl(final String producer, final String region, final String country) {
        if (producer == null) throw new IllegalArgumentException("Producer cannot be null");
        if (region == null) throw new IllegalArgumentException("Region cannot be null");
        if (country == null) throw new IllegalArgumentException("Country cannot be null");
        mProducer = producer;
        mRegion = region;
        mCountry = country;
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
    public void setCountry(final String country) {
        if (country == null) {
            throw new IllegalArgumentException("Country cannot be null");
        }
        mCountry = country;
    }

    @Override
    public String getCountry() {
        return mCountry;
    }

    @Override
    public void setRegion(final String region) {
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        mRegion = region;
    }

    @Override
    public String getRegion() {
        return mRegion;
    }
    
}
