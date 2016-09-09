/*
 * 判断两个链表是否相交,若相交则找到第一个节点的位置
 * 如果两个链表相交，则一定有相同的尾节点；第一个相交节点可以让长的链表先走len1-len2个节点，然后和短的一起走，找到第一个相同的节点
 */
public class IsConnect {
	int len1 = 0;
	int len2 = 0;
	//用找第一个相交节点需要len
	public boolean isConnect(Node h1, Node h2) {
		Node tail1 = h1;
		while(tail1.next != null) {
			tail1 = tail1.next;
			len1++;
		}
		Node tail2 = h2;
		while(tail2.next != null) {
			tail2 = tail2.next;
			len2++;
		}
		return tail1 == tail2;
	}
	
	public Node theFistNode(Node h1, Node h2) {
		Node t1 = h1;
		Node t2 = h2;
		if (!isConnect(h1, h2))
			return null;
		if (len1 > len2) {
			int d = len1 - len2;
			while (d!=0) {
				t1 = t1.next;
				d--;
			}
		} else {
			int d = len2 - len1;
			while(d!=0) {
				t2 = t2.next;
				d--;
			}
		}
		while (t1!=t2) {
			t1 = t1.next;
			t2 = t2.next;
		}
		return t1;
	}
}
