package com.example.basiclearn.chapter10;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;

public class ServiceActivity extends AppCompatActivity {

    private Button start, stop;
    private Button bind, unbind, getServiceStatus;
    //保持所启动的Service的IBinder对象
    BindService.MyBinder binder;
    //定义一个ServiceConnection对象
    private ServiceConnection connection = new ServiceConnection() {
        //链接成功时回调该方法
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            System.out.println("===Service Connected===");
            binder = (BindService.MyBinder) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            System.out.println("===Service Disconnected===");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(listener);
        stop.setOnClickListener(listener);

        bind = findViewById(R.id.bind);
        unbind = findViewById(R.id.unbind);
        getServiceStatus = findViewById(R.id.status);

        bind.setOnClickListener(listener);
        unbind.setOnClickListener(listener);
        getServiceStatus.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ServiceActivity.this,com.example.basiclearn.chapter10.FristService.class);
            Intent intent1 = new Intent(ServiceActivity.this,com.example.basiclearn.chapter10.BindService.class);
//            intent.setAction("com.example.basiclearn.service.FRIST_SERVICE");
            switch (view.getId()) {
                case R.id.start:
                    startService(intent);
                    break;
                case R.id.stop:
                    stopService(intent);
                    break;
                case R.id.bind:
                    bindService(intent1,connection, BIND_AUTO_CREATE);
                    break;
                case R.id.unbind:
                    unbindService(connection);
                    break;
                case R.id.status:
                    Toast.makeText(ServiceActivity.this, "Service的ocunt值为：" +
                            binder.getCount(), Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
}
