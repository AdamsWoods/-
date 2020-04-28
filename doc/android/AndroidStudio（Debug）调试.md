#### AndroidStudio（Debug）调试

> 最近使用Android studio调试的比较多，记录一下

##### 1、debug的两种模式

* 重新开始的debug（从启动开始）

  ![1583923554629](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583923555848.png)

* 插入式的debug（从当前页开始）

  ![1583923586787](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583923586787.png)

##### 2、插入断点

如果已经在debug的模式下，还未执行到的行，可以在下面插入一个断点

其他的也就一样了

##### 3、查看，屏蔽断点

![1583924429616](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924429616.png)

* 查看所有断点（上面的两个红豆），同时也可以选择关掉某一个breakpoint

  ![1583924408381](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924408381.png)

* 上面的红豆带一个斜杠，![1583925042580](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583925042580.png)可以不在该处停止，继续执行

  ![1583924680324](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924680324.png)

* 单个断点屏蔽(右键代码左边的红豆，可单独关闭)

  ![1583924171727](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924171727.png)

enable: 就是去掉这个断点，不会在这里挺住

suspend: 挂起（没怎么用过）

##### 4、断点的跳转

![1583924723553](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924723553.png)

这个按钮，可以从当前断点跳到下一个断点

##### 5、一步步的调试

![1583924874711](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1583924874711.png)

* 第一个，直接下一步
* 第二个，进入该方法
* 第三个，强制进入
* 第四个，退出该方法

差不多了，基本就是这个样。功能点基本常用的就这么多。