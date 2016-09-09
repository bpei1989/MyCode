/*
 * 寻找链表的中间结点
 * 快慢指针方法，快的一次走两步，慢的一次走一步，快的到末尾，慢的到中间
 */

public class FindMidofList {
	public Node findMid(Node head) {
		Node p = head;//链表初始化一般都是指向头结点
		Node q = head;
		while(p!=null && p.next!=null && p.next.next!=null) {//注意条件，其实p和pnext不为空也可以
			p = p.next.next;
			q = q.next;
		}
		return q;
	}
}
