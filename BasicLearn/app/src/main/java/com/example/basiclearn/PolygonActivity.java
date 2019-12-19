package com.example.basiclearn;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class PolygonActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_polygon);
        GLSurfaceView glSurfaceView = new GLSurfaceView(this);
        MyRender2 myRender = new MyRender2();
        glSurfaceView.setRenderer(myRender);
        setContentView(glSurfaceView);
    }
}
