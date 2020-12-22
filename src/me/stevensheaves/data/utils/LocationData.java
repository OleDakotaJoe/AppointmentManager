package me.stevensheaves.data.utils;

import java.util.Locale;

public class LocationData {
    private static final String systemLanguage;
    private static final String systemCountry;
    private static final String displayLanguage;
    private static final String displayCountry;

     static {
        Locale locale = Locale.getDefault();
        systemCountry = locale.getCountry();
        systemLanguage = locale.getLanguage();
        displayCountry = locale.getDisplayCountry();
        displayLanguage = locale.getDisplayLanguage();
    }

    public static String getSystemLanguage() {
        return systemLanguage;
    }

    public static String getSystemCountry() {
        return systemCountry;
    }

    public static String getDisplayLanguage() {
        return displayLanguage;
    }

    public static String getDisplayCountry() {
        return displayCountry;
    }
}
