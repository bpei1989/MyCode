/*
 * 写排序算法，需要对各种数组边界特别注意，需要保存的保存，++等操作也不能忽视
 * 先把待排序的序列分成多个子序列，每个子序列都有序，再把子序列合并
 * 首先要计算中间下标，mid=(low+high)/2,然后分别对mid左右做排序，递归到只有一个元素时返回，最后merge
 * 需要一个临时的tmp数组和待排序数组一样大，注意当一个数组全部并入，另一个数组还剩着时的处理
 * 归并是需要把low，mid，high全传入，因为需要知道左右两边数组的边界
 * 真正写的时候注意判空合法等
 */
public class MergeSort {
	public static void mergeSort(int[] nums, int low, int high) {
		int mid = (low + high) / 2;
		//写排序算法，务必注意各种left<right之类的判断，几乎所有操作都需要这个条件
		if (low < high) {//必须要有这个条件，否则会出现没有返回条件的情况，栈溢出时
			mergeSort(nums, low, mid); //这个不用-1，没有支点
			mergeSort(nums, mid+1, high);
			merge(nums, low, mid, high);
		}
	}
	
	private static void merge(int[] nums, int low, int mid, int high) {
		int[] tmp = new int[high -low + 1];
		//这里的low，high，mid需要固定，不像快速left和right是动态的，所以不需要先保存
		int i = low;
		int j = mid+1;
		int k = 0;
		
		while((i <= mid) && (j <= high)) {
			if (nums[i] < nums[j]) {
				tmp[k++] = nums[i++];//注意++
			} else {
				tmp[k++] = nums[j++];//注意++
			}
		}
		
		//左边剩余
		while(i <= mid) {
			tmp[k++] = nums[i++];//注意++
		}
		
		//右边剩余
		while(j <= high) {
			tmp[k++] = nums[j++];//注意++
		}
		
		for(int m = 0; m < tmp.length; m++) {
			nums[m + low] = tmp[m];//注意，这里需要+low，因为不是最后那次，中间的low不为0，需要加
		}
	}
	
	public static void main(String args[]) {
		int[] nums = {2, 7, 8, 3, 1, 6, 9, 0, 5, 4};
		MergeSort.mergeSort(nums, 0, nums.length-1);
		for(int i : nums) {
			System.out.print(i + " ");
		}
	}
}
