package com.example.basiclearn;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class Texture3DActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private float anglex = 0f;
    private float angley = 0f;
    static final float ROTATE_FACTOR = 60;
    //定义手势检测器实例
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        MyRender2 myRender = new MyRender2();
        glSurfaceView.setRenderer(myRender);
        setContentView(glSurfaceView);

        detector = new GestureDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        v = v > 4000 ? 4000 : v;
        v = v < -4000 ? -4000 : v;
        v1 = v1 > 4000 ? 4000 : v1;
        v1 = v1 < -4000 ? 4000 : v1;
        //根据横向上的速度计算Y轴旋转的角度
        angley += v * ROTATE_FACTOR / 4000;
        //根据纵向上的速度沿x轴旋转的角度
        anglex += v1 * ROTATE_FACTOR / 4000;
        return true;
    }
}
