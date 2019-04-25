package com.example.vaibh.gobiz.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsUtil {

    private static SharedPrefsUtil instance;

    private SharedPreferences sharedPreferences;

    private SharedPrefsUtil(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPrefsUtil getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefsUtil(context);
        }
        return instance;
    }

    public void setString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key, String defaultValue) {
        String res = sharedPreferences.getString(key, defaultValue);
        return res;
    }

}
