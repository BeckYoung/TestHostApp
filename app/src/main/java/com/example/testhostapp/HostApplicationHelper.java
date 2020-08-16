package com.example.testhostapp;

import android.util.Log;

public class HostApplicationHelper {
    private static final String TAG = HostApplicationHelper.class.getSimpleName();
    private static volatile HostApplicationHelper helper = null;
    private HostApplication application;

    public static HostApplicationHelper getInstance() {
        if (helper == null) {
            synchronized (HostApplicationHelper.class) {
                if (helper == null) {
                    helper = new HostApplicationHelper();
                }
            }
        }
        return helper;
    }

    private HostApplicationHelper() {
        Log.i(TAG, "HostApplicationHelper create");
    }

    public void init(HostApplication application) {
        this.application = application;
    }

    public HostApplication getApplication() {
        return application;
    }
}
