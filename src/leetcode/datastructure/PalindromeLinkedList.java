package leetcode.datastructure;

import basic.ListNode;

/**
 * Given a singly linked list, determine if it is a palindrome.
 * 
 * Follow up: Could you do it in O(n) time and O(1) space?
 *
 */
public class PalindromeLinkedList
{
    /**
     * First find the end of the list get size n
     * - if odd number, new head at n/2 + 2, reverse head until n/2
     * - if even number, new head at n/2 + 1, reverse head until n/2
     */
    public static boolean isPalindrome(ListNode head)
    {
        if (null== head) return false;
        if (null == head.next) return true;

        int size = 0;
        ListNode ptr = head;
        while (null != ptr)
        {
            ptr = ptr.next;
            size++;
        }

        int newHead1Position = 0;
        int newHead2Position = 0;
        if (size%2 == 0)
        {
            newHead1Position = size/2 + 1;
            newHead2Position = size/2;
        }
        else
        {
            newHead1Position = size/2 + 2;
            newHead2Position = size/2;
        }

        int newHeadCount = 1;
        int reverseCount = 0;
        ListNode currentNode = head;
        ListNode reverseNewHead = null;
        ListNode nextNode = null;

        ListNode newHead = head;
        while (newHeadCount < newHead1Position)
        {
            newHead = newHead.next;
            newHeadCount++;
        }
        
        
        while (reverseCount < newHead2Position)
        {
            nextNode = currentNode.next;
            currentNode.next = reverseNewHead;
            reverseNewHead = currentNode;
            currentNode = nextNode;
            reverseCount++;
        }

        while (null != reverseNewHead)
        {
            if (reverseNewHead.val != newHead.val)
            {
                return false;
            }
            reverseNewHead = reverseNewHead.next;
            newHead = newHead.next;
        }

        return true;
    }

    public static void main(String[] args)
    {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);

        System.out.print(isPalindrome(node));
    }
}
