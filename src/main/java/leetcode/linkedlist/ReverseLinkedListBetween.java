package leetcode.linkedlist;

import baseObj.ListNode;

/**
 * Reverse a link list between begin and end exclusively
 * an example:
 * a linked list:
 * 0->1->2->3->4->5->6
 * |           |
 * begin       end
 * after call begin = reverse(begin, end)
 *
 * 0->3->2->1->4->5->6
 *          |  |
 *      begin end
 * @return the reversed list's 'begin' node, which is the precedence of node end
 *
 */

// Basic template
public class ReverseLinkedListBetween {
    public static ListNode reverse(ListNode begin, ListNode end) {
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

    public static void main(String[] args) {
        ListNode intersection = new ListNode(0);
        intersection.add(1).add(2).add(3).add(4).add(5).add(6);
    }
}
