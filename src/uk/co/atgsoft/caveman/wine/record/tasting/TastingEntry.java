/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.tasting;

import uk.co.atgsoft.caveman.wine.record.DateEntry;

/**
 *
 * @author adam
 */
public interface TastingEntry extends DateEntry {
    
    String getReviewer();
    
    String getLocation();
    
    float getRating();
    
    String getNotes();
}
