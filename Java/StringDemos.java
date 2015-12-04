/**
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


