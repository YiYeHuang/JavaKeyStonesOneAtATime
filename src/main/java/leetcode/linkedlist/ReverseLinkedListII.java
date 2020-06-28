package leetcode.linkedlist;

import leetcode.basicDto.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 92 Reverse a linked list from position m to n. Do it in one-pass.
 *
 * Note: 1 ≤ m ≤ n ≤ length of list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 */

@Medium
@LinkedListTag
public class ReverseLinkedListII {

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null ) return head;

        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;

        // find the head before reverse
        ListNode pre = dummy;
        int step = m;
        while (step > 1) {
            pre = pre.next;
            step--;
        }

        // start as the reverse head, will be tail
        ListNode tailTobe = pre.next;
        ListNode reverseNext = tailTobe.next;

        step = n - m;

        // move next
        // break previous connection

        while (step > 0) {
            // move to next item
            tailTobe.next = reverseNext.next;

            // point the current next to previous head
            reverseNext.next = pre.next;

            // move up pre
            pre.next = reverseNext;

            // update the next to move
            reverseNext = tailTobe.next;
            step--;
        }

        return dummy.next;
    }

    public static ListNode reverseBetweenTemplate(ListNode head, int m, int n) {
        if (head == null || head.next == null ) return head;

        ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
        dummy.next = head;

        // find the head before reverse
        ListNode begin = head;
        int step = m;
        while (step > 1) {
            begin = begin.next;
            step--;
        }

        // start as the reverse head, will be tail
        ListNode tailTobe = begin.next;
        ListNode reverseNext = tailTobe.next;

        step = n - m;

        // move next
        // break previous connection

        ListNode end = begin;

        while (step > 0) {
            end = end.next;
            step--;
        }

        ReverseLinkedListBetween.reverse(begin, end);

        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.add(1).add(2).add(3).add(4).add(5).add(6);
        System.out.println(reverseBetweenTemplate(head, 0, 5));
    }
}
