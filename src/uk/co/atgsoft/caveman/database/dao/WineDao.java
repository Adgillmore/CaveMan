/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.database.dao;

import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam.gillmore
 */
public interface WineDao {
    
    boolean exists(Wine wine);
    
    void insertWine(Wine wine);
    
    void removeWine(Wine wine);
    
    void updateWine(Wine wine);
    
    List<Wine> getAllWines();
}
