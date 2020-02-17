### ConstraintLayout中的chainstyle & guideline

> 有时候容易忘记，摘抄记录一下。
>
> 参考博客：[https://blog.csdn.net/cui130/article/details/89849807](https://blog.csdn.net/cui130/article/details/89849807) 

#### 1、chainstyle（链式）

**属性 **有水平和垂直之分：

* `layout_constraintHorizontal_chainStyle` 水平方向
* `layout_constraintVertical_chainStyle` 垂直方向

**属性值** 有三种：

* spread (默认) 如下：

  * 默认模式如下：

  ![1581678709089](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581678709089.png)

  * 使用weight方式如下：（layout_width=0dp）

    添加属性`layout_constraintHorizontal_weight` ,同理也有垂直方向的，可以自己尝试

    ![1581678840993](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581678840993.png)

* spread_inside

  ![1581678652469](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581678652469.png)

* packed

  * 捆绑在一起居中显示

  ![1581678346181](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581678346181.png)

  * 偏移显示

    添加属性`layout_constraintHorizontal_bias`

    ![1581678560641](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581678560641.png)

#### 2、guideline（辅助线）

**属性** 辅助线也有垂直和水平之分，使用属性 `orientation` 来确定方向

* horizontal 辅助线为水平方向
* vertical 辅助线为垂直方向

确定辅助线的位置有三种属性：

* `layout_constraintGuide_begin` 水平方向为距离左边的距离；垂直方向为距离上边的距离

  ```
  app:layout_constraintGuide_begin="100dp"
  ```

* `layout_constraintGuide_end`  水平方向为距离右边的距离；垂直方向为距离下边的距离

  ```
  app:layout_constraintGuide_percent="0.4"
  ```

* `layout_constraintGuide_percent` 水平方向为从左向右占比；垂直方向为距离从上到下占比

  ```
  app:layout_constraintGuide_end="50dp"
  ```

举例如下：（垂直方向，水平方向同理）

![1581680594036](C:\Users\WisdomZhang\AppData\Roaming\Typora\typora-user-images\1581680594036.png)

#### 3、代码如下：

```xml
<TextView    
          android:id="@+id/weightStart"    
          android:layout_width="0dp"    
          android:layout_height="30dp"    
          android:text="weightStart"    
          android:textColor="#ffffff"    
          android:background="#83BB96"    
          android:gravity="center"    
          android:layout_marginTop="10dp"    
          app:layout_constraintHorizontal_chainStyle="spread"   
          app:layout_constraintHorizontal_weight="1"    
          app:layout_constraintStart_toStartOf="parent"    
          app:layout_constraintEnd_toStartOf="@id/weightCenter"    
          app:layout_constraintTop_toTopOf="parent"/>

<TextView    
          android:id="@+id/weightCenter"    
          android:layout_width="0dp"    
          android:layout_height="30dp"    
          android:text="weightCenter"    
          android:textColor="#ffffff"    
          android:background="#567B63"    
          android:gravity="center"    
          app:layout_constraintHorizontal_weight="2"   
          app:layout_constraintTop_toTopOf="@+id/weightStart"    
          app:layout_constraintStart_toEndOf="@+id/weightStart"    
          app:layout_constraintEnd_toStartOf="@+id/weightEnd"/>

<TextView    
          android:id="@+id/weightEnd"    
          android:layout_width="0dp"    
          android:layout_height="30dp"    
          android:text="weightEnd"    
          android:textColor="#ffffff"    
          android:background="#669999"    
          android:gravity="center"   
          app:layout_constraintHorizontal_weight="3"   
		  app:layout_constraintTop_toTopOf="@+id/weightStart"    
		  app:layout_constraintStart_toEndOf="@id/weightCenter"    
          app:layout_constraintEnd_toEndOf="parent"/>

<TextView    
          android:id="@+id/insideStart"    
          android:layout_width="wrap_content"    
          android:layout_height="30dp"    
          android:text="insideStart"    
          android:textColor="#ffffff"    
          android:background="#83BB96"    
          android:gravity="center"    
          android:layout_marginTop="10dp"    
          app:layout_constraintHorizontal_chainStyle="spread_inside"   
          app:layout_constraintStart_toStartOf="parent"   
          app:layout_constraintEnd_toStartOf="@id/insideCenter"   
          app:layout_constraintTop_toBottomOf="@+id/weightStart"/>

<TextView    
          android:id="@+id/insideCenter"    
          android:layout_width="wrap_content"    
          android:layout_height="30dp"    
          android:text="insideCenter"   
          android:textColor="#ffffff"    
          android:background="#567B63"   
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/insideStart"   
          app:layout_constraintStart_toEndOf="@+id/insideStart"   
          app:layout_constraintEnd_toStartOf="@+id/insideEnd"/>

<TextView    
          android:id="@+id/insideEnd"   
          android:layout_width="wrap_content"    
          android:layout_height="30dp"   
          android:text="insideEnd"   
          android:textColor="#ffffff"   
          android:background="#669999" 
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/insideStart"   
          app:layout_constraintStart_toEndOf="@id/insideCenter"   
          app:layout_constraintEnd_toEndOf="parent"/>

<TextView   
          android:id="@+id/PackedStart"    
          android:layout_width="wrap_content"   
          android:layout_height="30dp"   
          android:text="PackedStart"  
          android:textColor="#ffffff"   
          android:background="#83BB96"  
          android:gravity="center"  
          android:layout_marginTop="10dp"   
          app:layout_constraintHorizontal_chainStyle="packed"   
          app:layout_constraintStart_toStartOf="parent"   
          app:layout_constraintEnd_toStartOf="@id/PackedCenter"   
          app:layout_constraintTop_toBottomOf="@+id/insideStart"/>

<TextView   
          android:id="@+id/PackedCenter"    
          android:layout_width="wrap_content"    
          android:layout_height="30dp"   
          android:text="PackedCenter" 
          android:textColor="#ffffff"   
          android:background="#567B63"   
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/PackedStart"   
          app:layout_constraintStart_toEndOf="@+id/PackedStart"   
          app:layout_constraintEnd_toStartOf="@+id/PackedEnd"/>

<TextView   
          android:id="@+id/PackedEnd"    
          android:layout_width="wrap_content"  
          android:layout_height="30dp"   
          android:text="PackedEnd"    
          android:textColor="#ffffff"  
          android:background="#669999"  
          android:gravity="center"    
          app:layout_constraintTop_toTopOf="@+id/PackedStart"   
          app:layout_constraintEnd_toEndOf="parent"    
          app:layout_constraintStart_toEndOf="@+id/PackedCenter"/>

<TextView    
          android:id="@+id/PackBiasStart"   
          android:layout_width="wrap_content"  
          android:layout_height="30dp"   
          android:layout_marginTop="8dp"  
          android:background="#83BB96" 
          android:gravity="center" 
          android:text="PackBiasStart"   
          android:textColor="#ffffff"    
          app:layout_constraintHorizontal_chainStyle="packed"   
          app:layout_constraintHorizontal_bias="0.2"    
          app:layout_constraintStart_toStartOf="parent"   
          app:layout_constraintEnd_toStartOf="@id/PackBiasCenter"   
          app:layout_constraintTop_toBottomOf="@+id/PackedStart" />

<TextView   
          android:id="@+id/PackBiasCenter"    
          android:layout_width="wrap_content"  
          android:layout_height="30dp"   
          android:text="PackBiasCenter"  
          android:textColor="#ffffff"   
          android:background="#567B63"  
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/PackBiasStart"    
          app:layout_constraintStart_toEndOf="@+id/PackBiasStart"    
          app:layout_constraintEnd_toStartOf="@+id/PackBiasEnd"/>

<TextView    
          android:id="@+id/PackBiasEnd"    
          android:layout_width="wrap_content"   
          android:layout_height="30dp"   
          android:text="PackBiasEnd"   
          android:textColor="#ffffff"  
          android:background="#669999" 
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/PackBiasStart"   
          app:layout_constraintStart_toEndOf="@id/PackBiasCenter"   
          app:layout_constraintEnd_toEndOf="parent"/>

<TextView   
          android:id="@+id/SpredStart"   
          android:layout_width="wrap_content"  
          android:layout_height="30dp"   
          android:text="SpredStart"  
          android:textColor="#ffffff" 
          android:background="#83BB96"   
          android:gravity="center" 
          android:layout_marginTop="10dp"    
          app:layout_constraintHorizontal_chainStyle="spread"   
          app:layout_constraintStart_toStartOf="parent"   
          app:layout_constraintEnd_toStartOf="@id/SpredCenter"    
          app:layout_constraintTop_toBottomOf="@+id/PackBiasStart"/>

<TextView   
          android:id="@+id/SpredCenter"    
          android:layout_width="wrap_content"  
          android:layout_height="30dp"   
          android:text="SpredCenter"  
          android:textColor="#ffffff"   
          android:background="#567B63"  
          android:gravity="center"    
          app:layout_constraintTop_toTopOf="@+id/SpredStart"    
          app:layout_constraintStart_toEndOf="@+id/SpredStart"    
          app:layout_constraintEnd_toStartOf="@+id/SpredEnd"/>

<TextView 
          android:id="@+id/SpredEnd"   
          android:layout_width="wrap_content"   
          android:layout_height="30dp"  
          android:text="SpredEnd"  
          android:textColor="#ffffff"   
          android:background="#669999" 
          android:gravity="center"   
          app:layout_constraintTop_toTopOf="@+id/SpredStart"   
          app:layout_constraintStart_toEndOf="@id/SpredCenter"   
          app:layout_constraintEnd_toEndOf="parent"/>

<androidx.constraintlayout.widget.Guideline
           android:id="@+id/guideline1"
		   android:layout_width="wrap_content"
		   android:layout_height="wrap_content"
           android:orientation="vertical"
		   app:layout_constraintGuide_begin="100dp"/>

<TextView   
          android:id="@+id/guide1"   
          android:layout_width="wrap_content"    
          android:layout_height="30dp"   
          android:text="SpredEnd"   
          android:textColor="#ffffff" 
          android:background="#669999"  
          android:gravity="center"    
          app:layout_constraintEnd_toStartOf="@+id/guideline1"   
          app:layout_constraintBottom_toBottomOf="parent"/>

<androidx.constraintlayout.widget.Guideline 
		android:id="@+id/guideline2"                                       		   android:layout_width="wrap_content"    
        android:layout_height="wrap_content"   
 		android:orientation="vertical"  
		app:layout_constraintGuide_end="50dp"/>

<TextView    
          android:id="@+id/guide2"    
          android:layout_width="wrap_content"    
          android:layout_height="30dp"    
          android:text="SpredEnd"  
          android:textColor="#ffffff" 
          android:background="#669999"   
          android:gravity="center"    
          app:layout_constraintEnd_toStartOf="@+id/guideline2"    
          app:layout_constraintBottom_toBottomOf="parent"/>

<androidx.constraintlayout.widget.Guideline
		android:id="@+id/guideline3"    
		android:layout_width="wrap_content"     
		android:layout_height="wrap_content"    
		android:orientation="vertical"   
		app:layout_constraintGuide_percent="0.6"/>

<TextView    
          android:id="@+id/guide3"    
          android:layout_width="wrap_content"   
          android:layout_height="30dp"    
          android:text="SpredEnd"    
          android:textColor="#ffffff"   
          android:background="#669999" 
          android:gravity="center"     
          app:layout_constraintEnd_toStartOf="@+id/guideline3"    
          app:layout_constraintBottom_toBottomOf="parent"/>
```

