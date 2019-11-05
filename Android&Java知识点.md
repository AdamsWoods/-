##### 1、弱引用WeakReference

在java中所有非静态的对象都会持有当前类的强引用，而静态对象则只会持有当前类的弱引用。（弱引用容易回收gc）

* handler的机制

  当一个android主线程被创建的时候，同时会有一个Looper对象被创建，而这个Looper对象会实现一个MessageQueue(消息队列)，当我们创建一个handler对象时，而handler的作用就是放入和取出消息从这个消息队列中，每当我们通过handler将一个msg放入消息队列时，这个msg就会持有一个handler对象的引用。

  > new Handler -> new Looper -> new MessageQueue ->由handler存取msg

* handler 不是静态，造成的内存泄露

  Activity被结束后，这个msg在被取出来之前，这msg会继续存活，但是这个msg持有handler的引用，而handler在Activity中创建，会持有Activity的引用，因而当Activity结束后，Activity对象并不能够被gc回收，因而出现内存泄漏。

  根本原因：activity结束后，msg还未被处理，就一直有handler的引用，handler有activity的引用，无法销毁。

* **WeakReference的使用**

  `WeakReference<T> wrf = new WeakReference( t );`

***

##### 2、图片压缩，防止oom（基础，有待提高）

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

***

##### 3、阻塞数组队列ArrayBlockingQueue

> 所谓阻塞，在某些情况下会挂起线程（即阻塞），一旦条件满足，被挂起的线程又会自动被唤醒。当队列满了，生产者会停顿等待消费者消费；当达到一定条件生产者继续生产。
>
> 队列使用的是一个数组，使用put，offer，take，poll，提供任务和获取任务

* 获取数据
  * poll(time)：在指定时间内取得数据，若没有取得，则返回失败；若成功取得，则返回成功。
  * take()：如果blokqueue为空，则等待线程挂起，直到不为空。
  * drainTo()：一次获取所有的数据对象，不许要分批加锁或释放锁。

* 放入数据
  * offer(anObject)：如果能够放入blockqueue即能够容纳，则返回true，否则返回false。
  * put(anObject)：如果anObject不能放入blockqueue，则当前的执行线程阻塞，等待blockqueue有空间再继续。

在创建ArrayBlockingQueue时，我们还可以控制对象的内部锁是否采用公平锁，默认采用非公平锁

##### 4、GradientDrawable 控件形状的修改

> GradientDrawable 是xml中的shape标签，可动态获取控件、修改控件形状的Java代码实现

* Java代码中动态修改

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

  * setShape(int shape); 参数可使用如下：
    * GradientDrawable.RECTANGLE: 矩形
    * GradientDrawable.OVLA: 椭圆形
    * GradientDrawable.LINE: 线
    * GradientDrawable.RING: 环形

* xml中修改

  设置控件的background的属性，@drawable/shape，对应的shape.xml中的内容为：

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  <shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
      <solid android:color="@color/colorAccent"/>
  </shape>
  ```

---

##### 5、StateListDrawable 控件的不同状态修改为不同的图片

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

---

##### 6、Field类对象

> 描述的是类的属性信息，可获取当前对象的成员变量类型，对成员变量重新设值



```sequence
alice -> bob:hello
```



