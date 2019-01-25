package leetcode.design_systemdesign;

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

/**
 * LFU potential problem with current implementation, as new node always insert at front, the front is the hot zone.
 * old-now-cold-af-node-live-at-back may never get delete. use wisely
 *
 * Steps:
 * update in hash structure --> reorder the main node structure
 */
public class LFUCache {

    /**
     * Instead of key value is the node, now counter is the node
     */
    class Node {
        public int count = 0;

        // reverse thinking, for same counter, could have multiple keys.
        // when same key has the same count, respect
        public LinkedHashSet<Integer> keys = null;
        public Node prev = null;
        public Node next = null;

        public Node(int count) {
            this.count = count;
            keys = new LinkedHashSet<Integer>();
            prev = null;
            next = null;
        }
    }

    private Node head;
    private int size;
    // A hash map that contains the key and the count, makes the value update time O(1)
    private HashMap<Integer, Integer> valueHash;
    // A hash map that mapping the key and the actual node, make the node search time O(1)
    private HashMap<Integer, Node> nodeHash;

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
            // invalid
            return;
        }

        if (valueHash.containsKey(key)){
            // update key value in hash map
            valueHash.put(key, value);
        } else {
            // input the node to the hashmap structure
            if (valueHash.size() < size) {
                valueHash.put(key, value);
            } else {

                // check capacity and remove if necessary
                removeIfFull();
                valueHash.put(key, value);
            }

            // Insert the new lowest count node the to head
            addToHead(key);
        }

        // increase the count after a successful input.
        increaseCount(key);
    }

    /**
     * Private =========================================================================================================
     */

    // Insert the new lowest count node the to head
    private void addToHead(int key) {
        if (head == null) {
            head = new Node(0);
            head.keys.add(key);
        } else if (head.count > 0) {
            Node node = new Node(0);
            node.keys.add(key);

            // insert, and be the new head with the lowest count
            node.next = head;
            head.prev = node;
            head = node;
        } else {
            head.keys.add(key);
        }

        // register the new node
        nodeHash.put(key, head);
    }

    // reorder when key get hit
    private void increaseCount(int key) {
        // remove the current key and re-put in the next node
        Node node = nodeHash.get(key);
        node.keys.remove(key);

        // increase new count.
        if (node.next == null) {
            node.next = new Node(node.count + 1);
            node.next.prev = node;
            node.next.keys.add(key);
        } // insert to next node if the counter is adjacent
        else if (node.next.count == node.count + 1) {
            node.next.keys.add(key);
        } // insert the node in between
        else {
            // create new node
            Node tmp = new Node(node.count + 1);
            tmp.keys.add(key);

            tmp.prev = node;
            tmp.next = node.next;
            node.next.prev = tmp;
            node.next = tmp;
        }

        // Since when increment, the new key is guarantee to put to the next node, update the node in the hashmap
        nodeHash.put(key, node.next);

        // remove empty node
        if (node.keys.size() == 0) {
            removeNode(node);
        }
    }


    // Since Head is guarantee to be the lowest count，always remove the first key in the head
    // this could cause the jump of the counter
    private void removeIfFull() {
        if (head == null) {
            return;
        }

        int old = 0;
        for (int n : head.keys) {
            old = n;
            break;
        }

        head.keys.remove(old);
        if (head.keys.size() == 0) {
            removeNode(head);
        }

        nodeHash.remove(old);
        valueHash.remove(old);
    }

    private void removeNode(Node node) {
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next != null) {
            node.next.prev = node.prev;
        }
    }

}
