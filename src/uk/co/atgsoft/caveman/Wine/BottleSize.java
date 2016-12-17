/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.Wine;

/**
 *
 * @author adam
 */
public enum BottleSize {
    
    HALF(0.5f),
    
    BOTTLE(1),
    
    MAGNUM(2),
    
    JEROBOAM4(4),
    
    JEROBOAM6(6),
    
    METHUSELAH(8),
    
    BALTHAZAR(16),
    
    NEBUCHADNEZZAR(20),
    
    MELCHIOR(24),
    
    MIDAS(40);
    
    private float mMultiplier;

    private BottleSize(final float multiplier) {
        mMultiplier = multiplier;
    }
    
    public float getMultiplier() {
        return mMultiplier;
    }
    
    
}
