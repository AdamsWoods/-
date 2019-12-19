package com.example.basiclearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MoveBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));
    }

    class MyView extends View {

        final int BACK_HEIGHT = 1700;
        Bitmap back;
        Bitmap plane;
        int WIDTH = 220;
        int HEIGHT = 300;
        int startY = BACK_HEIGHT - HEIGHT;

        public MyView(Context context) {
            super(context);
            back = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
            plane = BitmapFactory.decodeResource(getResources(),R.drawable.aa);

            final Handler handler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 0x123){
                        //重新开始移动
                        if (startY <= 0){
                            startY = BACK_HEIGHT - HEIGHT;
                        } else {
                            startY -= 3;
                        }
                    }
                    invalidate();
                }
            };

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0x123);
                }
            }, 3,100);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap bitmap2 = Bitmap.createBitmap(back, 0, startY, WIDTH, HEIGHT);
            canvas.drawBitmap(bitmap2,0,0,null);
            canvas.drawBitmap(plane,160,380,null);
        }
    }
}
