package com.example.basiclearn;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.View;

public class MyMatrixView extends View {

    private Bitmap bitmap;
    private Matrix matrix = new Matrix();
    private float sx = 0.0f;
    private int width,height;
    private float scale = 1.0f;
    private boolean isScale = false;

    public MyMatrixView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.avenda1);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        this.setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        matrix.reset();
        if (isScale){
            //缩放
            matrix.setScale(scale, scale);
        } else {
            //旋转
            matrix.setSkew(sx, 0);
        }
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0,0,width,height,matrix,true);
        canvas.drawBitmap(bitmap2,matrix,null);
    }
}
