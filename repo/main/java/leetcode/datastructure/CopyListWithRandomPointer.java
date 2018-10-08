package leetcode.datastructure;

import java.util.HashMap;

/**
 * A linked list is given such that each node contains an additional random
 * pointer which could point to any node in the list or null.
 * 
 * Return a deep copy of the list.
 *
 */
public class CopyListWithRandomPointer
{
    public static RandomListNode copyRandomList(RandomListNode head)
    {
        if (head == null)
        {
            return head;
        }
        RandomListNode result = new RandomListNode(head.label);
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        map.put(head, result);

        RandomListNode ptr = head.next;
        while (null != ptr)
        {
            map.put(ptr, new RandomListNode(ptr.label));
            ptr = ptr.next;
        }

        RandomListNode ptr2 = head;
        
        while(null != ptr2)
        {
            RandomListNode current = map.get(ptr2);
            current.next = map.get(ptr2.next);
            current.random = map.get(ptr2.random);
        }

        return result;
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
