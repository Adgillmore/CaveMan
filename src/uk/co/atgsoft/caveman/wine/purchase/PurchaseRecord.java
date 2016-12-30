/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.purchase;

import java.math.BigDecimal;
import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public interface PurchaseRecord {
    
    void setId(String id);
    
    String getId();
    
    void setWine(Wine wine);
    
    Wine getWine();
    
    void setPrice(BigDecimal price);
    
    BigDecimal getPrice();
    
    void setQuantity(int quantity);
    
    int getQuantity();
    
    void setBottleSize(BottleSize size);
    
    BottleSize getBottleSize();
    
    void setVendor(String vendor);
    
    String getVendor();
    
    void setDate(LocalDate date);
    
    LocalDate getDate();
    
}
