/*
 * 检查链表是否有环
 * 快慢指针，fast一次两步slow一次一步，每次移动fast和slow都比较，当fast等于slow则说明环存在，否则fast到达null
 */
public class IsLoopLink {
	public boolean isLoop(Node head) {
		Node fast = head;
		Node slow = head;
		while (fast!=null && fast.next!=null) {
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				return true;
			}
		}
		return false;
	}
}
