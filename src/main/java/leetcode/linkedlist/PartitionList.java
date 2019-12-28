package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * Example:
 *
 * Input: head = 1->4->3->2->5->2, x = 3
 * Output: 1->2->2->4->3->5
 */

@Medium
@LinkedListTag
public class PartitionList {
    public static ListNode partition(ListNode head, int x) {
        ListNode dummySmaller = new ListNode(0);
        ListNode dummyRest = new ListNode(0);
        ListNode resultHead = dummySmaller;
        ListNode restHead = dummyRest;


        while (head != null) {
            if (head.val < x) {
                dummySmaller.next = head;
                dummySmaller = dummySmaller.next;
            } else {
                dummyRest.next = head;
                dummyRest = dummyRest.next;
            }
            head = head.next;
        }

        // this is important, avoid cycle
        dummyRest.next = null;
        dummySmaller.next = restHead.next;
        return resultHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.add(2).add(4).add(7).add(5).add(9).add(3).add(8);
        System.out.println(partition(head, 5));
    }
}
