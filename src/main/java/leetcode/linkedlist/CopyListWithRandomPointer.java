package leetcode.linkedlist;

import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;

import java.util.HashMap;
import java.util.Map;

/*
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 *
 */

@Medium
@LinkedListTag
public class CopyListWithRandomPointer {
    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;

        //  original        copy
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        // loop 1. copy all the nodes
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        // loop 2. assign next and random pointers
        RandomListNode orginal = head;
        while (orginal != null) {
            map.get(orginal).next = map.get(orginal.next);
            map.get(orginal).random = map.get(orginal.random);
            orginal = orginal.next;
        }

        return map.get(head);
    }
}

class RandomListNode
{
    int label;
    RandomListNode next, random;

    RandomListNode(int x)
    {
        this.label = x;
    }
};
