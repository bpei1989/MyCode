/**
 * Created by bpei
 */

/**
1. 查看当前日期
用Date对象的toString方法，还可以用Calendar.getInstance().getTime()方法获取一个Date对象，然后调用其toString方法
*/



/**
2. 按照指定格式打印日期/时间
用java.text.DateFormat对象
无论那个区域Locale，只要用缺省的DateFormat格式化，即DateFormat.getInstance()方法获得，就可以按照正确的格式打印日期：
如果需要特定格式，则用SimpleDateFormat，如new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz")
a是AM或PM，zzz是时区
*/
Date today = new Date();
DateFormat df = DateFormat.getInstance();
System.out.println(df.format(today));
DateFormat df_f = DateFormat.getDateInstance(DateFormat.FULL, Locale.FRENCH);//打印法国时间
System.out.println(df_f.format(today));

Result：
12/14/15 4:55 PM
lundi 14 décembre 2015



/**
3. 将年月日时分秒转换为一个Calendar对象或Date对象
用Calendar类的set(y,m,d,h,m,[,s])方法，注意这里面月是从零开始的，其他保持不变
GregorianCalendar 是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统。 
GregorianCalendar 是一种混合日历，可由调用者通过调用 setGregorianChange() 来更改起始日期
*/
GregorianCalendar d1 = new GregorianCalendar(1986, 04, 05); //5月5日，因为月从0开始
GregorianCalendar d2 = new GregorianCalendar();//today



/**
4. 将字符串转换为Date对象或Calendar对象
用DateFormat类，parse方法可以根据DateFormat对象保存的格式，解析一个字符串
*/
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
String input = "1818-11-11";
Date t;
try {
	t = formatter.parse(input);
	System.out.println(t);
} catch (ParseException e) {
	System.out.println("unparseable");
}
Result: Wed Nov 11 00:00:00 CST 1818



/**
5. 将秒数，1970后，转成日期
用java.util.Date()
*/
Date d = new Date(10000);//注意，参数是毫秒



/**
6. 日期加减运算
Date类有个getTime()方法，返回长整型的秒数，对该数值进行运算
*/
Date now = new Date();
long t = now.getTime();
t -= 700*24*60*60*1000;
Date then = new Date(t);



/**
7. 计算日期间的间隔
Date对象的getTime()转为长整型，将差值格式化
*/
public class DateDiff {
	public static void main(String[] av) {
		Date d1 = new GregorianCalendar(2000,11,31,23,59).getTime();
		Date today = new Date();
		
		long diff = today.getTime() - d1.getTime();
		System.out.println("The 21st century upto today is " + (diff/(1000*60*60*24)) + " days");
	}
}



/**
8. 如何比较两个日期
若日期为Date对象，可用equals，before，after方法，如果日期为长整型，可以用==，<,>
起始Date对象可以getTime，然后==，<,>
*/



/**
9. 第几日
用Calendar类的get方法，基本所有的日期都支持
*/
Calendar c = Calendar.getInstance();
System.out.println("Year: " + c.get(Calendar.YEAR));
System.out.println("Month: " + c.get(Calendar.MONTH));
System.out.println("Day of week: " + c.get(Calendar.DAY_OF_WEEK));



/**
10. 测量事情所花费的时间
可以两次调用System.currentTimeMillis()方法或System.nanoTime()，然后做相减，nanoTime是毫微秒
*/
long start = System.currentTimeMillis();//注意是long
method_to_be_executed();
long end = System.currentTimeMillis();
long elasped = end - start;



/**
11. 休眠
Thread.sleep
*/
Thread.sleep(100*1000);//毫秒，所以需要*1000转成秒
