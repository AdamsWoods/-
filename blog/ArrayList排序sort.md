#### ArrayList排序sort

使用ArrayList的sort(Comparator<?> comparator)进行排序

##### 方法一：list.sort();

```java
/**
 * 降序排序
 */
private ArrayList<BaseItem> sortItem(ArrayList<BaseViewItem> list){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
         list.sort((o1, o2) -> {
         long date1 = 0;
         long date2 = 0;
         if (o1 instanceof ViewItem){
              ViewItem item = (ViewItem) o1;
              date1 = Long.valueOf(dateToStamp(item.getDate()));
          } else {
              TaskViewItem item = (TaskViewItem) o1;
              date1 = Long.valueOf(dateToStamp(item.getDate()));
          }
          if (o2 instanceof SheetItem){
              SheetItem item = (SheetItem) o2;
              date2 = Long.valueOf(dateToStamp(item.getDate()));
          } else {
              TaskViewItem item = (TaskViewItem) o2;
              date2 = Long.valueOf(dateToStamp(item.getDate()));
          }
          /**
           * 比较大小
           */
          if (date1 >= date2)
              return -1; //-1 降序 1 升序
          else
              return 1;
          });
     }
     return list;
}
```

##### 方法二：Collections.sort();

```java
public class Persons implements Comparable<Persons>{
    private int age;

    public Persons(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

	/**
	 * 实现比较的方法，返回值的正负确定顺序
	 */
    @Override
    public int compareTo(Persons o) {
        if(this.age < o.age){
            return -1; 
        }else if(this.age == o.age){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public String toString() {
        return "Persons{" + "age=" + age + '}';
    }
}

//使用方法
ArrayList<Person> list = new ArrayList<Person>();
list.add(new Person(12));
.....
Collections.sort(list); //对list排序
```

