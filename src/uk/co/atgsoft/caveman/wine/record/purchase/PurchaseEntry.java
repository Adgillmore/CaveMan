/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.math.BigDecimal;

/**
 * An abstraction of a wine purchase.
 * @author adam
 */
public interface PurchaseEntry {
    
    void setPrice(BigDecimal price);
    
    BigDecimal getPrice();
    
    void setVendor(String vendor);
    
    String getVendor();
}
