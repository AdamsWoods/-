#指定 cmake 的最小版本
cmake_minimum_required(VERSION 3.4.1)

file(GLOB native_srcs "${CMAKE_SOURCE_DIR}src/main/cpp/*.cpp")

# 使用native-lib.cpp文件生成共享库native-lib
add_library( # Sets the name of the library.
             native-lib
             # Sets the library as a shared library.
             SHARED
             # Provides a relative path to your source file(s).
             src/main/cpp/native-lib.cpp)

#########################################################

include_directories(${CMAKE_SOURCE_DIR}/src/main/cpp/include)#引入opencv文件夹

##########################添加动态链接库###########################

#导入opencv_java4 动态库
add_library(libopencv_java4 SHARED IMPORTED)
set_target_properties(libopencv_java4
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_SOURCE_DIR}/libs/${ANDROID_ABI}/libopencv_java4.so)

#######################################################################

# 在ndk中查找log库 取别名log-lib
find_library( # Sets the name of the path variable.
              log-lib
              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

#将第三方库以及自己写的库关联到需要用的NDK原生库中
target_link_libraries( # Specifies the target library.
                       native-lib
                       libopencv_java4
                       jnigraphics # jnigraphics包含图形操作的库
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )