/*
 * 删除链表中重复数据
 * 可以遍历链表把值存在HashSet中，如果当前值在Set中已经存在则删除，简单，但需要额外空间
 * 双重循环不需要额外空间，外循环遍历链表，内存换从当前节点开始向后去重
 */
public class DeleteDupInLink {
	public void deleteDup(Node head) {
		Node p = head;
		while(p != null) {
			Node q = p;
			while(q.next != null) {
				if (p.data == q.next.data) {//找和p数据一样的删除
					q.next = q.next.next;
				} else 
					q = q.next;
			}
		}
		p = p.next;
	}
}

class Node {
	Node next = null;
	int data;
	public Node(int data) {this.data = data;}
}
