package me.stevensheaves.data.utils;

import java.util.Locale;

/**
 * Utility class for getting and storing system location details.
 */
public class LocationData {
    private static final Locale locale =  Locale.getDefault();
    private static final String systemLanguage = locale.getLanguage();
    private static final String displayCountry = locale.getDisplayCountry();


    /**
     * Getter for <code>systemLanguage</code> field.
     * @return Returns the String value from the <code>systemLanguage</code>
     */
    public static String getSystemLanguage() {
        return systemLanguage;
    }


    /**
     * Getter for <code>displayCountry</code> field.
     * @return Returns the String value from the <code>displayCountry</code>
     */
    public static String getDisplayCountry() {
        return displayCountry;
    }
}
