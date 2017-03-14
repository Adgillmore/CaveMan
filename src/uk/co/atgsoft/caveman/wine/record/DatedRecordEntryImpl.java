/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import java.time.LocalDate;
import uk.co.atgsoft.caveman.wine.BottleSize;
import uk.co.atgsoft.caveman.wine.Wine;

/**
 *
 * @author adam
 */
public class DatedRecordEntryImpl extends BaseRecordEntryImpl implements DatedRecordEntry {
    
    private LocalDate mDate;

    public DatedRecordEntryImpl(final String id, final Wine wine, final int quantity, 
            final BottleSize size, final LocalDate date) {
        super(id, wine, quantity, size);
        if (date == null) throw new IllegalArgumentException("Date cannot be null");
        mDate = date;
    }
    
    @Override
    public void setDate(final LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        mDate = date;
    }

    @Override
    public LocalDate getDate() {
        return mDate;
    }
}
