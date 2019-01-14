package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

/**
 530. Minimum Absolute Difference in BST

 Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

 Example:

 Input:

   1
    \
     3
    /
   2

 Output:
 1

 Explanation:
 The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 */

@Amazon
@Google

@Easy
@Tree
public class MinimumAbsoluteDifferenceBST {

	/**
	 * BST + inorder traversal question with global value to help, use inorder to avoid Math.abs
	 */
	int min = Integer.MIN_VALUE;
	TreeNode prev = null;

	public int getMinimumDifference(TreeNode root) {
		if (root == null) {
			return min;
		}

		getMinimumDifference(root.left);

		if (prev != null) {
			min = Math.min(min, root.value - prev.value);
		}

		prev = root;

		getMinimumDifference(root.right);

		return min;
	}
}
