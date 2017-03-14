/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.purchase;

import java.util.List;
import uk.co.atgsoft.caveman.wine.record.WineRecord;

/**
 *
 * @author adam
 */
public interface PurchaseRecord extends WineRecord {
    
    List<PurchaseEntry> getPurchases();
    
    void addPurchaseEntry(PurchaseEntry entry);
}
