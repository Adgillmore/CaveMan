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
public interface Wine {
    
    void setId(String id);
    
    String getId();
    
    void setName(String name);
    
    String getName();
    
    void setProducer(String producer);
    
    String getProducer();
    
    void setVintage(int vintage);
    
    int getVintage();
    
    void setGrape(String grape);
    
    String getGrape();
    
    void setCountry(String country);
    
    String getCountry();
    
    void setRegion(String region);
    
    String getRegion();
    
    void setColour(WineColour colour);
    
    WineColour getWineColour();
    
    void setAlcohol(float alcohol);
    
    float getAlcohol();
    
    void setPrice(BigDecimal price);
    
    BigDecimal getPrice();
    
}
