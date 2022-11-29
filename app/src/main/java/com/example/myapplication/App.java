package com.example.myapplication;

import android.app.Application;
import android.util.Log;

public class App extends Application {
    private static final String TAG="test";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate:App ");


    }
}

