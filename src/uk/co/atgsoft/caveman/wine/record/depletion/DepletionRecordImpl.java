/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.depletion;

import java.util.ArrayList;
import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.WineRecordImpl;

/**
 *
 * @author adam
 */
public class DepletionRecordImpl extends WineRecordImpl implements DepletionRecord {

    private final List<DepletionEntry> entries;

    public DepletionRecordImpl(final Wine wine) {
        super(wine);
        entries = new ArrayList<>();
    }
    
    @Override
    public List<DepletionEntry> getDepletions() {
        return entries;
    }

    @Override
    public void addDepletion(final DepletionEntry entry) {
        if (entry == null) throw new IllegalArgumentException("Purchase entry cannot be null");
        if (!entries.contains(entry)) entries.add(entry);
    }
    
}
