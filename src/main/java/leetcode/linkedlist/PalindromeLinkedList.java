package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedListTag;

/**
 Given a singly linked list, determine if it is a palindrome.

 Example 1:

 Input: 1->2
 Output: false
 Example 2:

 Input: 1->2->2->1
 Output: true
 Follow up:
 Could you do it in O(n) time and O(1) space?
 */

@LinkedListTag
@Easy
public class PalindromeLinkedList
{
    /**
     * First find the end of the list get size n
     * - if odd number, new head at n/2 + 2, reverse head until n/2
     * - if even number, new head at n/2 + 1, reverse head until n/2
     */
    public static boolean isPalindrome(ListNode head)
    {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // deal with odd number
        if (fast != null) {
            slow = slow.next;
        }

        ListNode newh1 = reverse(slow);
        ListNode newh2 = head;

        while (newh1 != null) {
            if (newh1.val != newh2.val) {
                return false;
            }
            newh1 = newh1.next;
            newh2 = newh2.next;
        }

        return true;
    }

    public static ListNode reverse(ListNode head) {
        ListNode pre = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void main(String[] args)
    {
        ListNode head = new ListNode(1);
        head.add(2).add(3).add(3).add(2).add(1);

        System.out.println(isPalindrome(head));
    }
}
