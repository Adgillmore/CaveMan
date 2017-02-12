/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecord;

/**
 *
 * @author adam.gillmore
 */
public interface StockDao {
    
    void addStock(StockRecord stock);
    
    void removeStock(StockRecord stock);
    
    void updateStock(StockRecord stock);
    
    StockRecord getStockRecord(Wine wine);
    
    List<StockRecord> getAllStockRecords();
}
