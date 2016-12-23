/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.wine.purchase.PurchaseRecord;

/**
 *
 * @author adam.gillmore
 */
public interface PurchaseDao {
    
    void addPurchase(PurchaseRecord purchase);
    
    void removePurchase(PurchaseRecord purchase);
    
    void updatePurchase(PurchaseRecord purchase);
    
    List<PurchaseRecord> getAllPurchases();
}
