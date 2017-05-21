/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.atgsoft.caveman.wine;

/**
 *
 * @author adam.gillmore
 */
public final class WineUtils {

    private static final String DELIMITER = ":";
    /**
     * Private constructor.
     */
    private WineUtils() {
    }
    
    public static String createWineId(final String name, final String producer, final String vintage) {
        if (!validString(name) || !validString(producer) || !validString(vintage)) {
            throw new IllegalArgumentException("invalid id");
        }
        return concatId(name, producer, vintage);
    }
    
    private static String concatId(final String name, final String producer, final String vintage) {
        final StringBuilder sb = new StringBuilder();
        sb.append(name)
                .append(DELIMITER)
                .append(producer)
                .append(DELIMITER)
                .append(vintage);
        return sb.toString();
    }
    
    private static boolean validString(final String s) {
        return s != null && !s.isEmpty();
    }
    
    public static boolean validVintage(final int vintage) {
        return vintage < 1800 || vintage > 2200;
    }
}
