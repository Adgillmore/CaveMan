/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineComposition;
import uk.co.atgsoft.caveman.wine.WineStyle;

/**
 *
 * @author adam.gillmore
 */
public class WineCompositionImpl implements WineComposition {
    
    private String mGrape;
    
    private WineColour mColour;
    
    private WineStyle mStyle;

    public WineCompositionImpl(final WineColour colour, final WineStyle style, final String grape) {
        if (grape == null) throw new IllegalArgumentException("Grape cannot be null");
        if (colour == null) throw new IllegalArgumentException("Colour cannot be null");
        if (style == null) throw new IllegalArgumentException("Style cannot be null");
        mColour = colour;
        mGrape = grape;
        mStyle = style;
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
    public void setStyle(final WineStyle style) {
        if (style == null) throw new IllegalArgumentException("Style cannot be null");
        mStyle = style;
    }

    @Override
    public WineStyle getStyle() {
        return mStyle;
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
