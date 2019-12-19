package com.example.basiclearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PinBallActivity extends AppCompatActivity {

    //桌面宽度
    private int tableWidth;
    //桌面高度
    private int tableHeight;
    //球拍的垂直位置
    private int racketY;
    //定义球拍的高度和宽度
    private final int RACKET_HEITHT = 20;
    private final int RACKET_WIDTH = 70;
    //小球的大小
    private final int BALL_SIZE = 12;
    //小球的纵向运行速度
    private int ySpeed = 10;
    Random random = new Random();
    //返回一个-0.5-0.5的比率，用于控制小球的运行方向
    private double xyRate = random.nextDouble() - 0.5;
    //小球的横向速度
    private int xSpeed = (int) (ySpeed * xyRate * 2);
    //小球的坐标（ballx, bally）
    private int ballX = random.nextInt(200) + 200;
    private int ballY = random.nextInt(10) + 200;
    //racketX代表球拍的水平位置
    private int racketX = random.nextInt(200);
    //游戏是否结束标志
    private boolean isLose = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉窗口标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
//        setContentView(R.layout.activity_pin_ball);
        //创建planeview组件
        final GameView gameView = new GameView(this);
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.addView(gameView);
        setContentView(gameView);
        //获取窗口管理器
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        tableHeight = display.getHeight();
        tableWidth = display.getWidth();
        racketY = tableHeight - 80;

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == 0x123){
                    gameView.invalidate();
                }
            }
        };
        gameView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.KEYCODE_DPAD_LEFT:
                        if (racketX > 0)
                            racketX -= 10;
                        break;
                    case KeyEvent.KEYCODE_DPAD_RIGHT:
                        if (racketX < tableWidth - RACKET_WIDTH)
                            racketX += 10;
                        break;
                }
                gameView.invalidate();
                return true;
            }
        });
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //碰到左边
                if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE)
                    xSpeed = (-xSpeed);
                //高度超过球拍，横向不在球拍上
                if (ballY >= racketY - BALL_SIZE &&
                        (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
                    timer.cancel();
                    isLose = true;
                } else if (ballY <= 0 || (ballY >= racketY - BALL_SIZE &&
                        ballX <= racketX + RACKET_WIDTH)){
                    ySpeed = -ySpeed;
                }
                ballY += ySpeed;
                ballX += xSpeed;
                handler.sendEmptyMessage(0x123);
            }
        },0,100);
    }

    public class GameView extends View {

        public GameView(Context context) {
            super(context);
            setFocusable(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            if (isLose) {
                paint.setColor(Color.RED);
                paint.setTextSize(40);
                canvas.drawText("游戏结束",50,200,paint);
            } else {
                paint.setColor(Color.rgb(240,240,80));
                canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
                paint.setColor(Color.rgb(80,80,200));
                canvas.drawRect(racketX,racketY,racketX + RACKET_WIDTH,
                        racketY+RACKET_HEITHT, paint);
            }
        }
    }

}
