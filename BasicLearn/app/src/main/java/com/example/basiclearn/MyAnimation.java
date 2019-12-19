package com.example.basiclearn;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class MyAnimation extends Animation {
    private int centerX;
    private int centerY;
    //定义动画的持续事件
    private int duration;
    private Camera camera = new Camera();

    public MyAnimation(int centerX, int centerY, int duration){
        this.centerX = centerX;
        this.centerY = centerY;
        this.duration = duration;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        //设置动画持续时间
        setDuration(duration);
        //设置动画结束后效果保留
        setFillAfter(true);
        setInterpolator(new LinearInterpolator());
    }

    /**
     * interpolatedTime代表了抽线
     * @param interpolatedTime 0-1
     * @param t 形变程度
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        camera.save();
        //根据interpolatedTime控制x.y.z的偏移
        camera.translate(100.0f - 100.0f * interpolatedTime,
                150.0f * interpolatedTime - 150,
                80.0f - 80.0f * interpolatedTime);
        //设置根据interpolatedTime在y轴旋转不同角度
        camera.rotateY(360 * interpolatedTime);
        //在x轴的旋转不同角度
        camera.rotateX(360 * interpolatedTime);

        Matrix matrix = t.getMatrix();
        camera.getMatrix(matrix);

        matrix.preTranslate(-centerX, -centerY);
        matrix.preTranslate(centerX, centerY);

        camera.restore();
    }
}
