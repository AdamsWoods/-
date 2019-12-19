package com.example.basiclearn;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class ButterFlyActivity extends AppCompatActivity {

    private float curx = 0;
    private float cury = 30;

    private float nextx;
    private float nexty;

    private SurfaceHolder holder;
    private Paint paint;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_fly);

        ListView listView = findViewById(R.id.list);
        WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getDisplayId();
        int screenHeight = display.getDisplayId();
        listView.setAnimation(new MyAnimation(screenWidth/2,screenHeight/2,3500));

        paint = new Paint();
        SurfaceView view = findViewById(R.id.show);
        holder = view.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                //锁定整个surfaceview
                Canvas canvas = surfaceHolder.lockCanvas();
                //绘制背景
                Bitmap back = BitmapFactory.decodeResource(ButterFlyActivity.this.getResources(),R.drawable.image4);
                canvas.drawBitmap(back, 0, 0, null);
                //绘制完成，释放画布，提交修改
                holder.unlockCanvasAndPost(canvas);
                //重新锁一次，避免下次lockCanvas遮挡
                holder.lockCanvas(new Rect(0,0,0,0));
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();
                    //锁定surfaceview的局部区域，只更新局部内容
                    Canvas canvas = holder.lockCanvas(new Rect(x - 50,
                            y - 50, x + 50, y + 50));
                    canvas.save();
                    canvas.rotate(30,x,y);
                    paint.setColor(Color.RED);
                    //绘制红色方块
                    canvas.drawRect(x,y,x+40,y+40,paint);
                    canvas.restore();
                    paint.setColor(Color.BLUE);
                    canvas.drawRect(x-40,y-40,x,y,paint);
                    //绘制完成，释放画布，提交修改
                    holder.unlockCanvasAndPost(canvas);
                }
                return false;
            }
        });

        final ImageView imageView = findViewById(R.id.image);
        final Handler handler = new Handler(){
            public void handleMessage(@NonNull Message message) {
                if (message.what == 0x123){
                    if (nextx > 600)
                        curx = nextx = 0;
                    else
                        nextx += 8;
                    nexty = cury + (float)(Math.random() * 10 - 5);
                    TranslateAnimation animation = new TranslateAnimation(
                            curx,nextx, cury, nexty
                    );
                    curx = nextx;
                    cury = nexty;
                    animation.setDuration(200);
                    imageView.startAnimation(animation);
                }
            }
        };
        final AnimationDrawable animationDrawable = (AnimationDrawable)
                imageView.getBackground();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animationDrawable.start();
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(0x123);
                    }
                }, 0,100);
            }
        });
    }
}
