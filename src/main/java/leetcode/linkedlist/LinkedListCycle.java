package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedListTag;

/**
 * 141 LinkedList cycle
 *
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up: Can you solve it without using extra space?
 * 
 */

@Easy
@LinkedListTag
public class LinkedListCycle
{
    /**
     * have a two steps runner and a one step normal visitor. If cycle exist,
     * they will meet eventually
     */
    public boolean hasCycle(ListNode head)
    {
        if (null == head || null == head.next)
        {
            return false;
        }

        ListNode walker = head;
        ListNode runner = head;

        // check runner always, cuz runner is always run faster than walker
        while (runner.next != null && runner.next.next != null)
        {
            walker = walker.next;
            runner = runner.next.next;
            if (walker == runner)
                return true;
        }
        return false;
    }


}
