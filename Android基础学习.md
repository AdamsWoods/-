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



##### 学习为的是什么？

全面的知识？工作？技能？新的技术？

我需要一份薪酬很高的工作，那么我应该怎么去涨工资？

##### 学习任务：

* GitHub学习资料
* developers实例学习
* 管网项目的模仿学习