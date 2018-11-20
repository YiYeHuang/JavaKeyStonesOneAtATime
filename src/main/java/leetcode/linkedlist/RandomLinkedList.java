package leetcode.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null. Return a deep copy
 * of the list.
 */
public class RandomLinkedList
{
    class RandomListNode
    {
        int label;
        RandomListNode next, random;

        RandomListNode(int x)
        {
            this.label = x;
        }
    };

    public RandomListNode copyRandomList(RandomListNode head)
    {
        if (head == null)
        {
            return null;
        }

        // map the input and the new node
        RandomListNode ptr = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        while (ptr != null)
        {
            map.put(ptr, new RandomListNode(ptr.label));
            ptr = ptr.next;
        }
        // assign next and random
        ptr = head;
        while (ptr != null)
        {
          map.get(ptr).next = map.get(ptr.next);
          map.get(ptr).random = map.get(ptr.random);
          ptr = ptr.next;
        }
 
        return map.get(head);
    }
}
