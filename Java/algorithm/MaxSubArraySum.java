/*
 * 求最大连续子数组之和
 * 思路：变量curSum表示目前子数组之和，如果小于0，则重新开始计算，否则和后面数字相加
 * 		maxSum表示当前最大和，如果curSum大于maxSum则更新
 * 
 */
public class MaxSubArraySum {
	public static int maxSubArr(int arr[]) {
		int curSum = 0;
		int maxSum = 0;
		for (int i=0; i<arr.length; i++) {
			if (curSum <= 0) {//负数和后面加会减少子数组和，放弃
				curSum = arr[i];
			} else {
				curSum = curSum + arr[i];
			}
			if (curSum > maxSum) {
				maxSum = curSum;
			}
		}
		return maxSum;
	}
}
