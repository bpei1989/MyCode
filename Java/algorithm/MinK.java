/*
 * 找数组第K小的数字
 * 做快排的分组操作左边小右边大，如果支点的位置小于k-1，则第k小在右边，则继续在右边找，递归。注意，是支点的位置
 */
public class MinK {
	public static int findMinK(int arr[], int low, int high, int k) {// 注意把k传入
		if (low > high) {
			return -1;
		}
		int mid = partition(arr, low, high);
		if (mid == k - 1) {
			return arr[mid];
		} else if (mid < k - 1)
			return findMinK(arr, mid + 1, high, k);
		else
			return findMinK(arr, low, mid - 1, k);

	}

	private static int partition(int arr[], int left, int right) {

		int pivot = arr[left];
		while (left < right) {
			while (left < right && arr[right] >= pivot) {
				right--;
			}
			if (left < right)
				arr[left++] = arr[right];
			while (left < right && arr[left] < pivot)
				left++;
			if (left < right)
				arr[right--] = arr[left];
		}
		arr[left] = pivot;
		return left;
	}

	public static void main(String args[]) {
		int arr[] = { 1, 2, 4, 3, 5, 8, 9, 7, 6 };
		int k = findMinK(arr, 0, arr.length - 1, 3);
		System.out.println(k);
	}
}
