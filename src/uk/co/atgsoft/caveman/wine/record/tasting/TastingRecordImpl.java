/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record.tasting;

import java.util.ArrayList;
import java.util.List;
import uk.co.atgsoft.caveman.wine.Wine;
import uk.co.atgsoft.caveman.wine.record.WineRecordImpl;

/**
 *
 * @author adam
 */
public class TastingRecordImpl extends WineRecordImpl implements TastingRecord {
    
    private final List<TastingEntry> entries;
    
    public TastingRecordImpl(final Wine wine) {
        super(wine);
        entries = new ArrayList<>();
    }

    @Override
    public List<TastingEntry> getTastingEntries() {
        return entries;
    }

    @Override
    public void addTastingEntry(final TastingEntry entry) {
        if (entry == null) throw new IllegalArgumentException("Purchase entry cannot be null");
        if (!entries.contains(entry)) entries.add(entry);
    }
}
