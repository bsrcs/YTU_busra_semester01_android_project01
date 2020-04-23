package com.busra.testapp;

import android.content.Context;
import android.content.SharedPreferences;

public  class LoginHelper {
    private static SharedPreferences mSharedPreferences;
    public static final String PREFERENCE = "preference";
    public static final String KEY_Name = "name";
    public static final String KEY_UNAME = "uname";
    public static final String KEY_PASSWD = "passwd";
    public static final String PREF_OLD_USER = "false";

    public static void registerUser(Context context, String Name, String UserName, String Password) {
        mSharedPreferences =  context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(KEY_Name, Name);
        mEditor.putString(KEY_UNAME, UserName);
        mEditor.putString(KEY_PASSWD, Password);
        mEditor.putBoolean(PREF_OLD_USER, true);
        mEditor.apply();
    }

    public static Boolean performLogin(Context context, String UserName, String Password) {
        mSharedPreferences = mSharedPreferences = context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if (UserName.equals(mSharedPreferences.getString(KEY_UNAME, null)) && Password.equals(mSharedPreferences.getString(KEY_PASSWD, null))) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean isNewUser(Context context) {
        mSharedPreferences =  context.getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        if (mSharedPreferences.getBoolean(PREF_OLD_USER, false) == false)// user is already registered
        {
            return true;
        }
        else {
            return false;
        }
    }


    public static boolean validUserData(Context context,String UserName,String Password) {
        return !(UserName.isEmpty() || Password.isEmpty());
    }
}
