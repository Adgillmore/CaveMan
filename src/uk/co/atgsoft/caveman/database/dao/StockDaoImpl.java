/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.Map;
import java.util.Map.Entry;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionSummary;
import uk.co.atgsoft.caveman.wine.record.purchase.PurchaseSummary;
import uk.co.atgsoft.caveman.wine.record.stock.StockEntry;
import uk.co.atgsoft.caveman.wine.record.stock.StockEntryImpl;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecord;
import uk.co.atgsoft.caveman.wine.record.stock.StockRecordImpl;

/**
 *
 * @author adam.gillmore
 */
public class StockDaoImpl implements StockDao {

    private final PurchaseDao mPurchaseDao;
    private final DepletionDao mDepletionDao;
    
    public StockDaoImpl(final String databaseName) {
        mPurchaseDao = new PurchaseDaoImpl(databaseName);
        mDepletionDao = new DepletionDaoImpl(databaseName);
    }
    
    @Override
    public StockRecord getStockRecord(final Wine wine) {
        final Map<BottleSize, PurchaseSummary> allAdditions = mPurchaseDao.getPurchaseSummary(wine);
        final Map<BottleSize, DepletionSummary> allDepletions = mDepletionDao.getDepletionSummary(wine);
        return calculateStock(allAdditions, allDepletions);
    }
    
    private StockRecord calculateStock(final Map<BottleSize, PurchaseSummary> allAdditions, 
            final Map<BottleSize, DepletionSummary> allDepletions) {
        final StockRecord record = new StockRecordImpl(allAdditions.get(BottleSize.STANDARD).getWine());
        for (Entry<BottleSize, PurchaseSummary> entry : allAdditions.entrySet()) {
            
            final BottleSize size = entry.getKey();
            final PurchaseSummary addition = entry.getValue();
            final int added = addition.getQuantity();
            
            final DepletionSummary depletions = allDepletions.get(size);
            final int remaining = added - (depletions == null ? 0 : depletions.getQuantity());
            final StockEntry stockEntry = new StockEntryImpl(addition.getWine(), remaining, size,  
                    addition.getAvgPrice(), 5.0f);
            record.addStockEntry(stockEntry);
        }
        
        return record;
    }

}
