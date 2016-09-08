/*
 * 堆排序中有几个点：有序区域，无序区域，无序区建堆后堆顶交换到无序区域的最后一个位置，交换之后这里变成有序区域
 * 无序区建堆首先找到无序区最后一个位置的父节点，(lastIndex-1)/2，然后依次对该节点和之前的节点执行下面操作
 * 找到该节点和它的左右子节点比较，调整
 * 左右子节点2*i+1, 2*i+2；调整时如果父子节点交换，需要把父节点的位置置为交换后的子节点位置
 * 其实就是一个从最右下角的子节点开始找到父节点，调整，再逐个调整逐个父节点之前的左右节点，期间如果发生父子交换，
 * 则重新记录父节点位置，重新建立父节点和其子节点为堆
 * 真正写的时候注意判空合法等
 */
public class HeapSort {
	
	//数组分无序区域和有序区域，有序区域是逐个从堆顶拿出的数据到有序区形成的，无序每次拿出堆顶后就会重建堆
	public static void heapSort(int[] data) {
		for (int i=0; i < data.length; i++) {
			//对无序区生成堆
			createMaxHeap(data, data.length-1-i);
			//把堆顶和无序区最后一个数据交换，这时堆顶并入有序区
			swap(data, 0, data.length-1-i);
		}
	}
	
	//循环从lastIndex处开始取父节点，(index-1)/2，父节点依次和左右子节点比较，调整
	public static void createMaxHeap(int[] data, int lastIndex) {//lastIndex是无序区最后一个位置
		//对各个父节点执行调整
		for (int i = (lastIndex - 1)/2; i >= 0; i--) {
			int k = i; //保存lastIndex的父节点位置
			//和子节点比较,2*i+1, 2*i+2分别是左右孩子节点
			while(2*k+1 <= lastIndex) {
				//biggerIndex表示当前最大节点的位置，初始化为左孩子节点
				int biggerIndex = 2*k + 1;
				//说明有右孩子节点
				if (biggerIndex < lastIndex) {
					//如果左孩子小于右孩子则把最大节点位置记录为右孩子节点
					if (data[biggerIndex] < data[biggerIndex+1]) {
						biggerIndex++;
					}
				}
				//如果父节点小于当前最大节点，则交换，因为是大顶堆，父节点要最大
				if (data[k] < data[biggerIndex]) {
					swap(data, k, biggerIndex);
					//把父节点的位置改为左右孩子中最大的那个的位置，重新往下调整
					//必须这样做，因为上层的的父节点如果沉下来到子节点，可能影响原本已经调整好的子节点的堆
					//比如原来堆顶是9，左孩子是8，祖父节点3，调整后父节点升上去了，祖父节点9，父节点为3了，得重新调整
					k = biggerIndex;
				} else {
					//父节点大于两个子节点中的最大值，说明这个区域已经是大顶堆
					break;
				}
			}
		}	
	}
	
	private static void swap(int[] data, int i, int j) {
		//必须有这个判断，否则可能会导致0，，操作后data[i] data[j]成0了
        if (i == j) {  
            return;  
        }  
		data[i] = data[i] + data[j];
		data[j] = data[i] - data[j];
		data[i] = data[i] - data[j];
	}
	
	public static void main(String args[]) {
		int[] data = {5, 3, 6, 2, 1, 9, 4, 8, 7, 9, 11};
		HeapSort.heapSort(data);
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
	}
}
