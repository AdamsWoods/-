package com.example.chapter8;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;

public class GestureTest extends AppCompatActivity implements GestureDetector.OnGestureListener {

    GestureDetector detector;
    ImageView imageView;
    Bitmap bitmap;
    int width,height;
    float currentScale = 1;
    Matrix matrix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_test);

        detector = new GestureDetector(this);
        imageView = findViewById(R.id.image);
        matrix = new Matrix();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avenda3);

        width = bitmap.getWidth();
        height = bitmap.getHeight();

        imageView.setImageBitmap(bitmap);
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
        //根据手势计算缩放比例
        currentScale += currentScale * v / 4000.0f;
        currentScale = currentScale > 0.01 ? currentScale : 0.01f;

        matrix.reset();
        matrix.setScale(currentScale, currentScale, 160, 200);

        BitmapDrawable tmp = (BitmapDrawable) imageView.getDrawable();

        if (!tmp.getBitmap().isRecycled()){
//            tmp.getBitmap().recycle();
        }
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0,0,width,height,matrix,true);
        imageView.setImageBitmap(bitmap1);
        return true;
    }
}
