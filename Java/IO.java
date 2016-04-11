1. 从标准输入读取数据
#可以用Scanner
Scanner sc = Scanner.create(System.in);
	int = sc.nextInt();

#用Reader
import java.io.*;

public class ReadStdinInt {
	public static void main(String[] ap) {
		String line = null;
		int val = 0;
		try {
			BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
			line = is.readLine()
			val = Integer.parseInt(line);
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + line);
		} catch(IOException e) {
			System.err.println("Unexpected IO error");
		}
		System.out.println("I read this number: " + val);
	}
}


2. 向标准输出写数据
#System.out，是一个PrintStream


3. Formatter类
java.util.Formatter 类提供了布局合理性和对齐方式，常见格式为数字，字符串和日期/时间数据，以及语言环境的输出的支持。
格式化并不一定是安全的多线程访问。线程安全是可选的，在这个类方法由用户自已定义
