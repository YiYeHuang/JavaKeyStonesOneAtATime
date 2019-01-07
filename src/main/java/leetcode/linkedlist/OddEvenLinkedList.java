package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Easy;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 328. Odd Even Linked List
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
 *
 * Example 1:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 1->3->5->2->4->NULL
 * Example 2:
 *
 * Input: 2->1->3->5->6->4->7->NULL
 * Output: 2->3->6->7->1->5->4->NULL
 * Note:
 *
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 *
 */
@Microsoft
@Google
@Bloomberg

@Medium
@LinkedListTag
public class OddEvenLinkedList {

    /**
     * Well, sooooooooooo, when a question is jump one at a time, consider faster/slower walker style
     *
     * odd.next = odd.next.next
     * even.next = even.next.next
     */
    public static ListNode oddEvenList(ListNode head) {
        if (null != head) {
            ListNode odd = head;
            ListNode even = head.next;
            ListNode cachedEvenHead = even;

            while (even != null && even.next != null) {
                odd.next = odd.next.next;
                even.next = odd.next.next;
                // move to next;
                odd = odd.next;
                even = even.next;
            }

            odd.next = cachedEvenHead;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.add(2).add(3).add(4).add(5).add(6);
        System.out.println(oddEvenList(head).toString());
    }

}
