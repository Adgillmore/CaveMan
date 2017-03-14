/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.tasting;

import java.util.List;
import uk.co.atgsoft.caveman.wine.record.WineRecord;

/**
 *
 * @author adam
 */
public interface TastingRecord extends WineRecord {
    
    List<TastingEntry> getTastingEntries();
    
    void addTastingEntry(final TastingEntry entry);
}
