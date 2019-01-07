package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.company.*;
import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedListTag;

/**
 21 Merge Two Sorted List

 Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
 nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4
 */

@Amazon
@Microsoft
@Facebook
@Bloomberg
@Apple

@Easy
@LinkedListTag
public class MergeTwoSortedList
{
    /**
     * recursively merge.
     * switch l1.next and l2.next
     */
    public ListNode mergeTwolistsRecur(ListNode l1, ListNode l2)
    {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val)
        {
            l1.next = mergeTwolists(l1.next, l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwolists(l1, l2.next);
            return l2;
        }
    }

    public static ListNode mergeTwolists(ListNode l1, ListNode l2)
    {
        ListNode dummyHead = new ListNode(0);
        ListNode walker = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                walker.next = l1;
                l1 = l1.next;
            } else {
                walker.next = l2;
                l2 = l2.next;
            }
            walker = walker.next;
        }

        while (l1 != null) {
            walker.next = l1;
            l1 = l1.next;
            walker = walker.next;
        }
        while (l2 != null) {
            walker.next = l2;
            l2 = l2.next;
            walker = walker.next;
        }

        return dummyHead.next;
    }
}
