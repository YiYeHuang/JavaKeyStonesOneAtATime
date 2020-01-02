package leetcode.design_systemdesign;


import leetcode.tag.level.Hard;
import leetcode.tag.type.Design;
import leetcode.tag.type.LinkedListTag;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * 
 * get(key) - 
 * Get the value (will always be positive) of the key if the key exists in the cache, 
 * otherwise return -1. 
 * 
 * put(key, value) - 
 * Set or insert the value if the key is not already present. 
 * When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 * 
 * Follow up: Could you do both operations in O(1) time complexity?
 *
 *
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */

@Design
@LinkedListTag
@Hard
/**
 * A linkedlist is already enough for lru cache, even though the idea of lru cache requires bound data structure
 * however the search time for linkedlist is O(logN) so a hashMap is used here, for quick access.
 * Double LinkedList is also used for easier insert and delete operations.
 */
public class LRUCache
{
    
    class DLinkedNode
    {
        int key;
        int value;
        DLinkedNode pre;
        DLinkedNode next;

        public DLinkedNode(int key, int value)
        {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString()
        {
            return key + "|" + value;
        }
    }

    /**
     * Set the current node pre and next, and skip itself. 
     */
    private void deleteNode(DLinkedNode node)
    {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * for updating the new hit to the top of the list
     */
    private void addToHead(DLinkedNode new_node)
    {
        // link to previous head
        new_node.next = m_head.next;
        // previous head link to current head
        new_node.next.pre = new_node;
        // current point to head
        new_node.pre = m_head;
        // head point to current
        m_head.next = new_node;
    }

    /**
     * Fill in the cache if there are rooms, evict the least recent used cache if no room
     * -> FIFO
     * 
     * Update recent order if the cache is re-hit
     */

    private int m_capacity;
    private HashMap<Integer, DLinkedNode> m_cache;
    private DLinkedNode m_head;
    private DLinkedNode m_tail;
    private int m_currentCount;

    public LRUCache(int capacity)
    {
        this.m_capacity = capacity;
        this.m_cache = new HashMap<Integer, DLinkedNode>();
        this.m_head = new DLinkedNode(0, 0);
        this.m_tail = new DLinkedNode(0, 0);
        m_head.next = m_tail;
        m_tail.pre = m_head;
        m_head.pre = null;
        m_tail.next = null;
        m_currentCount = 0;
    }

    public int get(int key)
    {
        // Hit
        if (m_cache.get(key) != null)
        {
            // Cache the reference
            DLinkedNode node = m_cache.get(key);
            deleteNode(node);
            addToHead(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value)
    {
        // update the value of the key.
        if (m_cache.get(key) != null)
        {
            DLinkedNode node = m_cache.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else
        {
            //Create the node first;
            DLinkedNode node = new DLinkedNode(key, value);
            m_cache.put(key, node);
            // didn't hit capa, just add to the head
            if (m_currentCount < m_capacity)
            {
                m_currentCount++;
                addToHead(node);
            }
            else
            {
                // remove the least recent used cache
                m_cache.remove(m_tail.pre.key);
                deleteNode(m_tail.pre);
                addToHead(node);
            }
        }
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        DLinkedNode itr = m_head;
        while(itr.next != null)
        {
            sb.append(itr.toString()).append("-");
            itr = itr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        LRUCache test = new LRUCache(3);
        test.put(1, 1);
        test.put(2, 2);
        test.put(3, 3);
        test.get(2);
        
        test.put(4, 4);
        test.put(5, 5);
        test.put(6, 6);
        test.get(1);
        test.get(6);
    }
}
