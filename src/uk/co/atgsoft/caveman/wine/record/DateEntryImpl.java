/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine.record;

import java.time.LocalDate;

/**
 *
 * @author adam
 */
public class DateEntryImpl implements DateEntry {
    
    private LocalDate mDate;

    public DateEntryImpl(final LocalDate date) {
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
