/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

import java.util.List;
import uk.co.atgsoft.caveman.wine.record.WineRecord;

/**
 *
 * @author adam
 */
public interface DepletionRecord extends WineRecord {
    
    List<DepletionEntry> getDepletions();
    
    void addDepletion(DepletionEntry entry);
}
