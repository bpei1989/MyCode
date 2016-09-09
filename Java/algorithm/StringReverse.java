import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 先全部swap，再逐个按空格把单词swap，注意需要有begin记录目前已翻转单词和未翻转单词的边界
 * 还可以使用String split，然后存到List中，用Collection.reverse方法
 */
public class StringReverse {
	public static void reverseWord(String str) {
		char[] arr = str.toCharArray();
		swap(arr, 0, arr.length-1);
		int begin = 0;//注意这个变量，记录当前翻转到位置
		for(int i = 1; i < arr.length; i++ ) {//至少从第二个字符开始才可能是空格所以i=1
			if (arr[i] == ' ') {
				swap(arr, begin, i - 1);//i-1,除去空格翻转
				begin = i + 1;//空格后的字符
			}
		}
		swap(arr, begin, arr.length-1);//必须写，最后一个单词翻转
		for(char t : arr) {
			System.out.print(t);
		}
	}
	
	public static void swap(char[] arr, int begin, int end) {
		while(begin < end) {
			char tmp = arr[begin];
			arr[begin] = arr[end];
			arr[end] = tmp;
			begin++;
			end--;
		}
	}
	
	public static void reverseWordWithSDK(String str) {
		String[] arr = str.split(" ");
		List<String> list = new ArrayList<String>();
		for(String t : arr) {
			list.add(t);
		}
		Collections.reverse(list);
		
		for(String t : list) {
			System.out.print(t + " ");
		}
	}
	
	public static void main(String args[]) {
		String words = "I am a Java coder";
		StringReverse.reverseWord(words);
	}
	
}
