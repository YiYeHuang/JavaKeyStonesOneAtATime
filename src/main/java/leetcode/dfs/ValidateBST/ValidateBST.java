package leetcode.dfs.ValidateBST;

import baseObj.TreeNode;
import leetcode.tag.type.StackTag;
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
     / \
    3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.
 */

@Tree
@StackTag
public class ValidateBST {

	/**
	 * basic DFS stack template.  Keep the previous node cache
	 */
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

			if (prevousCache != null && prevousCache.val >= root.val) {
				return false;
			}

			prevousCache = root;
			root = root.right;
		}
		return true;
	}



	// recursively iterating over the tree while defining interval <minVal, maxVal> for each node which value must fit in.
	public boolean isValidBST_Rec(TreeNode root) {
		return helper(root, null, null);
	}

	boolean helper(TreeNode root, Integer min, Integer max) {
		if (root == null)
			return true;

		if ((min != null && root.val <= min) || (max != null && root.val >= max))
			return false;

		return helper(root.left, min, root.val) && helper(root.right, root.val, max);
	}



	private TreeNode prev = null;

	public boolean isValidBST_Rec2(TreeNode root) {
		if(root == null){
			return true;
		}
		// keep goes on left
		if(!isValidBST(root.left)) {
			return false;
		}

		if(prev != null && root.val <= prev.val) {
			return false;
		}
		prev = root;
		// switch to right
		return isValidBST(root.right);
	}
}
