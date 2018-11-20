package leetcode.linkedlist;

import baseObj.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * 
 * For example: 
 * Given 1->2->3->4->5->NULL and k = 2, 
 * return 4->5->1->2->3->NULL.
 * 
 * 
 */
public class RotateList
{
    public static ListNode rotateRight(ListNode head, int k)
    {
        if (head == null || head.next == null || k == 0)
        {
            return head;
        }

        // find tail first and find the lenght btw
        ListNode tail = head;
        int len = 1;
        while (tail.next != null)
        {
            tail = tail.next;
            len++;
        }

        // This is for k > list len... get the actually steps
        k = k%len;

        tail.next = head;
        
        //   1->2->3
        //   ^     |
        //   5 <- 4
        
        for (int i = 0; i < len - k; i++)
        {
            tail = tail.next;
        }
        head = tail.next;
        tail.next = null;
        
        return head;
    }
}
