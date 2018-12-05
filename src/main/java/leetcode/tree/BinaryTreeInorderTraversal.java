package leetcode.tree;


import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */

@Facebook
@Google
@Microsoft
@Amazon
@Bloomberg

@Medium
@Tree
@StackTag
@DFS
public class BinaryTreeInorderTraversal {

	public static List<Integer> inorderTraversalRec(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}

	private static void load(TreeNode node, List<Integer> result) {
		if (node == null) return;

		load(node.left, result);
		result.add(node.value);
		load(node.right, result);
	}

	public static List<Integer> inorderTraversalItr(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<TreeNode>();

		TreeNode walker = root;
		// dfs gets to the smallest, all the way left
		while (walker != null && !stack.isEmpty()) {
			while (walker != null) {
				stack.add(walker);
				walker = walker.left;
			}
			// pop the current min;
			walker = stack.pop();
			walker = walker.right;
		}
		return result;
	}


	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new  TreeNode(4);
		root.right = new  TreeNode(8);

		inorderTraversalRec(root);
	}
}
