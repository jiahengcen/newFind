package com.pwc.newfind;

import android.content.Context;

/**
 * Created by lhuang126 on 1/13/2018.
 */

public class Application extends android.app.Application {
    public static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
