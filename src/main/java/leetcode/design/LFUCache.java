package leetcode.design;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Hard;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * Design and implement a data structure for Least Frequently Used (LFUCache) cache.
 * It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new item.
 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), t
 * he least recently used key would be evicted.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
    * LFUCache cache = new LFUCache(  );
    *
    *cache.put(1,1);
    *cache.put(2,2);
    *cache.get(1);       // returns 1
    *cache.put(3,3);    // evicts key 2
    *cache.get(2);       // returns -1 (not found)
    *cache.get(3);       // returns 3.
    *cache.put(4,4);    // evicts key 1.
    *cache.get(1);       // returns -1 (not found)
    *cache.get(3);       // returns 3
    *cache.get(4);       // returns 4
 */
@Amazon
@Bloomberg
@Microsoft
@Hard
public class LFUCache {

    /**
     * Instead of key value is the node, now counter is the node
     */
    class DeNode {
        public int count = 0;

        // reverse thinking, for same counter, could have multiple keys.
        // when same key has the same count, respect
        public LinkedHashSet<Integer> keys = null;
        public DeNode prev = null;
        public DeNode next = null;

        public DeNode(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = next = null;
        }
    }

    private DeNode head;
    private int size;
    // A hash map that contains the key and the count, makes the value update time O(1)
    private HashMap<Integer, Integer> valueHash = null;
    // A hash map that mapping the key and the actual node, make the node search time O(1)
    private HashMap<Integer, DeNode> nodeHash = null;

    public LFUCache(int capacity) {
        this.size = capacity;
        this.valueHash = new HashMap<>();
        this.nodeHash = new HashMap<>();
    }

    public int get(int key) {
        if (valueHash.containsKey(key)) {
            increaseCount(key);
            return valueHash.get(key);
        }

        return -1;
    }

    public void put(int key, int value) {
        if (size == 0) {
            // full
            return;
        } else {

        }
    }

    /**
     * Private =========================================================================================================
     */

    private void increaseCount(int key) {
        DeNode node = nodeHash.get(key);
        node.keys.remove(key);

        // increase new count.
        if (node.next == null) {
            node.next = new DeNode(node.count + 1);
            node.next.prev = node;
            node.next.keys.add(key);
        } // insert to next node if the counter is adjacent
        else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } // insert the node in between
        else {
            // create new node
            DeNode tmp = new DeNode(node.count + 1);
            tmp.keys.add(key);

            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }
    }


}
