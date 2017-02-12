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
public interface WineOrigin {
    
    void setProducer(String producer);
    
    String getProducer();
    
    void setCountry(String country);
    
    String getCountry();
    
    void setRegion(String region);
    
    String getRegion();
}
