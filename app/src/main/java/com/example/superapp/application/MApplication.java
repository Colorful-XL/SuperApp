package com.example.superapp.application;

import android.app.Application;

public class MApplication extends Application {
    private static MApplication mApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static MApplication getInstance(){
        return mApplication;
    }


}
