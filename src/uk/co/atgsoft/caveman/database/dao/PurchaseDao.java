/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import java.util.Map;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseEntry;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseSummary;

/**
 *
 * @author adam.gillmore
 */
public interface PurchaseDao {
    
    void addPurchase(PurchaseEntry purchase);
    
    void removePurchase(PurchaseEntry purchase);
    
    void updatePurchase(PurchaseEntry purchase);
    
    List<PurchaseEntry> getPurchases(Wine wine);
    
    Map<BottleSize, PurchaseSummary> getPurchaseSummary(Wine wine);
}
