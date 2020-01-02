package leetcode.linkedlist;

import baseObj.ListNode;

import leetcode.tag.level.Hard;
import leetcode.tag.type.DivideConquer;
import leetcode.tag.type.LinkedListTag;

/**
 * 25. Reverse Nodes in k-Group

 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
 *
 * Example:
 *
 * Given this linked list: 1->2->3->4->5
 *
 * For k = 2, you should return: 2->1->4->3->5
 *
 * For k = 3, you should return: 3->2->1->4->5
 *
 * Note:
 *
 * Only constant extra memory is allowed.
 * You may not alter the values in the list's nodes, only nodes itself may be changed.
 */
@Hard
@LinkedListTag
@DivideConquer
public class ReverseNodesinKGroups {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode begin = dummyHead;

        int i = 0;

        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
         }

         return dummyHead.next;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
        //where first will be doomed "last"
        ListNode prev = begin;
        ListNode head = begin.next;
        ListNode walker = head.next;

        while(walker != end){
            // break the reverse target
            head.next = walker.next;
            // link the break target with the reverse list head
            ListNode currentHead = prev.next;
            walker.next = currentHead;
            // linked the fix begin with the break target
            prev.next = walker;
            // move walker to next
            walker = head.next;
        }

        // return the end of the reverse list
        return head;
    }
}
