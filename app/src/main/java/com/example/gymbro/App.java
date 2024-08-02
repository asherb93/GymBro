package com.example.gymbro;

import android.app.Application;

import com.example.gymbro.Utils.ImageLoader;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SignalManager.init(this);
        ImageLoader.initImageLoader(this);
        SharedPreferencesManager.init(this);
    }
}
