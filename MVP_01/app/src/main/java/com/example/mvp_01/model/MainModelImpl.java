package com.example.mvp_01.model;

import android.util.Log;

public class MainModelImpl implements MainModel {
    @Override
    public String getDataFromNet() {
        return "MVP模式，into fragment";
    }

    @Override
    public void stopRequest() {
        Log.i("MainModelImpl", "stop request...");
    }
}
