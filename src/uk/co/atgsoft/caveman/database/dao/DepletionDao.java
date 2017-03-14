/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.depletion.DepletionEntry;

/**
 *
 * @author adam.gillmore
 */
public interface DepletionDao {
    
    void addDepletion(DepletionEntry depletion);
    
    void removeDepletion(DepletionEntry depletion);
    
    void updateDepletion(DepletionEntry depletion);
    
    List<DepletionEntry> getDepletions(Wine wine);
}
