package com.sample.openweather.utils;

import android.content.SharedPreferences;

/**
 * Created by venugopalraog on 10/16/17.
 */

public class SharedPreferencesUtil {

    private static final String LAST_SEARCHED_CITY_NAME = "LastSearchedCityName";

    private final SharedPreferences preferences;

    public SharedPreferencesUtil(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public boolean saveLastSearchedCityName(String cityName) {
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putString(LAST_SEARCHED_CITY_NAME, cityName);
        return prefsEditor.commit();
    }

    public String getLastSearchedCityName() {
        return preferences.getString(LAST_SEARCHED_CITY_NAME, Constants.DEFAULT_CITY);
    }
}
