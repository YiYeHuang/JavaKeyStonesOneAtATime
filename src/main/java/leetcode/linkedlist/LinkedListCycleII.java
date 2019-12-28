package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

/**
 * 42 LinkedList cycleII: return the entry point
 *
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 *
 * Note: Do not modify the linked list.
 *
 * Follow up:
 * Can you solve it without using extra space?
 */

@Medium
@LinkedListTag
public class LinkedListCycleII {

    /**
     * If there is a cycle, return the entry location of the cycle
     *
     * 2.1)
     * L1 is defined as the distance between the head point and entry point
     *
     * 2.2)
     * L2 is defined as the distance between the entry point and the
     * meeting point
     *
     * 2.3) C is defined as the length of the cycle
     *
     * 2.4) n is defined as the travel times of the fast pointer around the
     * cycle When the first encounter of the slow pointer and the fast pointer
     *
     * According to the definition of L1, L2 and C, we can obtain:
     *
     * the total distance of the slow pointer traveled when encounter is
     * L1 + L2
     *
     * the total distance of the fast pointer traveled when encounter is
     * L1 + L2 + n * C
     *
     * Because the total distance the fast pointer traveled is twice as the slow
     * pointer, Thus:
     *
     * slow walker traveled 2  * (L1+L2) = L1 + L2 + n * C
     * =>
     * L1 + L2 = n * C
     * =>
     * L1 = (n - 1) C + (C - L2)
     *
     * count out C, L1 = C - L2
     *
     * So, when the slow pointer and the fast pointer encounter in the cycle,
     * we can define a pointer "entry" that point to the head,
     * this "entry" pointer moves one step each time so as the slow pointer.
     * When this "entry" pointer and the slow pointer both point to the same location,
     * this location is the node where the cycle begins.
     */

    public ListNode detectCycle(ListNode head)
    {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null)
        {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
            {
                while (head != slow)
                {
                    head = head.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null; // there has no cycle
    }
}
