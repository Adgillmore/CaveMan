/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import uk.co.atgsoft.caveman.wine.BottleSize;

/**
 *
 * @author adam
 */
public interface QuantityEntry {
    
    void setQuantity(int quantity);
    
    int getQuantity();
    
    void setBottleSize(BottleSize size);
    
    BottleSize getBottleSize();
}
