### LogCat 和 Run 里面不打印日志和报错问题

最近老是出现logcat里面没有日志，run里面也没有错误日志。出现bug了就没法调试，之前不知道怎么就弄好了，使用的一些方法 `adb logcat` 没什么用。 

今天，再次百度发现了一个解决方法，然后该方法可用。根据解决方法，应该时手机把log给关掉了。

> 参考链接：[https://blog.csdn.net/gladyoucame/article/details/84476418](https://blog.csdn.net/gladyoucame/article/details/84476418) 

然后摘抄一下，记录这个方法：

* 进入你的拨号页，输入一串字符  `*#*#2846579#*#*`  
* 然后进入 工程菜单  ![1587989541290](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1587989541290.png) 
* 接着进入后台设置 ![1587989596669](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1587989596669.png)
* 再就是log的设置了，![1587989630279](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1587989630279.png)
* 结束了