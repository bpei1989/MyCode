/**
 * Created by bpei
*/


/**
1. 泛型
List<String> myList = new ArrayList<String>();
*/


/**
2. foreach
for(String s : myList) 	//最常用
	System.out.println(s);
:表示"in"
*/


/**
3. 避免强制类型转换
用<T>,参数化
注意，如果不提供具体类型，类型就会被视为Object
*/
//一个简单的Stack实现
public class MyStack<T> { //T参数化泛型
	...
	
	public void push(T obj){ //所有涉及泛型的都用T
		...
	}
	
	public T pop() { //	pop的返回值也是T
		...
	}
	
	public boolean hasNext() {
		...
	}
}

MyStack<Bank> bank = new MyStack<Bank>();



/**
4. Enum
用enum类型
public enum Color{  
    RED,BLUE,BLACK,YELLOW,GREEN  
}
际上enum声明定义的类型就是一个类。 而这些类都是类库中Enum类的子类(java.lang.Enum<E>)。它们继承了这个Enum中的许多有用的方法。
声明enum关键字和class关键字一样，enum再起所在的文件中可声明为public或缺省访问方式，亦可以在类中声明
*/
//Meida.java
public enum Media {
	book, music, movie;
}

//使用
public class Products {
	String titile;
	String author;
	Media m; //enum是一个类
}

//Media类的实际内部实现(不用自己实现，Java已经实现，主要是看enum关键字实现原理)
public class Media extends java.lang.Enum{
	public static final Meida book;
	public static final Meida music;
	public static final Meida movie;
	public static final Media[] values;
	public static Media valueOf(java.lang.String);
	public Media(java.lang.String, int);
	public int compareTo(java.lang.Enum);
	public int compareTo(java.lang.Object);
	static {};
}
