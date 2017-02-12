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
    
    private WineOrigin mOrigin;
    
    private WineComposition mComposition;
    
    private int mVintage;
    
    private float mAlcohol;
    
    private BigDecimal mAvgPrice;
    
    private String delimiter = ": ";
    
    private String spacer = ", ";
    
    public WineImpl() {
    }

    public WineImpl(String id, final String name, final WineOrigin origin, final WineComposition composition, 
            int vintage, float alcohol, BigDecimal price) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (origin == null) throw new IllegalArgumentException("Origin cannot be null");
        if (composition == null) throw new IllegalArgumentException("Composition cannot be null");
        if (vintage < 1800 || vintage > 2200) throw new IllegalArgumentException("Vintage is out of range");
        if (price == null) throw new IllegalArgumentException("Price cannot be null");
        if (alcohol < 0) throw new IllegalArgumentException("Alcohol cannot be less than 0");
        mId = id;
        mName = name;
        mOrigin = origin;
        mComposition = composition;
        mVintage = vintage;
        mAlcohol = alcohol;
        mAvgPrice = price;
        
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
    public int getVintage() {
        return mVintage;
    }

    @Override
    public void setVintage(final int vintage) {
        if (vintage < 1800 || vintage > 2200) return;
        mVintage = vintage;
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
    public void setProducer(String producer) {
        mOrigin.setProducer(producer);
    }

    @Override
    public String getProducer() {
        return mOrigin.getProducer();
    }

    @Override
    public void setGrape(String grape) {
        mComposition.setGrape(grape);
    }

    @Override
    public String getGrape() {
        return mComposition.getGrape();
    }

    @Override
    public void setCountry(String country) {
        mOrigin.setCountry(country);
    }

    @Override
    public String getCountry() {
        return mOrigin.getCountry();
    }

    @Override
    public void setRegion(String region) {
        mOrigin.setRegion(region);
    }

    @Override
    public String getRegion() {
        return mOrigin.getRegion();
    }

    @Override
    public void setColour(WineColour colour) {
        mComposition.setColour(colour);
    }

    @Override
    public WineColour getWineColour() {
        return mComposition.getWineColour();
    }

    @Override
    public void setStyle(WineStyle style) {
        mComposition.setStyle(style);
    }

    @Override
    public WineStyle getStyle() {
        return mComposition.getStyle();
    }

    
    
    
}
