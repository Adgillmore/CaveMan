/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

/**
 *
 * @author adam
 */
public interface Wine {
    
    void setName(String name);
    
    String getName();
    
    void setProducer(String producer);
    
    String getProducer();
    
    int getVintage();
    
    void setVintage(int vintage);
    
    String getGrape();
    
    void setGrape(String grape);
    
}
