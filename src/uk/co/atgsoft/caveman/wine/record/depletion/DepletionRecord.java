/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

import uk.co.atgsoft.caveman.wine.record.WineRecord;

/**
 *
 * @author adam
 */
public interface DepletionRecord extends WineRecord {
    
    void setRating(float rating);
    
    float getRating();
    
    void setReview(String review);
    
    String getReview();
}
