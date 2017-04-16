/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

import uk.co.atgsoft.caveman.wine.record.QuantityEntry;
import uk.co.atgsoft.caveman.wine.record.WineEntry;

/**
 * a summary of all depletions of the specified wine for a given bottle size.
 * @author adam.gillmore
 */
public interface DepletionSummary extends WineEntry, QuantityEntry {
    
}
