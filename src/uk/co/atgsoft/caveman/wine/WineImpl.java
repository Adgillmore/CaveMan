/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

import java.math.BigDecimal;

/**
 *
 * @author adam
 */
public class WineImpl implements Wine {
    
    private String mId;

    private String mName;
    
    private String mProducer;
    
    private int mVintage;
    
    private String mGrape;
    
    private String mCountry;
    
    private String mRegion;
    
    private WineColour mColour;
    
    private float mAlcohol;
    
    private BigDecimal mAvgPrice;
    
    private String delimiter = ": ";
    
    private String spacer = ", ";
    
    private WineStyle mStyle;

    public WineImpl() {
    }

    public WineImpl(String id, String name, String producer, final String region, 
            final String country, int vintage, float alcohol, BigDecimal price, 
            WineColour colour, WineStyle style, String grape) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (producer == null) throw new IllegalArgumentException("Producer cannot be null");
        if (region == null) throw new IllegalArgumentException("Region cannot be null");
        if (country == null) throw new IllegalArgumentException("Country cannot be null");
        if (vintage < 1800 || vintage > 2200) throw new IllegalArgumentException("Vintage is out of range");
        if (grape == null) throw new IllegalArgumentException("Grape cannot be null");
        if (colour == null) throw new IllegalArgumentException("Colour cannot be null");
        if (price == null) throw new IllegalArgumentException("Price cannot be null");
        if (style == null) throw new IllegalArgumentException("Style cannot be null");
        if (alcohol < 0) throw new IllegalArgumentException("Alcohol cannot be less than 0");
        mId = id;
        mName = name;
        mProducer = producer;
        mRegion = region;
        mCountry = country;
        mVintage = vintage;
        mAlcohol = alcohol;
        mAvgPrice = price;
        mColour = colour;
        mGrape = grape;
        mStyle = style;
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
        if (vintage < 1800 || vintage > 2200) return;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(getName())
                .append(spacer)
                .append(getProducer())
                .append(spacer)
                .append(getVintage())
                .append(spacer)
                .append(getGrape());
        return sb.toString();
    }

    @Override
    public String getId() {
        return mId;
    }

    @Override
    public void setId(final String id) {
        mId = id;
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

    @Override
    public void setColour(final WineColour colour) {
        if (colour == null) {
            throw new IllegalArgumentException("Colour cannot be null");
        }
        mColour = colour;
    }

    @Override
    public WineColour getWineColour() {
        return mColour;
    }

    @Override
    public void setAlcohol(final float alcohol) {
        if (alcohol < 0 || alcohol > 100) throw new IllegalArgumentException("Alcohol must be between 0 and 100");
        mAlcohol = alcohol;
    }

    @Override
    public float getAlcohol() {
        return mAlcohol;
    }

    @Override
    public void setPrice(final BigDecimal price) {
        if (price == null || price.floatValue() < 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
        mAvgPrice = price;
        
    }

    @Override
    public BigDecimal getPrice() {
        return mAvgPrice;
    }

    @Override
    public void setStyle(final WineStyle style) {
        if (style == null) throw new IllegalArgumentException("Style cannot be null");
        mStyle = style;
    }

    @Override
    public WineStyle getStyle() {
        return mStyle;
    }
    
    
    
}
