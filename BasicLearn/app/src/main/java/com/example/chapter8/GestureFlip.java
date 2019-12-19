package com.example.chapter8;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basiclearn.R;

public class GestureFlip extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ViewFlipper filpper;
    GestureDetector gestureDetector;
    final int FILP_DISTENCE = 50;
    Animation[] animations = new Animation[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_flip);

        filpper = findViewById(R.id.flipper);
        gestureDetector = new GestureDetector(this);

        filpper.addView(addView(R.drawable.avenda3));
        filpper.addView(addView(R.drawable.avenda2));
        filpper.addView(addView(R.drawable.avenda1));
        filpper.addView(addView(R.drawable.image3));

        animations[0] = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        animations[1] = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
        animations[2] = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        animations[3] = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);
    }

    private View addView(int id) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return  imageView;
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
        if (motionEvent.getX() - motionEvent1.getX() > FILP_DISTENCE){
            filpper.setInAnimation(animations[0]);
            filpper.setOutAnimation(animations[1]);
            filpper.showPrevious();
            return true;
        } else if (motionEvent1.getX() - motionEvent.getX() > FILP_DISTENCE){
            filpper.setInAnimation(animations[2]);
            filpper.setOutAnimation(animations[3]);
            filpper.showNext();
            return  true;
        }
        return false;
    }
}
