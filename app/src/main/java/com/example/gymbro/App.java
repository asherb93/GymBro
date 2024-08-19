package com.example.gymbro;

import android.app.Application;

import com.example.gymbro.Utils.FirebaseManager;
import com.example.gymbro.Utils.GsonUtils;
import com.example.gymbro.Utils.ImageLoader;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseManager.initFirebaseManager(this);
        SignalManager.init(this);
        ImageLoader.initImageLoader(this);
        SharedPreferencesManager.init(this);
        GsonUtils.getInstance();
    }
}
