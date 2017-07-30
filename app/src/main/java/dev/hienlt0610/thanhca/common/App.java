package dev.hienlt0610.thanhca.common;

import android.app.Application;
import android.content.Context;

import timber.log.Timber;

/**
 * Created by hienl on 7/29/2017.
 */

public class App extends Application {
    private static App mInstance;
    private static Context mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mAppContext = getApplicationContext();
        Timber.plant(new Timber.DebugTree());
    }

    public static App getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        return mAppContext;
    }
}
