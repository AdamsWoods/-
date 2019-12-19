#### Navigte的初步使用

> Navigate组件（导航组件）,主要是页面导航，Fragment页面之间的跳转。
>
> 学习demo: [navigate](https://github.com/googlecodelabs/android-navigation) 
>
> ps: 现在这些代码都是kotlin写的，真的不学一点kotlin，这些东西还真的时没法看。应该会有错误，请批评指正。

##### 1、添加依赖：

```groovy
implementation "androidx.navigation:navigation-fragment-ktx:2.1.0"
implementation "androidx.navigation:navigation-ui-ktx:2.1.0"
```

##### 2、基本的使用步骤：

创建navigation文件，创建fragment，连接fragment流程，设置activity中fragment将navigation连接，设置fragment间的跳转（可设置跳转之间的动画action）

具体如下：

- 1.在res文件夹下面，创建一个navigation的文件夹。接下来创建一个resource的xml文件。

  <img width=50% height=50% src="https://img2018.cnblogs.com/blog/1584305/201912/1584305-20191211110708745-699425340.png"/>

- 2.进入该文件，进入design模式。在此，也可以创建fragment类。然后就可以设计你的fragment的流程了。使用鼠标连接每一个fragment即可，响应的跳转代码都自动生成。（点击，可以创建新的frgament。在navigate.xml文件夹下面就可以看到生成了一个新的fragment节点）

  <img width=50% height=50% src="https://img2018.cnblogs.com/blog/1584305/201912/1584305-20191211110748696-897111046.png"/>

- 3.Activity的布局中fragment的设置，将其与上面xml文件联系起来，(name，defaultNavHost，navGraph属性)设置如下：

  ```xml
  <fragment
   android:id="@+id/my_nav_host_fragment"        
   android:name="androidx.navigation.fragment.NavHostFragment"
   android:layout_width="match_parent"
   android:layout_height="match_parent"
   app:defaultNavHost="true"
   app:navGraph="@navigation/mobile_navigation" />
  ```

- 4.在fragment类中,设置跳转：

  ```kotlin
  view.findViewById<Button(R.id.navigate_destination_button)?.setOnClickListener {
  	findNavController().navigate(R.id.flow_step_one_dest, null)
  }
  
  //or
  Navigation.createNavigateOnClickListener(R.id.flow_step_one_dest)
  ```

- 5.跳转转场动画，可用如下设置：

  ```xml
  <fragment
    	android:id="@+id/home_dest" 
      android:name="****"
      android:label="@string/home"
      tools:layout="@layout/home_fragment">
  
      <action
          android:id="@+id/next_action"
          app:destination="@+id/flow_step_one_dest"
          app:enterAnim="@anim/slide_in_right"
          app:exitAnim="@anim/slide_out_left"
          app:popEnterAnim="@anim/slide_in_left"
          app:popExitAnim="@anim/slide_out_right" />
  
  </fragment>**基本的使用情况就是这样了**
  ```

##### 3.

**基本的使用情况就是这样了**