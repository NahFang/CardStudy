package com.nahfang.studycard;

import android.app.Application;
import android.content.Context;

public class application extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
