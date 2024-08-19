package com.example.gymbro.Utils;

import android.content.Context;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {

    private static volatile GsonUtils instance;
    private final Gson gson = new Gson();
    private static Context context;

    // init
    private GsonUtils(Context context) {
        this.context = context;
    }

    public static GsonUtils getInstance() {
        return instance;
    }

    // Public method to get the singleton instance
    public synchronized GsonUtils initGsonUtils(Context context) {
        if (instance == null) {
            instance = new GsonUtils(context);
        }
        return instance;
    }

    // Generic method to convert an object to JSON
    public <T> String toJson(T object) {
        return gson.toJson(object);
    }

    // Generic method to convert JSON to an object
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    // Generic method to convert JSON to an object with TypeToken
    public <T> T fromJson(String json, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        return gson.fromJson(json, type);
    }
}