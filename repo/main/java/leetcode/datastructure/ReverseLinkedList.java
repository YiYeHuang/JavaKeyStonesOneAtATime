package leetcode.datastructure;

import basic.ListNode;

public class ReverseLinkedList
{
    public ListNode reverseListRecr(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode nextNode=head.next;
        ListNode newHead=reverseListRecr(nextNode);
        nextNode.next=head;
        head.next=null;
        return newHead;
    }

    /**
     * 
     * 1. Jump to next node,
     * 2. point current node to previous node
     * 3. point previous node to current node to iterate to next
     * 4. move current node to next node.
     */
    
    public ListNode reverseListInplace(ListNode head)
    {
        ListNode currentNode = head;
        ListNode previous = null;
        ListNode nextNode = null;
        while (currentNode != null)
        {
            nextNode = currentNode.next;
            currentNode.next = previous;
            previous = currentNode;
            currentNode = nextNode;
        }
        return previous;
    }
}
