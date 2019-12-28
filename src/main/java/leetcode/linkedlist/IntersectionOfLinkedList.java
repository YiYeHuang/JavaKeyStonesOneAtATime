package leetcode.linkedlist;

import baseObj.ListNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.LinkedListTag;

/**
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 *
 *
 * For example, the following two linked lists:
 *
 * A:          a1 → a2
 *                    ↘
 *                      c1 → c2 → c3
 *                    ↗
 * B:     b1 → b2 → b3
 * begin to intersect at node c1.
 *
 *
 * Notes:
 *
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 * You may assume there are no cycles anywhere in the entire linked structure.
 * Your code should preferably run in O(n) time and use only O(1) memory.
 */

@Easy
@LinkedListTag
public class IntersectionOfLinkedList {

    /**
     * This is a changing type question of faster node and slow node, instead of node speed change, the list length change
     *
     * keep two walker scanning, if the length is same, easy to find intersection.
     * if length is not same, eventually one will catch up another.
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if( null==headA || null==headB )
            return null;

        ListNode walkerA = headA, walkerB = headB;
        while( walkerA!=walkerB){
            if (walkerA==null){
                walkerA = headB;
            } else {
                walkerA = walkerA.next;
            }
            if (walkerB==null){
                walkerB = headA;
            } else {
                walkerB = walkerB.next;
            }
        }
        return walkerA;
    }

    public static void main(String[] args) {
        ListNode intersection = new ListNode(99);
        intersection.add(100).add(101).add(102);

        ListNode head1 = new ListNode(1);
        head1.add(2).add(3).add(4).addNode(intersection);

        ListNode head2 = new ListNode(2);
        head2.add(5).add(7).addNode(intersection);


        getIntersectionNode(head1, head2);
    }
}
