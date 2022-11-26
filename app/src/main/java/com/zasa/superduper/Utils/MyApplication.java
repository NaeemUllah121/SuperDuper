package com.zasa.superduper.Utils;


import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    public static Context context;
    @Override

    public void onCreate() {
        super.onCreate();
        context=this;
        new PrefsManager();

    }
}

