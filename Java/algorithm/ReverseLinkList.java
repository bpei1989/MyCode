/*
 * 反转链表
 * 注意相邻三个节点，i，m，n，如果反转了i，则next指针就会改变，这时需要先保存下一个节点，再反转，防止链表反转时断开
 * 核心是三个指针，pre，cur，next，先保存当前节点的下一个节点到next，然后cur的next指向pre，pre=cur， cur=next
 * 最后next是null，则保存为头结点
 */
public class ReverseLinkList {
	public void reverse(Node head) {
		Node pReserveHead = head;
		Node pNode = head;
		Node pPrev = null;
		while(pNode != null) {
			Node pNext = pNode.next;//保存next防止断裂
			if(pNext == null)//最后一个节点就是反转后的头结点
				pReserveHead = pNode;
			pNode.next = pPrev;
			pPrev = pNode;
			pNode = pNext;
		}
	}
}
