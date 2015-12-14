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
4.确保浮点的准确性
Strictfp —— Java 关键字。
strictfp, 即 strict float point (精确浮点)。
strictfp 关键字可应用于类、接口或方法。使用 strictfp 关键字声明一个方法时，该方法中所有的float和double表达式都严格遵守FP-strict的限制,
符合IEEE-754规范。当对一个类或接口使用 strictfp 关键字时，该类中的所有代码，包括嵌套类型中的初始设定值和代码，都将严格地进行计算。
严格约束意味着所有表达式的结果都必须是 IEEE 754 算法对操作数预期的结果，以单精度和双精度格式表示。
如果你想让你的浮点运算更加精确，而且不会因为不同的硬件平台所执行的结果不一致的话，可以用关键字strictfp.

通常处理器都各自实现浮点运算，各自专业浮点处理器为实现最高速，计算结果会和IEEE标准有细小差别。所以设立‘严格浮点计算strictfp’，
保证在各平台间结果一致，IEEE标准优先，性能其次；

当串行化某个对象时，如果该对象的某个变量是transient，那么这个变量不会被串行化进去,不包括在序列化的表示中。也就是说，假设某个类的成员变量是transient，那么当通过
ObjectOutputStream把这个类的某个实例
保存到磁盘上时，实际上transient变量的值是不会保存的。因为当从磁盘中读出这个对象的时候，对象的该变量会没有被赋值。
*/



/**
5.浮点数的比较
如何判断两个浮点数是否相等
所谓两个浮点数相等，只是在某个允许的误差范围内相等，这个微小的范围就是公差，可用java FloatCmp显示出公差
*/
public static boolean equals (double a, double b, double eps) {
	return Math.abs(a - b) < eps;
}



/**
6. 舍入浮点数，使其成为整数或指定精度的浮点数
强制类型转换只是把尾部去掉，2.99转为2，不能四舍五入，可用Math.round()方法，该方法若给定double，则返回long，给float，返回int
如果不想用默认的舍入规则，则可以编写自己的round方法
*/
static int round(double d) {
	if (d < 0) {
		throw new IllegalArgumentException();
	}
	int di = (int)Math.floor(d); //向下取整
	if ((d - di) > THERSHOLD) {
		return di + 1;
	} else {
		return di;
	}
}



/**
7. 将数字格式化
用NumberFormat子类
NumberFormat类具有静态工厂方法，用于提供数字，货币和百分比的格式化，它们一般返回一个DecimalFormat对象，对该对象采用模式，
可以从工厂方法NumberFormat.getInstance方法回去适合本地用户的一个DecimalFormat对象，并通过方法设置该对象

public abstract class NumberFormat extends Format
method:
public static Locale[] getAvailableLocales() //返回所有语言环境的数组
public static final NumberFormat getInstance() //返回当前默认语言环境的数字格式
public static NumberFormat getInstance(Locale inLocale)//返回指定语言环境的数字格式
public static final NumberFormat getCurrencyInstance()//返回当点默认环境的货币格式
public static NumberFormat getCurrencyInstance(Locale inLocale)//返回指定语言环境的货币格式

MessageFormat 、DateFormat 、NumberFormat 是 Format 三个常用的子类，如果要想进一步完成一个好的国际化程序，则肯定需要同时使用这样三个类完成


DecimalFormat 的基本使用
是NumberFormat 类的子类，主要的作用是用来格式化数字使用，当然，在格式化数字的时候要比直接使用NumberFormat 更加方便，
因为可以直接指定按用户自定义方式进行格式化操作，如果要想进行自定义格式化操作，则必须指定格式化操作的模板。
0	数字	代表阿拉伯数字，每一个0代表一个阿拉伯数字，如果该位不存在则显示0
#	数字	代表阿拉伯数字，每一个#表示一位阿拉伯数字，如果该位不存在则不显示
.	数字	小数点分隔符或货币的小数分隔符
-	数字	代表负号
,	数字	代表分隔符
E	数字	分隔科学计算中的尾数和指数
;	子模式边界	分隔正数和负数子模式
%	前缀或后缀	百分数
\u2030	前缀或后缀	乘以1000并显示为千分数
’	当以上某个字符需要转义时用
其他字符		显示其本身
*/
//NumberFormat
import java.text.* ;
public class NumberFormatDemo01{
    public static void main(String args[]){
        NumberFormat nf = null ;        // 声明一个NumberFormat对象
        nf = NumberFormat.getInstance() ;   // 得到默认的数字格式化显示
        System.out.println("Format as: " + nf.format(10000000)) ;
        System.out.println("Format as: " + nf.format(1000.345)) ;

        NumberFormat format1 = NumberFormat.getCurrencyInstance();
        System.out.println("CurrencyInstance: " + format1.format(10000000000.10));
    }
};
Result：
Format as: 10,000,000
Format as: 1,000.345
CurrencyInstance: $10,000,000,000.10

//DecimalFormat 用法
import java.text.* ;
class FormatDemo{
    public void format1(String pattern,double value){   // 此方法专门用于完成数字的格式化显示
        DecimalFormat df = null ;           // 声明一个DecimalFormat类的对象
        df = new DecimalFormat(pattern) ;   // 实例化对象，传入模板
        String str = df.format(value) ;     // 格式化数字
        System.out.println("Use " + pattern
                + " Format number " + value + ": " + str) ;
    }
};
public class NumberFormatDemo02{
    public static void main(String args[]){
        FormatDemo demo = new FormatDemo() ;    // 格式化对象的类
        demo.format1("###,###.###",111222.34567) ;
        demo.format1("000,000.000",11222.34567) ;
        demo.format1("###,###.###$",111222.34567) ;
        demo.format1("000,000.000$",11222.34567) ;
        demo.format1("##.###%",0.345678) ;
        demo.format1("00.###%",0.0345678) ;
        demo.format1("###.###\u2030",0.345678) ;
    }
};
Result：
Use ###,###.### Format number 111222.34567: 111,222.346
Use 000,000.000 Format number 11222.34567: 011,222.346
Use ###,###.###$ Format number 111222.34567: 111,222.346$
Use 000,000.000$ Format number 11222.34567: 011,222.346$
Use ##.###% Format number 0.345678: 34.568%
Use 00.###% Format number 0.0345678: 03.457%
Use ###.###‰ Format number 0.345678: 345.678‰



/**
8. 进制之间的转换
java.lang.Integer类提供了解决办法，可以用toBinaryString()方法，将一个整数转换成二进制字符串，或用valueOf()方法，将一个二进制字符串转换为整数
Integer.ValueOf()方法比toBinaryString()方法更普遍，它只需改变第二个参数就可以把一个数字串由任何进制转换成int，Integer还提供toBinaryString(),
toOctalString()和toHexString()
String也包括静态方法：valueOf(int), valueOf(double)等。它们提供缺省格式，将给定数值转换为相应的字符串。
*/
String bin = "101010";
System.out.println(bin + " as an integer
 is " + Integer.valueOf(bin, 2));

int i = 42;
System.out.println(i + " as binary digits is " + Integer.toBinaryString(i));



/**
9. 用BitSet处理不连续整数列
*/
import java.util.BitSet;

public class NumSeries {
	BitSet b = new BitSet();
	b.set(0); //January
	b.set(3); //April
	
	for (int i = 0; i<12; i++) {
		if (b.get(i)) {
			System.out.println("Month " + months[i] + " requested");
		}
	}
	
	protected static String months[] = {...} // 1-12
}



/**
10. 产生随机数，三角函数，对数
用java.lang.Math.random()方法产生随机数。
用Math.cos算三角函数
Math类所有方法都是静态的，不需要实例化

对于以e为底的可以用java.lang.Math的log()方法，Math.log（somevalue）
对于其他的可以用Math.log(value)/Math.log(base)
*/

/**
11. 处理特大数字
用java.math中BigInteger或BigDecimal

Java在java.math包中提供的API类BigDecimal，用来对超过16位有效位的数进行精确的运算。双精度浮点型变量double可以处理16位有效数。
在实际应用中，需要对更大或者更小的数进行运算和处理。float和double只能用来做科学计算或者是工程计算，在商业计算中要用java.math.BigDecimal。
BigDecimal所创建的是对象，我们不能使用传统的+、-、*、/等算术运算符直接对其对象进行数学运算，而必须调用其相对应的方法。
方法中的参数也必须是BigDecimal的对象。
BigDecimal一共有4个构造方法
BigDecimal(int) 创建一个具有参数所指定整数值的对象。
BigDecimal(double) 创建一个具有参数所指定双精度值的对象。
BigDecimal(long) 创建一个具有参数所指定长整数值的对象。
BigDecimal(String) 创建一个具有参数所指定以字符串表示的数值的对象。
BigDecimal 的运算方式 不支持 + - * / 这类的运算 它有自己的运算方法
BigDecimal add(BigDecimal augend) 加法运算
BigDecimal subtract(BigDecimal subtrahend) 减法运算
BigDecimal multiply(BigDecimal multiplicand) 乘法运算
BigDecimal divide(BigDecimal divisor) 除法运算
*/
System.out.println("Here's Long.MAX_VAULE: " + Long.MAX_VALUE);
BigInteger bInt = new BigInteger("333333333333333333333333333333333333333333333333333333333333333333333");
System.out.println("Here's a bigger number: " + bInt);
System.out.println("Here it is as a double: " + bInt.doubleValue());
