/*
 * 输出字符串的所有组合
 * 如abc：a，b，c，ab，ac，bc，abc等
 * 思路是递归，但递归有效率问题
 * （不懂，先了解递归写法）可以构造一个长n的01字符串，表示输出结果中是否包含某个字符，如001标识没有ab，只有c，则题目变为输出001到111组合
 */
public class GetAllKindsOfCombine {
	public static void combineRecursively(char[] c, int begin, int len, StringBuffer sb) {
		//到最后
		if (len == 0) {
			//System.out.print(sb + " ");
		}
		//只有一个字符时
		if (begin == c.length) {
			return;
		}
		//先a
		sb.append(c[begin]);
		System.out.println("sb now: " + sb.toString());
		//再bc，然后删掉b，再ac
		combineRecursively(c, begin + 1, len - 1, sb);
		sb.deleteCharAt(sb.length()-1);
		System.out.println("sb delete:" + sb.toString());
		combineRecursively(c, begin+1, len, sb);
	}
	
	public static void main(String[] args) {
		String s = "abc";
		char[] c = s.toCharArray();
		StringBuffer sb = new StringBuffer("");
		int len = c.length;
		for (int i=1; i<=len; i++) {
			combineRecursively(c, 0, i, sb);
		}
	}
}
 