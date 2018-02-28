package com.pwc.newfind.base;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lhuang126 on 2/28/2018.
 */

public class UserHelper {
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";

    public static String getUserPhone() {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PHONE, "");
    }

    public static void setUserPhone(String phone) {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PHONE, phone);
        editor.apply();
    }

    public static String getUserEmail() {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        return sharedPreferences.getString(EMAIL, "");
    }

    public static void setUserEmail(String email) {
        SharedPreferences sharedPreferences = Application.getInstances().getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EMAIL, email);
        editor.apply();
    }

    public static void logout() {
        Application.getInstances().setUserToken("");
        setUserPhone("");
        setUserEmail("");
        Application.getInstances().getTempUserToken();
    }
}
