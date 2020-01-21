一、算法的基本能力

二、java语言能力，jvm的了解

三、Android核心

四、工具和技能

###  一、Service和BroadcastReceiver

####  1、Service

#### 2、AIDL服务（跨进程调用Service）

#### 3、接收广播消息

#### 4、接收系统广播消息

---

### 二、Android的网络应用



----

### 三、桌面管理

#### 1.改变手机壁纸

使用WallpaperManager改变手机壁纸，方法：

* `setBitmap(Bitmap bitmap)` 设置壁纸的位图
* `setResource(int resid)` 设置壁纸为resid的图片
* `setStrean(InputStream data)` 将壁纸设置为data数据所代表的图片

---

### 四、其他

#### 1、easypermission 权限管理

权限有几种分类， **normal** 、**dangerous**、**signature** 和 **signatureOrSystem**四个等级；

android6.0以后有些危险权限需要手动去授权，就有了运行时权限的处理；

一旦用户为应用授予某个权限，则所有后续对该权限组中权限的请求都将被自动批准。

###### 1、检测权限是否申请

```java
EasyPermissions.hasPermissions(Context context, @NonNull String… perms)
```

###### 2、申请权限

```java
EasyPermissions.requesPermissions(this, string, code, permissions)
```

* this: context

* string: 弹出提示语

* code: 请求请求码

* permissions: 权限数组

###### 3、请求权限的回调

```java
@Override
public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
	super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
}
```

###### 4、其他回调

申请成功回调 ```onPermissionGranted(int requestCode, List<String> list)```

失败回调 ```onPermissionsDenied(int requestCode, List<String> list)``` ，如果失败，使用如下

```java
@Override
public void onPermissionsDenied(int requestCode, List<String> perms) {
if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
    new AppSettingsDialog.Builder(this).build().show();
    //弹出个对话框
}
```

调用弹出权限详情对话框。

###### 5、注解**@AfterPermissionGranted()**

权限请求授权完成后，会自动调用此方法，进入成功授权里面的方法，省略成功的回调

```java
@AfterPermissionGranted(RC_CAMERA_AND_LOCATION)
private void methodRequiresTwoPermission() {
	String[] perms = {Manifest.permission.CAMERA, 		Manifest.permission.ACCESS_FINE_LOCATION};
	if (EasyPermissions.hasPermissions(this, perms)) {
    	// Already have permission, do the thing
    	// ...
	} else {
 	   // Do not have permissions, request them now
 	   EasyPermissions.requestPermissions(this, 	getString(R.string.camera_and_location_rationale),
            RC_CAMERA_AND_LOCATION, perms);
	}
}
```

#### 2、控件的结构

* 结构：Title + ContentView -> DecorView -> PhoneWindow -> Activity
  * DecorView在PhoneWindow中显示出来<font color=#991100>（在setContentView()后，ActivityManagerService会回调OnResume方法，此时系统才会将整个DecorView显示在PhoneWindow中）</font>。
  * DecorView显示页面内容，层级为：DecorView -> ViewGroup -> ActionBar + ContentView。
  * DecorView会接受到参数，来设置不同的规格布局。

#### 3、控件的测量

>  控件的测量即告诉系统有一个多大的View需要展示

控件的测量。如果需要自定义大小，则需要重写onMesure(int measureWidth, int measureHeight)方法。

测量使用的MeasureSpec类测量，使用的是32位的int值，前两位是模式，后30位是宽高

测量的模式有如下三种：

* Exactly：精确的模式，准确的给出控件的宽高
* At_Most：最大值模式，为WrapContent，该控件的大小随子控件变化。在不超过父控件的大小的情况下，允许的最大宽高。
* Unspecsified：模糊模式，要多大就多达

测量的步骤 ：

```java
//此方法若不重写，则默认填满父容器
@Override
protect void onMesure(int widthMeasureSpec, int heightMeasureSpec){
	super.onMesure();
    //设置宽高
	setMeasuredDimension(
		measureWidth(widthMeasureSpec),
        measureHeight(heightMeasureSpec)
	);
}

//获取宽度
private int measureWidth(int WidthMeasureSpec){
    int result = 0;
    int model = MeasureSpec.getModel(widthMeasureSpec);
    int size = MeasureSpec.getSize(widthMeasureSpec);
    
    if(model == MeasureSpec.EACACTLY){
        return size;
    } else {
        //默认值
        result = 200;
        //为wrap_content，
        if（model == MeasureSpec.AT_MOST){
            result = Math.min(result, size);
        }
    }
    return result;
}

//获取高度
private int measureHeight(int heightMeasureSpec){
    int result = 0;
    //模式
    int model = MeasureSpec.getModel(heightMeasureSpec);
    //大小
    int size = MeasureSpec.getSize(heightMeasureSpec);
    
    if(model == MeasureSpec.EACACTLY){
        return size;
    } else {
        //默认值
        result = 200;
        //为wrap_content，则需要选择一个最小
        if（model == MeasureSpec.AT_MOST){
            result = Math.min(result, size);
        }
    }
    return result;
}
```

对于ViewGroup的测量，则是ViewGroup遍历自己的所有子控件确定自己大小，调用子控件的onMeasure方法测量大小，然后调用子控件的Layout方法定位。onLayout方法，控制位置逻辑。

#### 4、控件的绘制

单个控件的绘制需要重写onDraw(Canvas canvas)方法。Canva是一个画布。

绘制一般通过new Canvas(bitmap)来生成Canvas，bitmap用来存储在Canvas上的绘制信息，canvas与bitmap紧密连接。然后使用mcanvas.drawXXXX();来绘制，绘制内容也都存放在bitmap中。通过改变bitmap，让view重绘，显示改变之后的bitmap。

ViewGroup通过dispatchDraw()方法绘制子view，同样是遍历。ViewGroup是一般不会调用onDraw方法，如果使用了背景色。

#### 5、Application使用

> Base class for maintaining global application state.
>
> Android维护全局应用状态的基类。
>
> 参考连接：[https://www.cnblogs.com/baiqiantao/p/9610018.html](https://www.cnblogs.com/baiqiantao/p/9610018.html)

可在AndroidManifest.xml的<application>标签里面的name属性指定，提供自己的application。该类或者其子类比其他任何类实例化。

application只会创建一个且仅有一个，全局唯一，生命周期最长。可用来进行数据传递、共享、缓存等操作。程序启动时，会自动启动创建一个application。

* 获取系统application

```java
Class<?> clazz = Class.forName("android.app.ActivityThread");
Field field = clazz.getDeclaredField("sCurrentActivityThread");
field.setAccessible(true);
//得到ActivityThread的对象，虽然是隐藏的，但已经指向了内存的堆地址
Object object = field.get(null);
Method method = clazz.getDeclaredMethod("getApplication");
method.setAccessible(true);
application = (Application) method.invoke(object);
```

* 生命周期
  * onCreate()：应用创建时调用，可获取application的单例。
  * onTerminate()：应用结束时调用，不一定被调用
  * onLowMemory()：内存不足时调用，
  * onConfiguration()：配置改变时调用，
  * onTrimMemory()：提示清除缓存，

* 注册全局的ActivityLifeCallBacks

  registerActivityLifeCallBacks和unregisterActivityCallBacks，对所有activity的监听。当activity的生命周期发生变化时，会使用。

* 数据共享全局共享
  * TAG，枚举类型
  * 共享数据变量，如：handler。但是app被结束掉，数据就消失，无法持久化保存。
  * 设置静态方法，全局使用。如：一键退出。

#### 6、LifeCyleObserver

> 生命周期

#### 7、keep注解



#### 8、万能ViewAdapter



#### 9、FlowableProcessor 解决Rxjava中的背压现象



#### 10、room数据库框架

>  参照链接：[https://juejin.im/post/5a4228036fb9a044ff31b8ca#heading-8](https://juejin.im/post/5a4228036fb9a044ff31b8ca#heading-8)

需要再build.gradle文件中添加 `google()`

添加如下依赖：

```groovy
implementation 'android.arch.persistence.room:runtime:1.0.0'
annotationProcessor 'android.arch.persistence.room:compiler:1.0.0'
//添加测试支持，我们可以对数据库进行androidTest（后面会介绍）
implementation 'android.arch.persistence.room:testing:1.0.0'
```

分为一下几个部分：

* Entity 

  运用注解@Entity(@tableName = "") @primaryKey(autoGenerate = true)

  ```java
  @Entity(tableName = "products")
  public class ProductEntity implements Product {    
      @PrimaryKey    private int id;    
      private String name;                                     }
  ```

* Dao

  ```java
  @Dao
  public interface ProductDao {
      @Query("SELECT * FROM products")
      LiveData<List<ProductEntity>> loadAllProducts();
  
      @Insert(onConflict = OnConflictStrategy.REPLACE)
      void insertAll(List<ProductEntity> products);
  
      @Query("select * from products where id = :productId")
      LiveData<ProductEntity> loadProduct(int productId);
  
      @Query("SELECT products.* FROM products JOIN productsFts ON (products.id = productsFts.rowid) "
          + "WHERE productsFts MATCH :query")
      LiveData<List<ProductEntity>> searchAllProducts(String query);
  }
  ```

  也可以使用事务

* AppDataBase

  生成数据库实例：

  ```
  RoomDemoDatabase database = Room.databaseBuilder(getApplicationContext(),
                  RoomDemoDatabase.class, "database_name")
                  .build();
  ```

  其他操作：

  ```Java
  Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
       .addCallback(new Callback() {	
           //第一次调用数据库时使用，创建所有表之后调用
           @Override
           public void onCreate(@NonNull SupportSQLiteDatabase db) {
                  super.onCreate(db);
           });
        }).addMigrations(MIGRATION_1_2) //数据库迁移
          .build();
  ```

* 类型转换 @TypeConverters

  ```Java
  public class Converters {
      @TypeConverter
      public static Date fromTimestamp(Long value) {
          return value == null ? null : new Date(value);
      }
  
      @TypeConverter
      public static Long dateToTimestamp(Date date) {
          return date == null ? null : date.getTime();
      }
  }
  ```

  在其他查询中，可以使用自己的定义类型，如下：

  ```java
  @Database(entities = {User.class}, version = 1)
  @TypeConverters({Converters.class})
  public abstract class AppDatabase extends RoomDatabase {
      public abstract UserDao userDao();
  }
  
  @Dao
  public interface UserDao {
      ...
      @Query("SELECT * FROM user WHERE birthday BETWEEN :from AND :to")
      List<User> findUsersBornBetweenDates(Date from, Date to);
  }
  ```

#### 11、Fragment生命周期

* 创建时，有如下过程：
  * onAttache
  * onCreate
  * onCreateView
  * onActivityCreated

* 可见时，有如下过程：
  * onStart
  * onResume
* 进入后台时，有如下过程：
  * onPause
  * onStop

* 被销毁时，有如下过程:
  * onPause
  * onStop
  * onDestroyView
  * onDestroy
  * onDetach

* 与Activity交互时，有这些过程：
  * onAttached--Fragment和activity关联时，调用这个方法
  * onCreateView---创建Fragment视图时
  * onActivityCreated—— 当activity的onCreate()方法被返回之后，调用这个方法。
  * onDestroyView--当fragment视图被移除时
  * onDetach--当activity与fragment方法分离时

#### 12、dispatchOnTouchEvent() 事件分发



#### 13、图片裁剪

* BitMap.createMap(bitmap, x, y, height, width, matrix, touch);



#### 14、屏幕适配

> 参考链接： [https://www.jianshu.com/p/ec5a1a30694b](https://www.jianshu.com/p/ec5a1a30694b)  

* 屏幕尺寸，分辨率，像素密度

```
>  [https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA](!https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA)  参考链接,今日头条
```

- px = density * dp;
- density = dpi / 160;
- px = dp * (dpi / 160);

运用今日头条的修改密度值如上链接；

采用smallestWidth方案：[https://github.com/ladingwu/dimens_sw#%E5%B8%8C%E6%9C%9B%E7%94%A8%E7%9C%9F%E6%9C%BA%E6%B5%8B%E8%AF%95%E7%9A%84%E5%8F%AF%E8%BF%90%E8%A1%8Capp%E9%A1%B9%E7%9B%AE%E7%9C%8B%E6%95%88%E6%9E%9C%E6%95%88%E6%9E%9C%E5%A6%82%E4%B8%8B%E5%9B%BE](https://github.com/ladingwu/dimens_sw#希望用真机测试的可运行app项目看效果效果如下图) 项目地址如上。

参考文章如下：[https://mp.weixin.qq.com/s/X-aL2vb4uEhqnLzU5wjc4Q](!https://mp.weixin.qq.com/s/X-aL2vb4uEhqnLzU5wjc4Q) 

#### 15、弱引用WeakReference

在java中所有非静态的对象都会持有当前类的强引用，而静态对象则只会持有当前类的弱引用。（弱引用容易回收gc）

- handler的机制

  当一个android主线程被创建的时候，同时会有一个Looper对象被创建，而这个Looper对象会实现一个MessageQueue(消息队列)，当我们创建一个handler对象时，而handler的作用就是放入和取出消息从这个消息队列中，每当我们通过handler将一个msg放入消息队列时，这个msg就会持有一个handler对象的引用。

  > new Handler -> new Looper -> new MessageQueue ->由handler存取msg

- handler 不是静态，造成的内存泄露

  Activity被结束后，这个msg在被取出来之前，这msg会继续存活，但是这个msg持有handler的引用，而handler在Activity中创建，会持有Activity的引用，因而当Activity结束后，Activity对象并不能够被gc回收，因而出现内存泄漏。

  根本原因：activity结束后，msg还未被处理，就一直有handler的引用，handler有activity的引用，无法销毁。

- **WeakReference的使用**

  `WeakReference<T> wrf = new WeakReference( t );`

#### 16、图片压缩，防止oom（基础，有待提高）

获取图片：

```java
//从res中获取，解析资源文件
public static Bitmap decodeResource(Resources res, int id) 
//从系统文件中解析
public static Bitmap decodeFile(String pathName)  
//从输入输出流中解析
public static Bitmap decodeStream(InputStream is)
```

生成目标图片：

```java
    public static Bitmap decodeBitmapById (@NonNull Resources res, int resId, int reqWidth, int reqHeight) {
        //1.生成一个options对象
        BitmapFactory.Options options = new BitmapFactory.Options();
        //2.设置为ture，则获取尺寸，不需要解析图
        options.inJustDecodeBounds = true;
        //3.获取原图尺寸到options
        calculateOptionsById(res, options, resId);
        //4.获取压缩大小，require大小为控件大小，inSampleSize为原图与目标图的比例相比
        options.inSampleSize = calculateInSamplesizeByOptions(options, reqWidth, reqHeight);
        //5.设置为false,会返回bitmap,ture不会返回
        options.inJustDecodeBounds = false;
        //6.获取压缩的bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(res, resId, options);
        return bitmap;
    }
```

#### 17、阻塞数组队列ArrayBlockingQueue

> 所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒。当队列满了，生产者会停顿等待消费者消费；当达到一定条件生产者继续生产。
>
> 队列使用的是一个数组，使用put，offer，take，poll，提供任务和获取任务

- 获取数据
  - poll(time)：在指定时间内取得数据，若没有取得，则返回失败；若成功取得，则返回成功。
  - take()：如果blokqueue为空，则等待线程挂起，直到不为空。
  - drainTo()：一次获取所有的数据对象，不许要分批加锁或释放锁。
- 放入数据
  - offer(anObject)：如果能够放入blockqueue即能够容纳，则返回true，否则返回false。
  - put(anObject)：如果anObject不能放入blockqueue，则当前的执行线程阻塞，等待blockqueue有空间再继续。

在创建ArrayBlockingQueue时，我们还可以控制对象的内部锁是否采用公平锁，默认采用非公平锁

#### 18、GradientDrawable 控件形状的修改

> GradientDrawable 是xml中的shape标签，可动态获取控件、修改控件形状的Java代码实现

- Java代码中动态修改

  ```java
  //获取控件的shape实例
  GradientDrawable backgound = (GradientDrawable)view.getBackground();
  //设置颜色
  background.setColor();
  //设置圆角
  background.setCornerRadius(20);
  //设置形状(椭圆形)
  background.setShape(GradientDrawable.OVAL);
  //给控件设置shape
  view.setBackgroundDrawable(background);
  ```

  - setShape(int shape); 参数可使用如下：
    - GradientDrawable.RECTANGLE: 矩形
    - GradientDrawable.OVLA: 椭圆形
    - GradientDrawable.LINE: 线
    - GradientDrawable.RING: 环形

- xml中修改

  设置控件的background的属性，@drawable/shape，对应的shape.xml中的内容为：

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
      <solid android:color="@color/colorAccent"/>
  </shape>
  ```

#### 19、StateListDrawable 控件的不同状态修改为不同的图片

> Drawable中的selector会被解析成StateListDrawable对象。该对象可放selctor中不同的状态，来修改控件的不同状态。也可直接手动写StateListDrawable，来修改状态，不需要selector.xml。

下面开始手动写一个：

```java
//初始化一个对象
StateListDrawable stateListDrawable = new StateListDrawable();
//获取属性值
int pressed = android.R.attr.state_Pressed;
int focused = android.R.attr.state_Focused;
//添加状态-pressed 为false, 第一个参数：状态；第二个参数：对应的图片
stateListDrawable.addState(new int[]{- pressed}, getResource().getDrawable(R.drawable.*));
stateListDrawable.addState(new int[]{pressed}, getResource().getDrawable(R.drawable.*));
stateListDrawable.addState(new int[]{- pressed}, getResource().getDrawable(R.drawable.*));
//没有状态时显示的图片
stateListDrawable.addState(new int[]{- pressed}, getResource().getDrawable(R.drawable.*));
```

使用xml的selector，可以达到同样效果，与GradientDrawable相同设置。

#### 20、Field类对象

> 描述的是类的属性信息，可获取当前对象的成员变量类型，对成员变量重新设值



#### 21、ARouter阿里的路由



#### 22、HyBrid App



#### 23、Ndk的基本使用

> 参考：[https://juejin.im/post/5d95a605e51d45783f5aa4cd](https://juejin.im/post/5d95a605e51d45783f5aa4cd) 使用
>
> [https://www.cnblogs.com/lsdb/p/9337285.html](https://www.cnblogs.com/lsdb/p/9337285.html) 项目中添加c++代码

* 创建NDK项目

* 向已经创建的项目中添加c++代码

  * 创建cpp文件夹，与Java同级别

  * cpp下创建native-lib.cpp文件，c++与Java的链接桥

    ```java
    #include <jni.h>
    #include <string>
    //#include "Facer.h"
    #include "Facer.cpp"
    
    extern "C" JNIEXPORT jstring
    
    JNICALL
    Java_com_example_apknameplugin_Main1Activity_stringFromJNI(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
    }
    
    extern "C"
    JNIEXPORT jstring JNICALL
    Java_com_example_apknameplugin_Facer_getFacer(
            JNIEnv *env, jclass clazz, jstring top,
            jstring bottom, jstring brow, jstring eyes) {
        Facer facer(//使用 env->GetStringUTFChars将jstring转化为string
                env->GetStringUTFChars(top, 0),
                env->GetStringUTFChars(bottom, 0),
                env->GetStringUTFChars(brow, 0),
                env->GetStringUTFChars(eyes, 0)
        );
    
        return env->NewStringUTF(facer.getFace().c_str());
    }
    ```

  * 创建CMakeLists.txt文件，与java同级别，c++的路径配置

    ```java
    cmake_minimum_required(VERSION 3.4.1)
    
    add_library( # Sets the name of the library.
                 native-lib
                 # Sets the library as a shared library.
                 SHARED
                 # Provides a relative path to your source file(s).
                 src/main/cpp/native-lib.cpp)
    find_library( # Sets the name of the path variable.
                  log-lib
                  # Specifies the name of the NDK library that
                  # you want CMake to locate.
                  log )
    target_link_libraries( # Specifies the target library.
                           native-lib
                           # Links the target library to the log library
                           # included in the NDK.
                           ${log-lib} )
    ```

  * Java代码中调用c++代码

    ```java
    static {//加载类库
                System.loadLibrary("native-lib");
    }
    public class Facer {
        public static native String getFacer( String top, String bottom, String brow, String eyes);
    }
    textView.setText(Facer.getFacer("-", "-", "~", "X"));
    textView1.setText(NativeHelper.stringFromJNI());
    ```

#### 24、数据懒加载

```java
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    protected void onVisible() {
        lazyLoad();
    }
    protected void onInvisible() {
    }
```

当Fragemtn可见时，才加载数据，不可见时不加载。

####  25、LeakCanary性能优化工具  Android Monitor + MAT



#### 26、 Navigation导航

> 参考链接：[https://www.jianshu.com/p/d37f5132db3c](https://www.jianshu.com/p/d37f5132db3c)  [https://www.jianshu.com/p/fa755a2df4ff](https://www.jianshu.com/p/fa755a2df4ff) 
>
> Navigation 是 JetPack 中的一个组件，用于方便的实现页面的导航（导航有一个起始地和目的地）
>
> 作为构建您的应用内界面的框架，重点是让`单 Activity 应用`成为首选架构。利用 Navigation 组件对 Fragment 的原生支持，您可以获得架构组件的所有好处（例如生命周期和 ViewModel），同时让此组件为您处理 FragmentTransaction 的复杂性。此外，Navigation组件还可以让您声明我们为您处理的转场。它可以自动构建正确的“向上”和“返回”行为，包含对深层链接的完整支持，并提供了帮助程序，用于将导航关联到合适的 UI 小部件，例如抽屉式导航栏和底部导航。

添加依赖：

```groovy
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0-alpha01"
    }
}

def nav_version = "1.0.0-alpha05"
implementation "android.arch.navigation:navigation-fragment:$nav_version" // use -ktx for Kotlin
implementation "android.arch.navigation:navigation-ui:$nav_version" // use -ktx for Kotlin
// optional - Test helpers
androidTestImplementation "android.arch.navigation:navigation-testing:$nav_version" // use -ktx for Kotlin
```

###### 1、添加res/navigation/nav_gragh文件

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@id/nav_graph_first_fragment">
    <fragment
        android:id="@+id/nav_graph_first_fragment"
        android:name="pot.ner347.androiddemo.navigation.FirstFragment"
        android:label="first"
        tools:layout="@layout/fragment_first"/>
</navigation>
```

###### 2、创建Fragment，start，destination

1.添加依赖

2.创建res/navigation/nav_gragh文件，进入design模式

3.添加fragment，在此可创建。同时nav_gragh中会自动添加一个fragment节点。也可以将fragment分组，从而嵌套。

4.开始路由，在design模式中连线，设定路由。同时，可以添加路由参数传递。

5.在fragment类中，添加点击跳转的路由事件。Navigation.findColltroler(View view).navigate(int resid)。同时，可以通过此方法的重载方法传递参数。也可以使用类名+Directions.action..().set**()

6.将nav_gragh与activity相连接起来，就可完成fragment的路由。

#### 27、时间的转换

#### 28、EditText取消焦点

```xml
android:focusable="true"
android:focusableInTouchMode="true"
```

#### 29、sqlit需要转义

这些字符有 **| , + , \* , ^ , $ , / , | , [ , ] , ( , ) , - , . , \**等, 因它们是正则表达式中的一部分, 所以如果想用该字符本身, 这些字符需要进行转义才能表示它本身；

```
String[] b = str.split("\\|");  *//注意这里用两个 \\，而不是一个\*  
System.out.println("处理结果: "+b[0]+","+b[1]);   *//输出的是: 处理结果: 5678,XYZ* 
```








```sequence

alice -> bob:hello
```









##### 学习为的是什么？

全面的知识？工作？技能？新的技术？

我需要一份薪酬很高的工作，那么我应该怎么去涨工资？

##### 学习任务：

* GitHub学习资料
* developers实例学习
* 管网项目的模仿学习

