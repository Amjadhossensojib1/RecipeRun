package com.dalazu.reciperun;

import android.app.Application;

import com.onesignal.Continue;
import com.onesignal.OneSignal;
import com.onesignal.debug.LogLevel;

public class MyApplication extends Application {

    private static final String ONESIGNAL_APP_ID ="e96d8841-8658-4ca6-b8d6-8942bf7f8fec";

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.getDebug().setLogLevel(LogLevel.VERBOSE);
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID);
       OneSignal.getNotifications().requestPermission(false, Continue.none());

    }
}
