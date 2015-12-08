/**
 * Created by Bing Pei.
 */

/**
1. substring()分解字符串
截取具体下标范围的首选substring，如果没有第二个参数则默认截取到尾部，从0开始，包括第一个参数，不包括第二个参数
[第一个参数，第二个参数）
*/

public static void main(String[] av) {
	String a = "Java is great.";
	System.out.println(a);
	String b = a.substring(5);
	System.out.println(b);
	String c = a.substring(5, 7);
	System.out.println(c);
	String d = a.substring(5, a.length());
	System.out.println(d);
}



/**
2. StringTokenizer分解字符串
当需要分割以特殊字符作为分隔符的字符串时，要考虑用StringTokenizer， 为指定字符串构造StringTokenizer对象，调用其
hasMoreTokens()方法和nextToken()方法， 默认分隔符为欧洲语言单词分隔符，也可以在第二个参数指定分隔符，第二个参数
可接受多个分隔符，默认不把分隔符放入分割后的字符串中，如果需要把分隔符当作单独的一个分割后的字符串，则需要把第三
个参数设为true
*/
//Demo1
StringTokenizer st = new StringTokenizer("Hello World of Java");

while (st.hasMoreTokens())
	System.out.println("Token: " + st.nextToken);

//Demo2
StringTokenizer st = new StringTokenizer("Hello, World|of|Java", ", |"); //逗号，空格，竖线三种分隔符

while(st.hasMoreElements()) //这里用的hasMoreElements
	System.out.println("Token: " + st.nextElement());
	
//Demo3
//逗号，空格，竖线三种分隔符，结果中分隔符本身也会作为单独一个分割后的字符串被打印出来
StringTokenizer st = new StringTokenizer("Hello, World|of|Java", ", |", true); 

while(st.hasMoreElements()) 
	System.out.println("Token: " + st.nextElement());

//Demo4
//相对于StringTokenizer，正则表达式更灵活，但一般不复杂，固定格式的场景StringTokenizer还是可以使用的，正则更常用于匹配复杂的场景
Matcher toke = Pattern.compile("\\d+").matcher(inputString); //注意是两个斜线
while(toke.find()) {
	String courseString = toke.group(0);
	int courseNumber = Integer.parseInt(courseString);
	...
}



/**
3. 连接字符串
可以使用“+”来连接字符串，Java编译器会把+编译为StringBuilder，然后调用其append()方法，由于+会产生多个StringBuilder对象来连接，
所以直接使用一个StringBuilder的效率高于用+，
StringBuilder和StringBuffer功能基本相同，只是StringBuffer线程安全，StringBuffer线程不安全，所以StringBuilder效率更高
*/

//三种方法构造同一字符串
public class StringBuilderDemo {
	public static void main(String[] argv) {
		String s1 = "Hello" + ", " + "World";
		System.out.println(s1);
		
		StringBuilder sb2 = new StringBuilder();
		sb2.append("Hello");
		sb2.append(',');
		sb2.append(' ');
		sb2.append("World");
		
		String s2 = sb2.toString(); //注意StringBuilder和StringBuffer需要toString
		System.out.println(s2);
		
		/*因为append()方法将返回对该StringBuilder对象本身的引用，所以.append(...).append(...)语句很常用
		不管修改了StringBuilder中多少字符，所有方法，append(),delete(),deleteCharAt(),insert(),replace(),reverse()，都只返回该
		StringBuilder的对象的引用，这个十分重要，可以连用函数，xxx().xxx().xxx()
		*/
		StringBuilder sb3 = new StringBuilder().append("Hello").append(",").append(" ").append("World");
		System.out.println(sb3.toString);
	}
}



/**
4. 处理单个字符
可以采用循环及String类charAt()方法， charAt有点类似于数组下标取值
*/
String a = "Hello, Java world!"
for (int i=0; i<a.length(); i++) {
	System.out.println("Char " + i + " is " + a.charAt(i));
}



/**
5. Unicode字符与String转换
因为Java字符和Unicode字符都是16位，所以Java字符能支持任何一个Unicode字符。String类的charAt方法可以返回一个Unicode字符，StringBuilder的append方法
也有一种形式可以接受Unicode，但是，有的系统没有相关字符集，可能无法正常显示Unicode字符。

Unicode utf-8 GBK
Unicode（统一码、万国码、单一码）是计算机科学领域里的一项业界标准,包括字符集、编码方案等。Unicode 是为了解决传统的字符编码方案的局限而产生的，
它为每种语言中的每个字符设定了统一并且唯一的二进制编码，以满足跨语言、跨平台进行文本转换、处理的要求。
utf-8
UTF-8（8-bit Unicode Transformation Format）是一种针对Unicode的可变长度字符编码。对可以用ASCII表示的字符使用UNICODE并不高效，因为UNICODE比ASCII
占用大一倍的空间，而对ASCII来说高字节的0对他毫无用处。为了解决这个问题，就出现了一些中间格式的字符集，他们被称为通用转换格式，即UTF.
UTF-8 是字节顺序无关的。它的字节顺序在所有系统中都是一样的,没有大小端问题
GBK Chinese Internal Code Specification GBK编码，是在GB2312-80标准基础上的内码扩展规范，使用了双字节编码方案，
其编码范围从8140至FEFE（剔除xx7F），共23940个码位，共收录了21003个汉字

基本可以认为Unicode是最全的，UTF-8可以变长且没有大小端，GBK用于汉字
*/
public class UnicodeChars {
	public static void main(String[] argv) {
		StringBuffer b = new StringBuffer();
		for (char c = 'a'; c < 'd'; c++) {
			b.append(c);
		}
		//直接append就行
		b.append('\u00a5'); //日文Yen
		b.append('\u0391'); //阿尔法
		
		for (int i=0; i<b.length(); i++) {
			System.out.println("Character #" + i + " is " + b.charAt(i)); //用charAt，和普通字符一样
		}
		System.out.println("Characters: " + b);
	}
}



/**
6. 颠倒字符串
单纯颠倒字符串本身，用StringBuilder就行，对于需要按单词颠倒的，可以用StringTokenizer和Stack
*/
//Demo1
String sh = "abcdefg"；
System.out.println(sh + " -> " + new StringBuffer(sh).reverse()); //注意StringBuffer需要用string为参

//Demo2
String s = "We are family";

Stack myStack = new Stack();
StringTokenizer st = new StringTokenizer(s); //StringTokenizer需要待分割String为参数
while(st.hasMoreTokens()) myStack.push(st.nextElement());

System.out.print('" ' + s + '""' + " backwards by word is:\n\t");
while(!myStack.empty()) {
	System.out.print(myStack.pop());
	System.out.print(' ');
}
System.out.println('""');



/**
7. 字母大小写
可以用toUpperCase, toLowerCase方法，还有equals()方法和equalsIgnoreCase()方法来比较两个字符串是否相等，不同的是后者忽略大小写

== equals比较：
==用于比较引用和比较基本数据类型时具有不同的功能：比较基本数据类型，如果两个值相同，则结果为true而在比较引用时，如果引用指向内存中的同一对象，结果为true
equals()方法的本意为确定两个对象的引用是否相同。而JDK类中有一些类覆盖了oject类的equals()方法，比较规则为：如果两个对象的类型一致，并且内容一致，
则返回true。这些类有：java.io.file,java.util.Date,java.lang.string,包装类（Integer,Double等）
*/
String a = "Hello, Java!";
System.out.println("Upper: \t" + a.toUpperCase());
System.out.println("Lower: \t" + a.toLowerCase());
String b = "hello, java!";
if (a.equalsIgnoreCase(b)) {
	System.out.println("The same word ignore case");
}



/**
8. 把非打印字符插入字符串中
用反斜杠转义
*/
System.out.println("The character is : \\t");



/**
9. 打印注释内容
删除字符串尾部空格
用String的trim()
trim可以删除字符串首尾的空格和tab
*/
//打印注释，假设注释以/**开头，*/结尾
public final String startMark = "//**";
public final String endMark = "*//";
protected boolean printing = false;
	try {
		String inputeLine;
		
		while((inputLine = is.readLine()) != null) {
			if (inputLine.trim().equals(startMark)) { //开头，后面打印
				printing = true;
			} else if (inputLine.trim().equals(endMark)) { //结尾，不打印
				printing = false;
			} else if (printing) {
				System.out.println(inputLine);
			}
		}
		is.close();
	} catch (IOException e) {
		//
	}
}
