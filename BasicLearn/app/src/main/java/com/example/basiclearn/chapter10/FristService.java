package com.example.basiclearn.chapter10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class FristService extends Service {
    /*
    must be override
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("=====service is created====");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("=====service is started====");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("=====service is destroyed====");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("=====service is unbind====");
        return super.onUnbind(intent);
    }
}
