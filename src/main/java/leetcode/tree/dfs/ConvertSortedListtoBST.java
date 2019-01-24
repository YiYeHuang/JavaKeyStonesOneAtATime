package leetcode.tree.dfs;

import baseObj.ListNode;
import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

/**
 109. Convert Sorted List to Binary Search Tree
 Medium
 717
 54


 Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted linked list: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

       0
      / \
    -3   9
    /   /
 -10  5

 */

@Google
@Microsoft
@Amazon

@Easy
@Tree
@DFS
@BinarySearch
public class ConvertSortedListtoBST {

	/**
	 * Fast + SLow Linked List split transform
	 *
	 * Similar to Convert sorted array to BST, to keep balanced,
	 * keep finding the mid to added to the tree
	 */
	public TreeNode sortedListToBST(ListNode head) {
		if (null == head || null == head.next) return null;

		return build(head);
	}

	public TreeNode build(ListNode head) {
		if(head==null)
			return null;
		ListNode slow = head;
		ListNode fast = head;
		ListNode leftTail=null;

		//find the mid node
		while(fast.next!=null && fast.next.next!=null){
			fast = fast.next.next;
			leftTail = slow;
			slow = slow.next;
		}

		if(leftTail!=null)
			leftTail.next = null; //break the link
		else
			head = null;

		TreeNode root = new TreeNode(slow.val);

		root.left = build(head);
		root.right = build(slow.next);
		return root;
	}
}
