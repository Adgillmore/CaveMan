/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

import uk.co.atgsoft.caveman.wine.WineColour;
import uk.co.atgsoft.caveman.wine.WineStyle;

/**
 *
 * @author adam.gillmore
 */
public interface WineComposition {
    
    void setGrape(String grape);
    
    String getGrape();
    
    void setColour(WineColour colour);
    
    WineColour getWineColour();
    
    void setStyle(WineStyle style);
    
    WineStyle getStyle();
}
