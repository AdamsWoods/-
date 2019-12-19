// ICat.aidl
package com.example.basiclearn.chapter10;

// Declare any non-default types here with import statements

interface ICat {
    /**
    * m描述一些基础的基础数据类型，可以作为参数，返回数据类型
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String getColor();
    double getWeight();

}
