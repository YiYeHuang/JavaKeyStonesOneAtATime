package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

/**


 111. Minimum Depth of Binary Tree


 Favorite

 Share
 Given a binary tree, find its minimum depth.

 The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

 3
 / \
 9  20
 /  \
 15   7
 return its minimum depth = 2.
 */

@Easy
@Tree
@DFS
public class MinDepth {

	/**
	 * dfs
	 *
	 * - when right and left not null, find min
	 * - when left or right null, find max, then try find min
	 */
	public int minDepth(TreeNode root) {
		if (null == root)
		{
			return 0;
		}
		else
		{
			if (root.left != null && root.right != null)
				return Math.min(minDepth(root.left), minDepth(root.right))+1;
			else
				return Math.max(minDepth(root.left), minDepth(root.right))+1;
		}
	}
}
