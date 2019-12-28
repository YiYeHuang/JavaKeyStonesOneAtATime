package leetcode.sort;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Sorting;

/**
 * 148. Sort List
 *
 * Sort a linked list in O(n log n) time using constant space complexity.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */

@Medium
@Sorting
public class SortList {
    /**
     * follow the merge sort algorithm, merge is straight forward,
     *
     * split need some trick, use the faster and slower pointer tricky in finding linkedlist cycle, to two split array
     *
     * Always use temp node walker for linkedlist questions
     */
    public ListNode sortList(ListNode head) {
        // to avoid faster pointer get null pointer exception
        if (head == null || head.next == null) {
            return head;
        }

        // 1. split the list
        ListNode leftTail = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            leftTail = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // break the link
        leftTail.next = null;

        //from here it is very similar to the merge sort code
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // merge
        return merge(left, right);
    }

    private ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode tempHead = new ListNode(0);
        ListNode walker = tempHead;

        // reconstruct the list
        while(leftHead != null && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                walker.next = leftHead;
                leftHead = leftHead.next;
            } else {
                walker.next = rightHead;
                rightHead = rightHead.next;
            }
            walker = walker.next;
        }

        while (leftHead != null) {
            walker.next = leftHead;
            leftHead = leftHead.next;
            walker = walker.next;
        }

        while (rightHead != null) {
            walker.next = rightHead;
            rightHead = rightHead.next;
            walker = walker.next;
        }

        return tempHead.next;
    }
}
