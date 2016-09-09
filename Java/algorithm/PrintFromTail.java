/*
 * 从尾到头打印链表
 * 遍历链表入栈，弹出，这正是递归的过程，所以用递归解决
 */
public class PrintFromTail {
	public void print(Node head) {
		if (head != null) {
			print(head.next);
			System.out.println(head.data);
		}
	}
}
