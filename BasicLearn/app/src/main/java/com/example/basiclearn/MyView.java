package com.example.basiclearn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {

    Paint paint = new Paint();
    Path path;
    float phase;
    PathEffect[] pathEffect = new PathEffect[7];
    int[] colors;

    public MyView(Context context, AttributeSet set) {
        super(context, set);
        paint.setStyle(Paint.Style.STROKE);
        path = new Path();
        path.moveTo(0,0);
        for (int i= 1; i <= 15; i++) {
            path.lineTo(i * 20, (float) (Math.random()*60));
        }
        colors = new int[]{Color.BLUE, Color.BLACK, Color.GREEN};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);//

        pathEffect[0] = null;
        pathEffect[1] = new CornerPathEffect(10);
        pathEffect[2] = new DiscretePathEffect(3.0f, 2.0f);
        pathEffect[3] = new DashPathEffect(new float[]{20,10,10,10}, phase);
        Path p = new Path();
        pathEffect[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);
        pathEffect[5] = new ComposePathEffect(pathEffect[2], pathEffect[4]);
        pathEffect[6] = new SumPathEffect(pathEffect[4], pathEffect[3]);
        canvas.translate(8,8);
        for (int i = 0; i < pathEffect.length; i++){
            paint.setPathEffect(pathEffect[i]);
            canvas.drawPath(path, paint);
            canvas.translate(0, 60);
        }
        phase += 1;
        invalidate();

        paint.setAntiAlias(true);//
        canvas.drawCircle(30,18,120, paint);//圆形
        //圆角矩形
        RectF rect = new RectF(10, 200, 70, 230);
        canvas.drawRoundRect(rect, 130,140,paint);
        //设置渐变器
        Shader mShader = new LinearGradient(0,0,40,60,
                new int[] {
                        Color.RED, Color.GREEN,Color.BLUE
                },null,Shader.TileMode.REPEAT);
        paint.setShader(mShader);
        //设置阴影
        paint.setShadowLayer(45, 10,10,Color.GRAY);
        canvas.drawCircle(200,40,30,paint);
    }
}
