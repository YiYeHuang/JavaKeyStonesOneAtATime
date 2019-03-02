package leetcode.tree;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Tree;

/**
 255. Verify Preorder Sequence in Binary Search Tree

 Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

 You may assume each number in the sequence is unique.

 Consider the following binary search tree:

     5
    / \
   2   6
  / \
 1   3
 Example 1:

 Input: [5,2,6,1,3]
 Output: false
 Example 2:

 Input: [5,2,1,3,6]
 Output: true
 */

@Medium
@Tree
public class VerifyPreorderSequenceBST {
	/**
	 * reference on serialize and deserialize bst
	 */
	int start;
	public boolean verifyPreorder(int[] preorder) {
		helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
		return start == preorder.length;
	}
	private void helper(int[] preorder, int lower, int upper) {
		if (start == preorder.length) return;
		int value = preorder[start];
		if (value < lower || value > upper) return;
		start++;
		helper(preorder, lower, value);
		helper(preorder, value, upper);
	}
}
