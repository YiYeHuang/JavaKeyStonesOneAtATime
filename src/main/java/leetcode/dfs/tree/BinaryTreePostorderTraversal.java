package leetcode.dfs.tree;


import baseObj.TreeNode;
import leetcode.tag.level.Hard;
import leetcode.tag.type.DFS;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 Given a binary tree, return the postorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [3,2,1]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */

@Hard
@Tree
@StackTag
@DFS
public class BinaryTreePostorderTraversal {

	public static List<Integer> PostorderTraversalRec(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}

	private static void load(TreeNode node, List<Integer> result) {
		if (node == null) return;

		load(node.left, result);
		load(node.right, result);
		result.add(node.val);
	}

	public static List<Integer> PostorderTraversalPost(TreeNode root) {
		return null;
	}


	public static void main(String[] args) {
	}
}
