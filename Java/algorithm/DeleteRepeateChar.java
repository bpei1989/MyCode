/*
 * 删除字符串中重复的字符，good变god
 * bitmpa(int数组，整除，求余，左移)记录该字符是否出现过，如果出现过就置为\0，最后遍历数组去除\0
 */
public class DeleteRepeateChar {
	public static String removeDuplicate(String str) {
		char[] c = str.toCharArray();
		int len=c.length;
		int[] flags = new int[8];//8*32=256bit，刚好够ascii的数目
		int i;
		for(i=0; i<8; i++) {
			flags[i] = 0;
		}
		for(i=0; i<len; i++) {
			int index=(int)c[i]/32;//整除找到数组下标
			int shift=(int)c[i]%32;//求余找到具体bit
			if((flags[index]&(1<<shift))!=0)//bit为1说明出现过
				c[i]='\0';
			else
				flags[index] |= (1 << shift);//如果没出现过，则标记为出现
		}
		int j=0;
		for(i=0; i<len; i++) {
			if(c[i] != '\0') {
				c[j++] = c[i];
			}
		}
		return new String(c, 0, j);
	}
	
	public static void main(String[] args) {
		String str = "abclaabcd";
		str = removeDuplicate(str);
		System.out.print(str);
	}
}
