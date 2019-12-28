package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.Tree;

/**
 * 108. Convert Sorted Array to Binary Search Tree
 Easy
 814
 85


 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

 */

@Amazon

@Easy
@Tree
@BinarySearch
public class ConvertSortedArraytoBST {

	/**
	 * the trick is to create a complete binary tree, should insert everything from middle as possible
	 *
	 * so search the everything mid
	 */
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) return null;

		return build(nums, 0, nums.length - 1);
	}

	public TreeNode build(int[] nums, int low, int high) {
		if (low > high) return null;

		int mid = low + (high - low)/2;
		// mid is already consumed
		TreeNode root = new TreeNode(nums[mid]);
		root.left = build(nums, low, mid - 1);
		root.right = build(nums, mid + 1, high);

		return root;
	}

}
