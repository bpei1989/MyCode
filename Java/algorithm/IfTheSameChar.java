/*
 * 判断两个字符串是否由相同的字符组成 
 * ascii有266个，申请一个长度为266的int数组，把第一个串的每个字符ascii码下标加1，第二个串每个字符ascii码下标减一
 * 如果数组都是0说明两个字符串是由相同的字符组成
 */
public class IfTheSameChar {
	public static boolean compare(String s1, String s2) {
		byte[] b1 = s1.getBytes();//转为byte才能进行ascii计算
		byte[] b2 = s1.getBytes();
		int[] bCount = new int[256];
		for(int i=0; i<256; i++) {//初始化为0
			bCount[i] = 0;
		}
		for (int i=0; i<b1.length; i++)
			bCount[b1[i] - '0']++;//相对于0的ascii才是0-255
		for (int i=0; i<b2.length; i++)
			bCount[b2[i] - '0']--;
		for(int i=0; i<256; i++) {
			if(bCount[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]) {
		String s1 = "abcefff";
		String s2 = "fffeabc";
		System.out.print(compare(s1, s2));
	}
}
