package com.example.basiclearn;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRender implements GLSurfaceView.Renderer {

    private float[] triangleData = new float[] {
      0.1f,0.6f,0.0f,//上顶点
      -0.3f,0.0f,0.0f,//左顶点
      0.3f,0.1f,0.0f,//右顶点
    };
    int[] triangleColor = new int[]{
            65535,0,0,0,
            0,65535,0,0,
            0,0,65535,0
    };
    float[] reactData = new float[]{
        0.4f,0.4f,0.0f,
        0.4f,-0.4f,0.0f,
        -0.4f,0.4f,0.0f,
        -0.4f,-0.4f,0.0f
    };
    int[] reactColor = new int[]{
            0,65535,0,0,
            0,0,65535,0,
            65535,0,0,0,
            65535,65535,0,0,
    };
    float[] reactData2 = new float[]{
            -0.4f,-0.4f,0.0f,
            0.4f,0.4f,0.0f,
            0.4f,-0.4f,0.0f,
            -0.4f,0.4f,0.0f
    };
    float[] pentacle = new float[]{
            0.4f,0.4f,0.0f,
            -0.2f,0.3f,0.0f,
            0.5f,0.0f,0f,
            -0.4f,0.0f,0f,
            -0.1f,-0.3f,0f
    };

    FloatBuffer triangleDataBuffer;
    IntBuffer triangleColorBuffer;
    FloatBuffer rectDataBuffer;
    IntBuffer rectColorBuffer;
    FloatBuffer rectData2Buffer;
    FloatBuffer pentacleBuffer;

    private float rotate;

    public MyRender(){
        //将数据包装成buffer
        triangleDataBuffer = makeFloatBuffer(triangleData);
        triangleColorBuffer = makeIntBuffer(triangleColor);

        rectDataBuffer = makeFloatBuffer(reactData);
        rectColorBuffer = makeIntBuffer(reactColor);

        rectData2Buffer = makeFloatBuffer(reactData2);

        pentacleBuffer = makeFloatBuffer(pentacle);
    }

    //初始化
    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        //关闭抗抖动
        gl10.glDisable(GL10.GL_DITHER);
        //设置系统对透视进行修正
        gl10.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
        gl10.glClearColor(1,1,1,1);
        //设置阴影平滑模式
        gl10.glShadeModel(GL10.GL_SMOOTH);
        //启用深度测试
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        //设置深度测试的类型
        gl10.glDepthFunc(GL10.GL_LEQUAL);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int i, int i1) {
        //设置3d视窗大小和位置
        gl10.glViewport(0,0, i, i1);
        //将当前矩阵模式设为
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        //初始化单位矩阵
        gl10.glLoadIdentity();
        float ration = (float) i/i1;
        //设置透视窗的空间大小
        gl10.glFrustumf(-ration, ration, -1, 1,1,10);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        //清楚屏幕缓存和深度缓存
        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        //启动顶点坐标数据
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        //启用顶点颜色数据
        gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);
        //设置当前矩阵堆栈为模型堆栈
        gl10.glMatrixMode(GL10.GL_MODELVIEW);

//        -------------------绘制第一个图形
//        重置当前的模型视图矩阵
        gl10.glLoadIdentity();
        gl10.glTranslatef(-0.32f, 0.35f,-1f);
        //设置顶点的位置数据
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleDataBuffer);
        //设置顶点的颜色数据
        gl10.glColorPointer(4, GL10.GL_FIXED, 0, triangleColorBuffer);
        //根据顶点数据绘制平面图形
        gl10.glDrawArrays(GL10.GL_TRIANGLES, 0,3);

//        -------------------绘制第二个图形
        //重置当前的模型视图矩阵
        gl10.glLoadIdentity();
        gl10.glTranslatef(0.6f, 0.8f,-1.5f);
        gl10.glRotatef(rotate, 0f, 0f,0.1f);
        //设置顶点的位置数据
        gl10.glVertexPointer(3,GL10.GL_FLOAT, 0, rectDataBuffer);
        //设置顶点的颜色数据
        gl10.glColorPointer(4,GL10.GL_FIXED, 0, rectColorBuffer);
        //根据顶点数据绘制平面图形
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0,4);

        //-------------------绘制第三个图形
        //重置当前的模型视图矩阵
        gl10.glLoadIdentity();
        gl10.glTranslatef(-0.4f, -0.5f,-1.5f);
        gl10.glRotatef(rotate, 0f, 0.2f,0f);
        //设置顶点的位置数据
        gl10.glVertexPointer(3,GL10.GL_FLOAT, 0, rectData2Buffer);
        //设置顶点的颜色数据
//        gl10.glColorPointer(4,GL10.GL_FIXED, 0, rectColorBuffer);
        //根据顶点数据绘制平面图形
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0,4);

        //-------------------绘制第四个图形
        //重置当前的模型视图矩阵
        gl10.glLoadIdentity();
        gl10.glTranslatef(0.4f, -0.5f,-1.5f);
        //设置纯色填充
        gl10.glColor4f(1.0f,0.2f,0.2f,0.0f);
        gl10.glDisableClientState(GL10.GL_COLOR_ARRAY);
        //设置顶点的位置数据
        gl10.glVertexPointer(3,GL10.GL_FLOAT, 0, pentacleBuffer);
        //设置顶点的颜色数据
//        gl10.glColorPointer(4,GL10.GL_FIXED, 0, rectColorBuffer);
        //根据顶点数据绘制平面图形
        gl10.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0,5);

        //绘制结束
        gl10.glFinish();
        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);

        rotate += 1;
    }

    public IntBuffer makeIntBuffer(int[] arr){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(arr).position(0);
        return intBuffer;
    }

    public FloatBuffer makeFloatBuffer(float[] arr){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(arr.length * 4);
        byteBuffer.order(ByteOrder.nativeOrder());
        FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
        floatBuffer.put(arr).position(0);
        return floatBuffer;
    }
}
