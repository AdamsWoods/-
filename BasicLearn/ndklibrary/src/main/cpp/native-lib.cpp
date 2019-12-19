//
// Created by WisdomZhang on 2019/11/26.
//

#include <jni.h>
#include <string>
#include "Facer.cpp"
#include <opencv2/imgproc/types_c.h>
#include <opencv2/core/mat.hpp>
#include "bitmap_utils.cpp"

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_ndklibrary_Main1Activity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_ndklibrary_Facer_getFacer(JNIEnv *env, jclass clazz, jstring top, jstring bottom,
                                           jstring brow, jstring eyes) {
    Facer facer(//使用 env->GetStringUTFChars将jstring转化为string
            env->GetStringUTFChars(top, 0),
            env->GetStringUTFChars(bottom, 0),
            env->GetStringUTFChars(brow, 0),
            env->GetStringUTFChars(eyes, 0)
    );

    return env->NewStringUTF(facer.getFace().c_str());
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_ndklibrary_Main1Activity_opBitmap(JNIEnv *env, jobject thiz, jobject bitmap,
                                                   jobject argb8888) {
    Mat srcMat;
    Mat dstMat;
    bitmap2Mat(env, bitmap, &srcMat);
    cvtColor(srcMat, dstMat, CV_BGR2GRAY);//将图片的像素信息灰度化盛放在dstMat
    return createBitmap(env,dstMat,argb8888);//使用dstMat创建一个Bitmap对象
}