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
 Given a binary tree, return the preorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,2,3]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */

@Facebook

@Medium
@Tree
@StackTag
@DFS
public class BinaryTreePreorderTraversal {

	public static List<Integer> preorderTraversalRec(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}

	private static void load(TreeNode node, List<Integer> result) {
		if (node == null) return;

		result.add(node.value);
		load(node.left, result);
		load(node.right, result);
	}

	public static List<Integer> preorderTraversalItr(TreeNode root) {

		return null;
	}


	public static void main(String[] args) {
	}
}
