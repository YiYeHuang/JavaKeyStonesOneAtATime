package leetcode.bfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.LinkedList;
import java.util.Queue;

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
      /    \
     9     20
   /  \
 15   7
 return its minimum depth = 2.
 */

@Easy
@Tree
@BFS
@DFS
public class MinDepth {

	/**
	 * dfs
	 *
	 * - when right and left not null, find min
	 * - when left or right null, find max, then try find min
	 */
	public int minDepthDFS(TreeNode root) {
		if (null == root)
		{
			return 0;
		}
		else
		{
			if (root.left != null && root.right != null)
				return Math.min(minDepthDFS(root.left), minDepthDFS(root.right))+1;
			else
				return Math.max(minDepthDFS(root.left), minDepthDFS(root.right))+1;
		}
	}

	/**
	 * level order. return depth when hit the first node with both lef and right empty
	 */
	public int minDepthBFS(TreeNode root) {
		if(root == null) return 0;
		int depth = 1;
		Queue<TreeNode> qu = new LinkedList<>();
		qu.offer(root);
		while(!qu.isEmpty())
		{
			int size = qu.size();
			while(size-- > 0)
			{
				TreeNode temp = qu.poll();
				if(temp.left == null && temp.right == null)
					return depth;
				if(temp.left != null) qu.offer(temp.left);
				if(temp.right != null) qu.offer(temp.right);
			}
			depth++;
		}
		return depth;
	}
}
