/**
 * Created by bpei
 */
 
 
 /**
 1. 数组
 数组的长度是后面new出来的
 */
  int[] i = new int[12];
  int[] days = {1,2,3,4,5,6,7};
  Calendar[] cal = new Calendar[3];
  int[][] my = new int[10][];
  for (int i=0; i<10; i++) {
	  my[i] = new int[24];
  }

  
  
/**
2. ArrayList
ArrayList封装了数组的功能，允许自动扩展，可以容纳任何类型的对象，以下方法也是List接口的主要方法及Vector和其他集合类的方法
add(Object o)	在末尾添加给定元素
add(int i, Object o)	在指定位置插入给定元素
clear()		从集合中删除全部元素
Contains(Object o)		如果包含给定元素，返回true
get(int i)		返回指定位置的对象句柄
indexOf(Object o)		如果找到给定对象，则返回其索引值，否则返回-1
remove(Object o)	根据引用或位置删除对象
remove(int i)
toArray()		返回包含集合对象的数组
*/
List a = new ArrayList();	//常用写法，接口在前，具体类对象在等号右边，可以用指定类型，如List<String>
a.add(xxx)
a.xxx



/**
3. Iterator
用于取集合中的元素，当不知道集合中是什么类型元素，用Iterator， Iterator是接口
如果知道是什么类型，则用for(Type a :: Collection col)
*/
