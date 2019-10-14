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

##### 3、阻塞数组队列ArrayBlockingQueue

