### [adb 连接手机\] cannot connect to 192.168.137.137:5555: 由于目标计算机积极拒绝，无法连接。 (10061)]

> 遇到的问题，记录一下，不过也都是抄人家的，[😅]

#### 1、问题

手机重启后，电脑输入 `adb connect 192.168.137.137:5555` 结果发生了问题，如下：

> cannot connect to 192.168.137.137:5555: 由于目标计算机积极拒绝，无法连接。 (10061)

`ps: 之前就遇到的问题，现在才真的解决`

#### 2、解决方法

* 使用数据线连接手机和电脑

* 在terminal（使用的是as里面的，cmd一样的）中输入 `adb devices` 查看设备是否连接上

* 输入 `adb root` 获取root权限

* 进入手机系统控制台shell，输入 `adb shell` 

  * <font color=#991100>输入 ` setprop service.adb.tcp.port 5555` 开启adb网络调试</font>

    `ps:  setprop service.adb.tcp.port -1 是打开adb调试`

  * 输入 `exit` 退出手机系统shell

* 开启adb调式端口 `adb tcpip 5555`

* 连接你的设备 `adb connect 192.168.137.137:5555` （自己手机的ip）

此法完全可用。

> 感谢该作者提供的解决方法，[https://www.cnblogs.com/jameszeng/p/11322719.html](https://www.cnblogs.com/jameszeng/p/11322719.html)

