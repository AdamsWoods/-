```python
platformName Android
platformVersion 
deviceName 
appPackage 
appActivity 
```

# adb命令：

* 获取包名和activity名

`adb shell dumpsys window | findstr mCurrentFocus`

* 推送

  ​	adb push 电脑文件路径 手机中目标路径

* 拉去
  	adb pull 手机中目标路径 电脑文件路径

* 应用启动时间
  	adb shell am start -w 包名/activity名

* 系统：系统分配cpu,内存的时间(waitetime)
  	application:应用界别(totaltime)
    	activity:界面加载资源的时间（thisTime）
    	thisTime:mm
    	totaltime;
    	waittime;点击图标到展示出来的总时间

* 获取手机日志

  获取bug

  adb logcat 

  #### 其他命令

	adb install 路径/***.apk
	adb uninstall 包名
	adb devices
	adb shell 进入android中的linux系统命令行中
	adb start-server 启动adb服务，先关闭再启动
	adb kill-server 停止adb服务，先关闭再启动
	adb --help



# Appium 基本操作

#### 1.1 启动其他app 

`driver.start_activity(appPackage, appActivity)`

>  启动appPackage应用，界面名字

#### 1.2 获取app的包名和界面名

**属性名**

```
driver.current_package
driver.current_activity
```

> 当前程序的包名和页面名

#### 1.3 关闭app和驱动对象  

**方法名**

```
driver.close_app()
driver.quit()
```

> close_app 桌面的启动器,直接关闭应用，不关闭驱动对象
>
> quit driver直接退出，同时关闭所有对象

#### 1.4 安装和卸载以及是否安装app

**方法名**

```
driver.is_app_installed('包名') #是否安装
driver.remove_app('包名') #卸载
driver.install(路径)
```

#### 1.5 将应用至于后台

**方法**

```
driver.backgroud_app('时间')
**进入后台时间，再自动返回前台
```

# UIAutomatorViewer

点击按钮报错

* 解决方法
  * `adb kill-server`
  * `adb start-server`

# 元素定位

**方法名**

```
driver.find_element_by_id(resourceid)
driver.find_element_by_class_name(resourceid)
driver.find_element_by_xpath(xpathvalues)
	xpathvalues=//*[@content-dec='']"
```

找一组元素

```
find_elements_by_id(id_value)
find_elements_by_class_name(name_value)
find_elements_by_xpath(xpath_value)
	//*contains(@text,'values')
```



