package com.bakarvin.klinikhp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Preferences {

    static final String KODE_USER = "kodeUser";
    static final String UNAME_USER = "unameUser";
    static final String USER_LOGIN = "userLogin";
    static final String CHECK_STATUS_LOGIN = "checkLogin";

    private static SharedPreferences getSharedPref(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    //
    public static void setLoginKode(Context context, String kodeUser){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(KODE_USER, kodeUser);
        editor.apply();
    }

    public static String getLoginKode(Context context){
        return  getSharedPref(context).getString(KODE_USER, "");
    }

    public static void setLoginUname(Context context, String pass){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(UNAME_USER, pass);
        editor.apply();
    }

    public static String getLoginUname(Context context){
        return  getSharedPref(context).getString(UNAME_USER, "");
    }


    public static void setUserLogin(Context context, String username){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putString(USER_LOGIN, username);
        editor.apply();
    }

    public static String getUserLogin(Context context){
        return  getSharedPref(context).getString(USER_LOGIN, "");
    }


    public static void setLoginStatus(Context context, boolean status){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.putBoolean(CHECK_STATUS_LOGIN, status);
        editor.apply();
    }

    public static boolean getLoginStatus(Context context){
        return  getSharedPref(context).getBoolean(CHECK_STATUS_LOGIN, false);
    }

    public static void clearLoginUser (Context context){
        SharedPreferences.Editor editor = getSharedPref(context).edit();
        editor.remove(USER_LOGIN);
        editor.remove(UNAME_USER);
        editor.remove(CHECK_STATUS_LOGIN);
        editor.remove(KODE_USER);
        editor.apply();
    }
}
