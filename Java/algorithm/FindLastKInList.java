/*
 * 链表中找倒数第k个元素
 * 两个指针一个指针比另一个指针块k-1步，第一个指针到NULL，则慢的那个指针到倒数第K
 */
public class FindLastKInList {
	public Node findLastK(Node head, int k) {
		Node p1 = head;
		Node p2 = head;
		for (int i=0; i<k-1; i++) {//注意是k-1
			p1 = p1.next;
		}
		while(p1 != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
}

