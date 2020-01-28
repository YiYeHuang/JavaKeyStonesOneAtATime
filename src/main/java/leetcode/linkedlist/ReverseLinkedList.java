package leetcode.linkedlist;

import baseObj.ListNode;

import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedListTag;

/**
206 Reverse a singly linked list.

 Example:

 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL
 Follow up:

 A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

@Easy
@LinkedListTag
public class ReverseLinkedList
{
    /**
     * - Go all the way to the end
     * - reconnect the
     */
    public static ListNode reverseListRecr(ListNode head)
    {
        if(head==null || head.next==null)
            return head;
        ListNode next = head.next;

        // go all the way to the end
        ListNode newHead = reverseListRecr(next);

        // reconnect the next
        next.next=head;

        // break the old
        head.next=null;

        return newHead;
    }

    /**
     * pre define a previous node
     *
     * 1. Temp walker 'next' jump to next node, to store the next node while break the current connection
     * 2. point current head to previous node (when doing first step, point to null)
     * 3. point previous node to current  to iterate to next
     * 4. move current node to next node.
     */

    //
    // 过往初始一场空
    // 新人从头向前冲
    // 起首踏步回头看
    // 过往起首连向前
    //
    // re 过往

    public static ListNode reverseListInplace(ListNode head)
    {
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.add(2).add(3).add(4).add(5);
        System.out.println(head.toString());

        System.out.println(reverseListRecr(head).toString());

    }
}
