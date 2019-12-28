package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 19 Remove Nth Node From End of List
 *
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 */

@Medium
@LinkedListTag
public class RemoveNthNodeFromEndOflist {

    /**
     * a hidden faster pointer slow point problem
     *
     * use the faster point to get to n, and then moving slow pointer, so the distance is always n
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (null == head) return null;

        // Deal with input == 1
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode fast = start;
        ListNode slow = start;

        for (int i = 1; i <= n+1; i++) {
            fast = fast.next;
        }

        while(fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // delete
        slow.next = slow.next.next;

        return start.next;
    }
}
