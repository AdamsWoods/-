# 一、Service和BroadcastReceiver

### 1、Service



### 2、AIDL服务（跨进程调用Service）



### 3、接收广播消息



### 4、接收系统广播消息



#  二、Android的网络应用



# 三、桌面管理

**1.改变手机壁纸**

使用WallpaperManager改变手机壁纸，方法：

* `setBitmap(Bitmap bitmap)` 设置壁纸的位图
* `setResource(int resid)` 设置壁纸为resid的图片
* `setStrean(InputStream data)` 将壁纸设置为data数据所代表的图片



*********

# 四、其他

##### 一、easypermission 权限管理

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

