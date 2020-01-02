package leetcode.tree;

import baseObj.TreeNode;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

/**
 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

 You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

 Example 1:

 Input:
 Tree 1                     Tree 2
    1                         2
   / \                       / \
  3   2                     1   3
 /                           \   \
 5                             4   7
 Output:
 Merged tree:
     3
    / \
   4   5
  / \   \
 5   4   7


 Note: The merging process must start from the root nodes of both trees.
 */

@Easy
@Tree
public class MergeBT {

	/**
	 * use BST version of dummy head
	 */
	public TreeNode mergeTreesDEEPCOPY(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) return null;

		int val = (t1 == null ? 0 : t1.val) + (t2 == null ? 0 : t2.val);
		TreeNode newNode = new TreeNode(val);

		newNode.left = mergeTreesDEEPCOPY(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
		newNode.right = mergeTreesDEEPCOPY(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

		return newNode;
	}

	/**
	 * Official answer
	 *
	 * The key is, if left or right is null, we actually dont need to care about the rest of the branch,
	 * return the non-null branch as the new subtree
	 * else, take take care of the value merge
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if (t1 == null)
			return t2;
		if (t2 == null)
			return t1;
		t1.val += t2.val;
		t1.left = mergeTrees(t1.left, t2.left);
		t1.right = mergeTrees(t1.right, t2.right);
		return t1;
	}
}
