/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.stock;

import java.math.BigDecimal;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam.gillmore
 */
public interface StockEntry {
    
    Wine getWine();
    
    BottleSize getBottleSize();
    
    int getQuantity();
    
    BigDecimal getAveragePrice();
    
    float getAverageRating();
}
