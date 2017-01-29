/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.depletion.DepletionRecord;

/**
 *
 * @author adam.gillmore
 */
public interface DepletionDao {
    
    void addDepletion(DepletionRecord depletion);
    
    void removeDepletion(DepletionRecord depletion);
    
    void updateDepletion(DepletionRecord depletion);
    
    List<DepletionRecord> getAllDepletions();
    
    List<DepletionRecord> getDepletions(Wine wine);
}
