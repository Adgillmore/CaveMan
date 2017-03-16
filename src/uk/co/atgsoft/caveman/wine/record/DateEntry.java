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
public interface DateEntry {
    
    void setDate(LocalDate date);
    
    LocalDate getDate();
}
