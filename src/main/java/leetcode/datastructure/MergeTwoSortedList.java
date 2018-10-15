package leetcode.datastructure;

import baseObj.ListNode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list
 * should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedList
{
    /**
     * recursively merge.
     * switch l1.next and l2.next
     */
    public ListNode mergeTwolists(ListNode l1, ListNode l2)
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
}
