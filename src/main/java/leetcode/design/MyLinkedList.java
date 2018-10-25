package leetcode.design;

import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedList;

import javax.annotation.processing.SupportedSourceVersion;

/**
 * Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.
 *
 * Implement these functions in your linked list class:
 *
 * get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
 * addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
 * addAtTail(val) : Append a node of value val to the last element of the linked list.
 * addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
 * deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
 * Example:
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
 * linkedList.get(1);            // returns 2
 * linkedList.deleteAtIndex(1);  // now the linked list is 1->3
 * linkedList.get(1);            // returns 3
 * Note:
 *
 * All values will be in the range of [1, 1000].
 * The number of operations will be in the range of [1, 1000].
 * Please do not use the built-in LinkedList library.
 */

@LinkedList
@Easy
public class MyLinkedList {

    private class Node
    {
        public int val;
        public Node next;
        public Node(int x) { val = x; }
    }


    private Node head;
    private int size;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        this.size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (head == null) {
            return -1;
        } else {

            if (index > size - 1) {
                return -1;
            }

            int idx = 0;
            Node cur = head;

            while (idx < index) {
                cur = cur.next;
                idx++;
            }

            return cur.val;
        }
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node newNode = new Node(val);

        newNode.next = head;
        head = newNode;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node cur = head;
        while (null != cur.next) {
            cur = cur.next;
        }
        cur.next = new Node(val);
        size++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        int idx = 0;
        Node cur = head;

        while (idx < index - 1) {
            cur = cur.next;
            idx++;
        }

        Node newNode = new Node(val);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index > size) {
            return;
        }
        int idx = 0;
        Node cur = head;

        while (idx < index - 1) {
            cur = cur.next;
        }

        cur.next = cur.next.next;
        size--;
    }

    public void print() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();

        list.addAtHead(1);
        list.addAtTail(3);
        list.print();
        list.addAtIndex(1, 2);
        list.print();
        list.deleteAtIndex(1);
        list.print();
        System.out.println(list.get(1));
        System.out.println(list.get(2));
    }
}
