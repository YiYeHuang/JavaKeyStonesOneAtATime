package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.type.Tree;

import java.util.Stack;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
   2
  / \
 1   3
 Output: true
 Example 2:

    5
   / \
  1   4
 /     \
 3      6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 */
public class ValidateBST {
	public boolean isValidBST(TreeNode root) {

		if (root == null) return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode prevousCache = null;

		while(root != null || !stack.isEmpty()) {
			while(root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();

			if (prevousCache != null && prevousCache.value >= root.value ) return false;

			prevousCache = root;
			root = root.right;
		}
		return true;
	}
}
