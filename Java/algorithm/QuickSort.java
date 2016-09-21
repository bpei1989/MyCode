/*
 * 以支点为准，分两边，然后递归
 * 注意每次都要判断左下标是否小于右下标，如果while循环是由于left=right跳出的，这时再对left++就错了
 * 还有交换后需要++或--，分边函数出去时，需要把支点放到中间，返回这个下标值
 * 真正写的时候注意判空合法等
 */
public class QuickSort {
	public static void quickSort(int[] list, int left, int right) {
		int middle;
		if (left < right) {
			middle = partition(list, left, right);
			quickSort(list, left, middle-1);
			quickSort(list, middle + 1, right);
		}
	}
	
	public static int partition(int[] list, int left, int right) {
		int pivot = list[left];
		while (left < right) {
			while((left < right) && (list[right] > pivot)) {
				right--;
			}
			//这里必须加判断，否则上面while退出可能由于left < right，不加判断就执行赋值会出错
			if (left < right) { 
				list[left] = list[right];
				left ++; //赋值后需要向指向下一个
			}
			while((left < right) && (list[left] < pivot)) {
				left ++;
			}
			//这里必须加判断，否则上面while退出可能由于left < right，不加判断就执行赋值会出错
			if (left < right) {
				list[right] = list[left];
				right --;//赋值后需要向指向前一个
			}
		}
		list[left] = pivot;
		return left;
	}
	
	public static void main(String args[]) {
		System.out.println("in");
		int[] list = {1,3,5,2,11,13,15,12,12};
		QuickSort.quickSort(list, 0, list.length-1);
        for(int i=0; i<list.length; i++){  
            System.out.print(list[i]+" ");  
        }  
	}
}
