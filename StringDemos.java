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


