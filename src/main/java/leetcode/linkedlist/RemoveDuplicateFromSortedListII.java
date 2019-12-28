package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 82. Remove Duplicates from Sorted List II
 *
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * Example 1:
 *
 * Input: 1->2->3->3->4->4->5
 * Output: 1->2->5
 * Example 2:
 *
 * Input: 1->1->1->2->3
 * Output: 2->3
 */

@Medium
@LinkedListTag
public class RemoveDuplicateFromSortedListII {

    /**
     * key compare to the fist question is cache the previous node
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode walker = dummyHead.next;
        ListNode pre = dummyHead;
        int lastCheck = head.val;

        while (walker.next != null) {

            boolean skip = false;
            while (walker.next != null && lastCheck == walker.next.val) {
                walker.next = walker.next.next;
                skip = true;
            }

            if (skip) {
                // ***** most important step ******
                pre.next = walker.next;
                walker = pre;
                lastCheck = pre.val;
            } else {
                // ***** most important step ******
                pre = walker;
                walker = walker.next;
                lastCheck = walker.val;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.add(2).add(4).add(4).add(5).add(6).add(6).add(6);
        System.out.println(deleteDuplicates(head).toString());
    }
}
