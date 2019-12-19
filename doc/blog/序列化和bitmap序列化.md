#### 序列化和bitmap序列化

> 记录一下，遇到的问题。activity之间intent传递数据，需要将对象序列化，同时一个对象中带有bitmap。百度了一下，很多序列化的文章，解决了自己的问题。

##### 序列化

现在我尝试的序列化有两种：一种是java的序列化Serializable，一种是Android的序列化Parcelable。

Parcelable的性能相比Serializable要高，但是使用起来没有那么的简单。

###### 1、Serializable序列化

​		类只要implement Serializable接口即可。同时，在类中需要声明一个标识serialVersionUID，如下所示：

`private static final long serialVersionUID = 12907898L;`

这样就好了。<font color="#333333ff">如果，不加这个UID也可以。但是，类中少了一个属性的话，程序就会崩溃。如果加了，程序不会崩溃。</font>

序列化：

```java
User person = new User();
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
out.writeObject(person);
out.close()
```

反序列化：

```java
ObjectInputStream in = new ObjectInputStream(new FileInputStream("cache.txt"));
User user = in.readObject(user);
in.close();
```

Intent传递：

```
intent.putExtra("personInfo", bean);

User person = getIntent().getSerializableExtra("personInfo");
```

###### 2、Parcelable序列化，bitmap的序列化

实现Parcelable接口，<font color="#333333ff">[同时参照网上的教程实现bitmap的序列化](!https://www.cnblogs.com/Free-Thinker/p/4635170.html)</font>，如下示例：

```java
public class Person implements Parcelable {
    
    //此处为静态变量。序列化时，初始化了。反序列时，还可以用。
  private static byte[] bytes;
    
  private Bitmap headBitmap; //头像
  private String level; //职位
  private String name; //姓名
  
  ......

  @Override
  public int describeContents() {
        return 0;
  }

    //对象写成序列，这些属性都是顺序的
  @Override
  public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(head_url);
        bytes = getBytesFromBitmap(headBitmap);
        dest.writeByteArray(bytes);
        dest.writeString(level);
        dest.writeString(name);
  }
    
    //序列转成对象
  public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new PersonBean(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new PersonBean[size];
        }
   };
   
   private PersonBean(Parcel in){
        in.readByteArray(bytes);
        headBitmap = getBitmapFromArrayBytes(bytes);
        level = in.readString();
        name = in.readString();
    }
    
    //bitmap转byte
    private byte[] getBytesFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = 
            new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    //byte转bitmap
    private Bitmap getBitmapFromArrayBytes(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }
}
```

Intent传递：

```java
intent.putExtra("personInfo", bean);

User person = getIntent().getParcelableExtra("personInfo");
```



> 参照bitmap序列化：[https://www.cnblogs.com/Free-Thinker/p/4635170.html](!https://www.cnblogs.com/Free-Thinker/p/4635170.html)