package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 83. Remove Duplicates from Sorted List
 *
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 *
 * Example 1:
 *
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 *
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 */

@Google

@LinkedListTag
@Medium
public class RemoveDuplicateFromSortedList {

    /**
     * similar to the question remove the given number, since the list is sorted, just remove do the next.next trick.
     */
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode walker = head;
        int lastCheck = head.val;

        while (walker.next != null) {
            if (lastCheck == walker.next.val) {
                walker.next = walker.next.next;
            } else {
                walker = walker.next;
                lastCheck = walker.val;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.add(2).add(3).add(3).add(4).add(5).add(6).add(6);
        System.out.println(deleteDuplicates(head).toString());
    }
}
