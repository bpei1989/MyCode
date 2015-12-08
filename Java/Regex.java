/**
 * Created by Bing Pei.
 */
 
 
 /**
 因为regex要解析的字符串会被javac编译，所以，对于任何特殊字符，包括反斜线，双引号之类，可能需要两级转义
 正则表达式 "You said it \." 必须写成这样的Java字符串： "\"You said it \\.\""   还有\d+ \w+都需要多加一个\
 
 
 速记
.
[ ]
^
$
四个字符是所有语言都支持的正则表达式，所以这个四个是基础的正则表达式。正则难理解因为里面有一个等价的概念，这个概念大大增加了理解难度，
让很多初学者看起来会蒙，如果把等价都恢复成原始写法，自己书写正则就超级简单了，就像说话一样去写你的正则了：

　　等价：
　　?,*,+,\d,\w 都是等价字符
　　?等价于匹配长度{0,1}
　　*等价于匹配长度{0,} 
　　+等价于匹配长度{1,}
　　\d等价于[0-9]
　　\w等价于[A-Za-z_0-9]。

常用运算符与表达式：
　　^ 开始
　　（） 域段
　　[] 包含,默认是一个字符长度
　　[^] 不包含,默认是一个字符长度
　　{n,m} 匹配长度，即进行n-m次匹配，\d｛1，3｝，1-3个数字
　　. 任何单个字符(\. 字符点)
　　| 或
　　\ 转义
　　$ 结尾
　　[A-Z] 26个大写字母
　　[a-z] 26个小写字母
　　[0-9] 0至9数字
[A-Za-z0-9] 26个大写字母、26个小写字母和0至9数字

　　分割语法：
　　[A,H,T,W] 包含A或H或T或W字母
　　[a,h,t,w] 包含a或h或t或w字母
　　[0,3,6,8] 包含0或3或6或8数字

　　语法与释义：
　　基础语法 "^([]{})([]{})([]{})$"
　　正则字符串 = "开始（[包含内容]{长度}）（[包含内容]{长度}）（[包含内容]{长度}）结束" 

　　?,*,+,\d,\w 这些都是简写的,完全可以用[]和{}代替，在(?:)(?=)(?!)(?<=)(?<!)(?i)(*?)(+?)这种特殊组合情况下除外。
　　初学者可以忽略?,*,+,\d,\w一些简写标示符，学会了基础使用再按表自己去等价替换


　　实例：
　　字符串；tel:086-0666-88810009999
　　原始正则："^tel:[0-9]{1,3}-[0][0-9]{2,3}-[0-9]{8,11}$" 
　　速记理解：开始 "tel:普通文本"[0-9数字]{1至3位}"-普通文本"[0数字][0-9数字]{2至3位}"-普通文本"[0-9数字]{8至11位} 结束"
　　等价简写后正则写法："^tel:\d{1,3}-[0]\d{2,3}-\d{8,11}$" ，简写语法不是所有语言都支持。
 */
 
 
 
 /**
 1.在Java中使用正则表达式
 使用java.util.regex
 如果判断给定字符串是否匹配某个字符串，只需使用String类的matches()方法，其参数是正则表达式，如果字符串符合则返回true，否则返回false
 if (inputString.matches(StringRegexPattern)) {
	 ...
 }
 
 这种用法适用于只匹配一次的情况， 如果正则表达式在程序中多次使用匹配，则用Pattern和Mathcer更有效。
下面这段程序三步只用在“标准”模式中匹配，如果程序只是用了一次regex可倾向于用这种模式，如果超过一次，就该编译执行了，编译后运行更快
 */
 //String convenience匹配
 String pattern = ".Q[^u]\\d+\\..*"; //pattern需要两次转义
 String line = "Order QT300. Now!";
 if (line.matches(pattern)) {
	 System.out.println("match");
 }
 
 /**
 2. 查找匹配文本
 正则匹配的步骤：
 a. 通过调用静态方法Patter.compile(regex)创建一个模式
 b. 为每个String调用pattern.matcher(String)从模式中请求一个Matcher
 c. 在结果Matcher中调用一次或多次finder方法
 Matcher有多个finder方法，灵活
 boolean matches(): 
尝试将整个区域与模式匹配。
如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
返回：
当且仅当整个区域序列匹配此匹配器的模式时才返回 true。
 
boolean lookingAt(): 
尝试将从区域开头开始的输入序列与该模式匹配。
与 matches 方法类似，此方法始终从区域的开头开始；与之不同的是，它不需要匹配整个区域。
如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
返回：
当且仅当输入序列的前缀匹配此匹配器的模式时才返回 true。
 
boolean find(): 
尝试查找与该模式匹配的输入序列的下一个子序列。
此方法从匹配器区域的开头开始，如果该方法的前一次调用成功了并且从那时开始匹配器没有被重置，则从以前匹配操作没有匹配的第一个字符开始。
如果匹配成功，则可以通过 start、end 和 group 方法获取更多信息。
返回：
当且仅当输入序列的子序列匹配此匹配器的模式时才返回 true。
 
boolean find(int start): 
重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列。
如果匹配成功，则可通过 start、end 和 group 方法获取更多信息，而 find() 方法的后续调用将从此匹配操作未匹配的第一个字符开始。
返回：
当且仅当从给定索引开始的输入序列的子序列匹配此匹配器的模式时才返回 true。
抛出：
IndexOutOfBoundsException- 如果开始点小于零或大于输入序列的长度。

成功执行以上方法中一个后，可以用下列方法获得匹配信息
start(), end(): 返回字符串中匹配的开始和结束字符的位置信息
groupCount()： 返回用括号括起来的获取的分组数，如果没有分组则返回0
group(i): 如果i小于或等于groupCount的值，则返回与分组i匹配的字符，Group0表示完全匹配，所以group(0)或group()返回匹配的整个字符串

regex处理中，圆括号中的内容最先处理，正则表达式可能会出现复杂的多次嵌套，调用group(int)方法可以获得指定嵌套层所匹配的内容。如果没有使用圆括号，则视为0层
 */
 //0层regex
 String patt = "Q[^u]\\d+\\.";
 Pattern r = Pattern.compile(patt);
 String line = "Order QT300. Now!";
 Matcher m = r.matcher(line);
 if (m.find()) {
	 System.out.println(patt + " matches \"" + m.group(0) + "\" in \"" + line + "\""); //group(0)是匹配段，不是整个待匹配字符串
	 System.out.println(patt + " matches \"" + line.substring(m.start(0), m.end(0)) + "\" in \"" + line + "\"");
 }
 
 Result:
 Q[^u]\\d+\\. matches "QT300." in "Order QT300. Now!"
 Q[^u]\\d+\\. matches "QT300." in "Order QT300. Now!"
 
 //多层regex
 /*
 从字符串里匹配单词，输入字符串：
 Smith， John
 Adams， John Quincy
 希望得到结果：
 John Smith
 John Quincy Adams
 */
 Pattern r = Pattern.compile("(.*), (.*)"); //构造两组圆括号，分别匹配两个域
 Matcher m = r.matcher(inputLine);
 if(m.matches()) {
	 System.out.println(m.group(2) + ' ' + m.group(1));
 }
 
 
 
 /**
 3. 替换匹配文本
 replaceAll(newString): 替换全部符合正则表达式的结果为newString
 appendReplacement(StringBuffer, newString): 将第一匹配字符前的字符复制到StringBuffer，再加上newString
 appendTail(StringBuffer): 将最后一个匹配后的文本输入到StringBuffer(通常与appendReplacement一起用)
 reset()：重置匹配位置
 */
 //纠正demon等错误写法，改为daemon
 String patt = "d[ae]{1,2}mon";
 String input = "Unix demons running";
 
 Pattern r = Pattern.compile(patt);
 Matcher m = r.matcher(input);
 System.out.println("Replaceall: " + m.replaceAll("daemon")); //注意是Matcher对象调用replaceAll方法，参数是替换成什么字符串
 
 //后面这段和前面功能基本一样，就是展示了appendXXX用法
 m.reset(); //matches(),find()等都会影响标记,而reset()可以重置这些标记,把m重置为最初状态（Matcher m = r.matcher(input);）
 StringBuffer sb = new StringBuffer();
 System.out.print("Append methods: ");
 while(m.find()) {
	 m.appendReplacement(sb, "daemon"); //第一个匹配前的字符复制到sb，然后再加上单词deamon
 }
 m.appendTail(sb); //复制剩下的部分到sb，和appendReplacement合用
 System.out.println(sb.toString());
 
 
 
 /**
 4.打印所有匹配的字符串
 从一个或多个文件中找到与正则表达式匹配的字符串
 可以一次一行读取文件，如果右匹配则摘录出来，还可以用NIO，把文件map到内存，直接全部匹配
 
 nio 是New IO 的简称，在jdk1.4 里提供的新api 。Sun 官方标榜的特性如下： 为所有的原始类型提供(Buffer)缓存支持。字符集编码解码解决方案。
 Channel ：一个新的原始I/O 抽象。 支持锁和内存映射文件的文件访问接口。 提供多路(non-bloking) 非阻塞式的高伸缩性网络I/O 。
 */
 //Demo1，一次一行匹配
 import java.util.regex.*;
 import java.io.*;
 
 public class ReaderIter{
	 public static void main(String args) throws IOException {
		 Pattern patt = Pattern.compile("[A-Za-z][a-z]+");
		 BufferedReader r = new BufferedReader(new FileReader(args[0])); //可以这样连写，简洁
		 
		 String line;
		 while(line = r.readLine() ! = null) {
			 Matcher m = patt.matcher(line); //每次匹配都需要重置Matcher，可以把Matcher理解为一种标志，每次向后移，如果匹配新的读入就需要重置
			 while(m.find()) {
				 System.out.println(group(0)); //最简单的方法
				 
				 int start = m.start(0);
				 int end = m.end(0);
				 System.out.println("start = " + start + "; end = " + end);
				 System.out.println(line.substring(start, end));
			 }
		 }
	 }
 }
 
 //Demo2, 用NIO，把文件读到内存，然后匹配，这个效率比Demo1高，Demo1需要在每次匹配时重置Matcher
 import java.io.*;
 import java.nio.*;
 import java.nio.channels.*;
 import java.nio.charset.*;
 import java.util.regex.*;
 
 //类似grep的程序，需要使用命令行形式
 public class GrepNIO{
	 public static void main(String[] args) throws IOException {
		 if (args.length < 2) {
			 System.err.println("Usage: GrepNIO patt file [...]");
			 System.exit(1);
		 }
		 
		 Pattern p = Pattern.compile(args[0]);
		 for (int i=1; i<args.length; i++)
			 process(p, args[i]);
	 }
	 
	 static void process(Pattern pattern, String fileName) throws IOException {
		 FileChannel fc = new FileInputStream(fileName).getChannel(); //从给定文件中获得一个FileChannel
		 ByteBuffer buf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()); //映射文件内容
		 CharBuffer cbuf = Charset.forName("ISO-8859-1").newDecoder().decode(buf); //将ByteBuffer解码为CharBuffer
		 
		 Matcher m = pattern.matcher(cbuf);
		 while(m.find()) {
			 System.out.println(m.group(0));
		 }
	 }
 }
 
 
 
 /**
 
 */