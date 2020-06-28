package leetcode.tree;

import algorithm.sorting.BSTUtil;
import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

/**
 * Invert a binary tree.

 Example:

 Input:

      4
    /   \
   2     7
  / \   / \
 1   3 6   9
 Output:

       4
     /   \
    7     2
   / \   / \
  9   6 3   1
 */

@Easy
@Tree
public class InventBT {

	/**
	 * Basic tree traversal question, beware of swap
	 */
	public static TreeNode invertTree(TreeNode root) {

		invert(root);
		return root;
	}

	public static void invert(TreeNode root) {

		if (root == null) return;

		TreeNode temp = root.right;
		root.right = root.left;
		root.left = temp;

		invert(root.left);
		invert(root.right);
	}

	public static void main(String[] args) {
		int[] input = {0,6,2,8,0,4,7,9,-1,-1,2,6};
		TreeNode root = BSTUtil.toPositiveBST(input);
		invertTree(root);
	}
}
