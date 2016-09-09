/*
 * 统计一行字符中有多少个单词，连续的多个空格算一个空格，开头空格不算
 * 思路：如果一个字符为非空格，而它的前面是空格，则是一个单词，单词计数器count++，如果当前字符非空格
 * 而且其前面字符也不是空格，则count不变；前面字符是否为空格用isBlank标识
 */
public class CntWordNum {
	public static int wordCounter(String s) {
		boolean isBlank = true;
		int count = 0;
		for (int i=0; i<s.length(); i++) {
			if(s.charAt(i) == ' ')
				isBlank = true;
			else if (isBlank == true) {
				isBlank = false;
				count++;
			}
		}
		return count;
	}
	
	public static void main(String args[]) {
		String s = " hello  how are you";
		System.out.println(s);
		System.out.println(wordCounter(s));
	}
}
