##### 一、几个基本的布局：

* 表格布局：TabLayout
  	 tabrow:为表个的每一行
    	一个列或者行是否可以收缩帧布局：FrameLayout
  	叠加效果
  	前景：foreground
  	前景的gravity：foregroundGravity
* 相对布局：
* 线性布局：

##### 二、几个基本的控件：

* TextView:继承View 派生出了Button类等
  eclipse：字数太多无法显示的问题
  autoLink:文本转为超链接
  .9图 可以自动缩放
* RationButton:单选按钮

* CheckBox：复选框
* ToggleButton:开关按钮
* imageView:
  scaleType:图片缩放和适应

* AnalogClock：模拟时钟
  * DigitalClock：数字时钟
  Chronometer:计时器
  	setOnChronometerTickListener()监听器；start()；stop()；setBAse()设置开始时间；

* AutoCompleteTextView:自动填充文本编辑框
  	completionHint:下拉菜单的标题
  	completionThreshold:多少个字提示
  	dropHeight:下拉的高度
  	popupBackground:下拉背景
  	设置Adapter,ArrayAdapter<String>

* Spinner:下拉列表
  	prompt:提示
  	entries:下拉列表的选项 <string-array name=""></string-array>
  	设置BaseAdapter，可以自定义列表项getView()

* ProgressBar:
  	max; progress; drawable 在drawable文件中添加进度条的自定义文件<layer-list>background和progress</...>
  	progressstyle
  	setProgress();设置进度
  	incrementProgressBy();设置进度的增减

* SeekBar:
  	可拖动的进度条
  	thumb设置拖动的图标
  	其他与progress相同	

* RationBar：星级评分条
  	numStars:多少个星星
  	rating:评分等级
  	stepSize:改变的不伐
  	isIndicator:是否允许用户改变
  	其他与progress相同

* TabHost:标签窗口
  	newTabSpec(String tag);//
  	addTab(TabHost.TabSpec tabSpec)//添加选项卡
  	布件写tag
  	Activity继承TABActivity	
  	 final TabHost tabHost = getTabHost();
       LayoutInflater.from(this).inflate(R.layout.tabhost_activity, tabHost.getTabContentView(),true);
  	 tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("已接电话").setContent(R.id.tab01));
  DatePicker:
  	日期选择器
  	init(year,month,day,Listener(){})
  TimePicker:
  	时间选择器
  	onTimeChangedListener(){}监听器，获取时间

ListView:
	普通列表
expandListView:	
	可扩展列表

* GridView:
  	网格视图
  	columnWidth:列宽
  	gravity:对其方式
  	numColumns：列数
  	horizontalSpacing:水平间距
  	vierticalSpaing:垂直间距
  	strechMode:拉伸模式
  	SimpleAdapter(context, )//
  	gridview.setAdapter();//
* Gallery：
  	画廊视图
  	unselectAlpha:没有选中的透明度
  	spacing:列表内的间距
  	animationDuration:切换的动画持续时间
  	setAdapter();
  	setOnItemSelectdListener();

//弹窗类

* AlertDialog:
  	Builder builder = AlertDialog.Builder();
  	builder.setIcon();//
  	builder.setTitle();
  	builder.setMessage();
  	builder.setNagtiveButton();
  	builder.setPositiveButton();
  	builder.setItems(int itemsId,Listener);
  	//单选dialog
  	builder.setSingleChoiceItems(Charsequence[] items,int checkeditem, Listener):
  	//复选dialog
  	builder.setMultiChoiceItems(charsequence[] items,boolean[] checkitems, Listener);

	builder.setAdapter(ListAdapter adapter, Listener);//自定以列表
	builder.create().show();
	//activity为弹窗格式
	theme="@android:style/Theme.Dialog"
**PopupWindow:**

​	显示位置：showAsDropDown(view v)在v的下面显示
​			  showAtLocation()在指定位子
​	View root = LayoutInflater.inflate(R.layout.main);
​	new PopupWindow(View root, width, height);//root为popupwindow布局
​	popupwindow.dismiss();

**ProgressDialog:**

​	setIndeterminate(boolean)//不显示进度值
​	setMax（）:
​	setMessage();//设置内容
​	setProgress();//设置进度
​	setProgressStyle();//进度条风格

**Toast:**

​	setView();//自定义Toast内容
​	步骤:
​	toast.getView();
​	LinearLayout linearLayout;
​	linearLayout.add();
​	toast.add(LinearLayout);
​	toast.show();

**Notification:**

​	1.NotificationManager manager = getSystemService(NOCTIFICATION_SERVICE);
​	2.Notification notify;
​	3.notify.set...（PendingIntent页面跳转）
​	4.manager.notify(id ,notify);
​	manager.cancel();//取消

**Menu:**

​	submenu//子菜单
​	submenu.setIcon();//高级别的系统默认没有
​	submenu.setHeadrIcon();
​	submenu.setHeaderTitle();
​	submenu.setHeaderView();
​	add(goupid,itemid,order,title);
​	onCreateOptionsMenu();//添加菜单
​	onOptionsItemSelected();//点击菜单item,监听器
​	menuitem.setOnMenuItemClickListener();//设置item的监听


	//复选框的menuitem,单选框
	setCheckable(boolean);//是否可以被勾选
	setGroupCheckable(goupid,boolean checkable,boolean exclusive));
		//exclusive,是否为一组单选菜单项
	快捷键
	
	ContextMenu//上下文菜单
	1.重写onCreateContextMenu(menu，view Source, menuinfo);
	2.registerForContextMenu(View view);//为view注册上下文menu
	3.onContextItemSelected();

事件处理:
	事件源与监听在一起的：回调机制
			  不在一起的：监听机制
	回调机制：事件源可以自己处理监听事件
	监听机制：系统将事件发送给事件监听器
	
	监听器的几种实现方式：
		匿名内部类button.setOnClickListener(new View.OnClickListener(){})
		内部类 	与外部类相似，只是在activity内部定义一个类
		外部类	class MyListener implement OnClickListener{}实现onclickListener()方法
		绑定到 activity class myactivity implement OnClickListener{}
	监听与回调的一个顺序
		android系统会先将事件给绑定的事件监听器，然后给事件源提供的监听器，传播到该组件所在的activity
	Configuration获取设备配置信息
		Congfiguration config = getResource().getConfiguration();
		config.oritation;
	重写onConfigurationChanged()响应设备更改
	
	handler消息传递机制
		1.handler启动新的线程发送消息
		2.在主线程中获取、处理消息
		sendEmptyMessage();/sendMessage();/handleMessage();
		sendMessageDelay();/sendEmptyMessageDelayed();
		Timer周期行的执行任务，new Timer().shcedule(new TimerTask(){});

Activity:
	icon：图标
	label: 标签
Activity的回调
	activity1->activity2(填写数据)->activity1(拿到activity2的数据)
	activity1:
		startActivityForResult(intent, requestcode);
		onActivityResult(requestcode, resultcode, intent)
	activity2:
		Activity2.setResult(resultcode, intent);
Activity的生命周期：
	oncreate --> onstart --> onresume --> onpause --> onstop --> ondestroy
activity的启动：
	<intent-filter>
		<ation android:name=""/> 抽象动作,可以有N个
		<category andriod:name=""/> actioan附加信息，可以有N个
	</intent-filter>
	
	一个Intent只能有一个action，有N个category
	
	intent.setAction();//设置action属性
	intent.setType();	//设置intent的类型
	intent.setCategory();//设置intent的category属性
	
	Data/Type属性
	Data为一个content://com.android.contacts/contacts/1(一个uri)
	：前面的为类型，后面为数据
	ACTION与DATA组合：
		ACTION_VIEW content://.... 为查看这个uri指定的数据
		ACTION_EDIT content://.... 为查看这个uri指定的数据编辑页面
		ACTION_DIAL content://.... 为查看这个uri指定的数据拨号页面
	intent.setAction();
	intent.setData();
	
	继承Tabactivity实现tab页，
	tabhost.addTab(tabHost.newTabSpec(string).setIndicatior("tab标签文字").setContent(new intent(this,AimActivity.class)));

android 资源字体，组件大小
	<dimen name="">30dp</dimen>
	<dimen name="">30sp</dimen>
	xml文件的使用语法：
		@[<package_name>:]<resource_type>/<resource_name>
	xml文件中可定义：
		bool变量值，<bool>
		int，<integer>
		定义数组资源：
			<array/>
			<string-array/>
			<integer-array/>
		数组资源的使用：
			TypeArray array = getResource().obtainTypeArray(R.array.name);
			view.setBackgroundDrawable(array.getDrawable(position));
		StateListDrawable资源：
			<selecltor>
				<item color/drawable = ""
					android:state_color/pressed... = ""/>
			<seclector/>
		LayerDrawable资源：
			<layer-list>
				<item />
				<item>...</item>
				...
			</layer-list>
		ShapeDrawable资源：形状
			<shape>
				<corners>
				<padding>
				<gradient>
				<size>
				<solid>
				<stroke>
			</shape>
		ClipDrawable资源：
			<clip oritation=""
				drawable=""
				gravity="">
		AnimationDrawable:
			属性值：from,to,pivotX,pivotY(变换中心点)
			alpha
			scale
			translate
			rotate
		  Animaiton anim = AnimationUtils.loadAnimation(context, resId);
		  anim.setFillAfter(boolean);
		  view.startAnimation(anim);
第11章 音视频的播放
	
			
			
			
			
			
			
			
			
	
	
	
