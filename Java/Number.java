/**
 * Created by Bing Pei.
 */

 /**
 基本数据类型	封装对象    大小（bit）    内容
 byte			Byte		8			有符号整数
 short			Short		16			有符号整数
 int			Integer		32			有符号整数
 long			Long		64			有符号整数
 float			Float		32			IEEE-754浮点数
 double			Double		64			IEEE-754浮点数
 char			Character	16			无符号Unicode字符
 */
 
 
 
 /**
 1. 检查给定字符串中是否包含数字，若包含，则将其转换为二进制形式
 使用封装类中的转换程序，并捕获异常NumberFormatException
 */
 //把一个字符串转换成Double
 public static void main(String argv[]) {
	 String aNumber = argv[0];
	 double result;
	 try {
		 result = Double.parseDouble(aNumber);
	 } catch(NumberFormatException ex) {
		 System.out.println("Invalid number" + aNumber);
		 return;
	 }
	 System.out.println("Number is " + result);
 }
 
//上面程序只对指定格式有效，比如只能解析Double，如果是int就不能解析，可以搜索字符. d e，若存在则用double，若不存在就用int
public Number process(String s) {
	if (s.matches(".*[.dDeEfF]")) { //因为只用一次，所以不用Pattern， compile， 直接用字符串匹配
		double dValue = Double.parseDouble(s);
		System.out.println("It's a double: " + dValue);
		return new Double(dValue);
	} catch (NumberFormatException e) {
		System.out.println("Invalid a double: " + s);
		return;
	} else {
		try {
			int iValue = Integer.parseInt(s);
			System.out.println("It's an int: " + iValue);
			return new Integer(iValue);
		} catch (NumberFormatException e2) {
			System.out.println("Not a number: " + a);
			return;
		}
	}
}



/**
2. 如何将一个大数据类型的数值赋给一个小数据类型的变量
可以强制类型转换，将int转换为float，double，short，char或byte，必须强制类型转换
*/
public static void main(String argv[]) {
	int i, k;
	double j = 2.75;
	i = j; //编译错误
	i = (int)j; //强制数据类型转换
	
	byte b;
	b = i; //编译错误
	b = (byte)i; //强制数据类型转换
}



/**
3. 数字与对象的相互转换
*/
Integer i = new Integer(42); //int to Integer
System.out.println(i.toString());

int j = j.intValue()； //Integer to int
System.out.println(j);



/**

*/