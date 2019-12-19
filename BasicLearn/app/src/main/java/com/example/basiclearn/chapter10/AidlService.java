package com.example.basiclearn.chapter10;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Timer;

public class AidlService extends Service {

//    private CatBinder catBinder;
    Timer timer = new Timer();
    String[] colors = new String[] {
        "红色","黄色","黑色"
    };
    private String color;
    private double weight;
    //继承Stub，继承stub，实现icat接口，并是心啊IBinder接口
//    public class CatBinder extends ICat.Stub {
//
//
//        @Override
//        public void send(int i, Bundle bundle) throws RemoteException {
//
//        }
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
