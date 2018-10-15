package leetcode.datastructure;

import baseObj.ListNode;

/**
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, 
 * val = 6 Return: 1 --> 2 --> 3 --> 4 --> 5
 */
public class DeleteAllSameValue
{
    public static ListNode removeElements(ListNode head, int val)
    {
        if (null == head)
        {
            return null;
        }

        ListNode pointer = head;
        while (pointer.next != null)
        {
            if (pointer.next.val == val)
            {
                pointer.next = pointer.next.next;
            }
            else 
            {
                pointer = pointer.next;
            }
        }

        // head.next is now guarantee not null
        return head.val == val ? head.next : head;
    }
}
