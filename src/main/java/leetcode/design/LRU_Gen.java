package leetcode.design;

import java.util.HashMap;

public class LRU_Gen<K, V>
{

    class DLinkedNode<T>
    {
        K key;
        V value;
        DLinkedNode<T> pre;
        DLinkedNode<T> next;

        public DLinkedNode(K key, V value)
        {
            this.key = key;
            this.value = (V) value;
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
    private void deleteNode(DLinkedNode<V> node)
    {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * for updating the new hit to the top of the list
     */
    private void addToHead(DLinkedNode<V> new_node)
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
    private HashMap<K, DLinkedNode<V>> m_cache;
    private DLinkedNode<V> m_head;
    private DLinkedNode<V> m_tail;
    private int m_currentCount;

    public LRU_Gen(int capacity)
    {
        this.m_capacity = capacity;
        this.m_cache = new HashMap<K, DLinkedNode<V>>();
        this.m_head = new DLinkedNode<V>(null, null);
        this.m_tail = new DLinkedNode<V>(null, null);
        m_head.next = m_tail;
        m_tail.pre = m_head;
        m_head.pre = null;
        m_tail.next = null;
        m_currentCount = 0;
    }

    public V get(int key)
    {
        // Hit
        if (m_cache.get(key) != null)
        {
            // Cache the reference
            DLinkedNode<V> node = m_cache.get(key);
            deleteNode(node);
            addToHead(node);
            return (V) node.value;
        }
        return null;
    }

    public void put(K key, V value)
    {
        // update the value of the key.
        if (m_cache.get(key) != null)
        {
            DLinkedNode<V> node = m_cache.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else
        {
            //Create the node first;
            DLinkedNode<V> node = new DLinkedNode<V>(key, value);
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

        DLinkedNode<V> itr = m_head;
        while(itr.next != null)
        {
            sb.append(itr.toString()).append("-");
            itr = itr.next;
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        LRU_Gen<Integer, String> test = new LRU_Gen<Integer, String>(4);

        test.put(1, "1");
        test.put(2, "2");
        test.put(3, "3");
        test.get(2);
        System.out.println(test);
        
        test.put(4, "4");
        test.put(5, "5");
        test.put(6, "6");
        test.get(1);
        test.get(6);
        System.out.println(test);
    }
}
