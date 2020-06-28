package leetcode.linkedlist;

import leetcode.basicDto.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 369. Plus One Linked List
 *
 * Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
 *
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 *
 * The digits are stored such that the most significant digit is at the head of the list.
 *
 * Example :
 *
 * Input: [1,2,3]
 * Output: [1,2,4]
 */

@Medium
@LinkedListTag
public class PlusOneLinkedList {

    /**
     * Key: only 9 will cause overflow, value is only 1
     *
     * if last digit is less than 9, no worry
     * if last digit is 9 and, previous is not, no worry
     *
     * only 99999 will cause issue
     *
     * point to the last non 9 digit to ZeroFied the rest,
     * dummy head will take care of the carryover
     */
    public static ListNode plusOne(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode lastNotNine = dummy;
        ListNode walker = dummy;

        while (walker.next != null) {
            walker = walker.next;
            if (walker.val != 9) {
                lastNotNine = walker;
            }
        }

        if (walker.val != 9) {
            walker.val++;
        } else {
            lastNotNine.val++;
            lastNotNine = lastNotNine.next;
            while (lastNotNine != null) {
                lastNotNine.val = 0;
                lastNotNine = lastNotNine.next;
            }
        }

        if (dummy.val == 0) {
            return dummy.next;
        }

        return dummy;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        head.add(9).add(9).add(9);
        System.out.println(plusOne(head));
    }
}
