package leetcode.bfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * 662. Maximum Width of Binary Tree

 Share
 Given a binary tree, write a function to get the maximum width of the given tree.
 The width of a tree is the maximum width among all levels.
 The binary tree has the same structure as a full binary tree, but some nodes are null.

 The width of one level is defined as the length between the end-nodes
 (the leftmost and right most non-null nodes in the level,
 where the null nodes between the end-nodes are also counted into the length calculation.

 Example 1:
 Input:

      1
    /   \
   3     2
  / \     \
 5   3     9

 Output: 4
 Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 Example 2:
 Input:

     1
    /
   3
  / \
 5   3

 Output: 2
 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 Example 3:
 Input:

     1
    / \
   3   2
 /
 5

 Output: 2
 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 Example 4:
 Input:

        1
       / \
      3   2
     /     \
    5       9
   /         \
 6           7
 Output: 8
 Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


 Note: Answer will in the range of 32-bit signed integer.
 */

@Medium
@BFS
@Tree
public class MaxWidth {

	/**
	 *
	 *
	 * example
	 *           1
	 *         /   \
	 *        3     2
	 *       / \     \
	 *      5   3     9
	 *  can be present as [1, 3, 2, 5, 3, null, 9]
	 *
	 *  the max width is the max index of end node - start node + 1 (since 0 base)
	 *
	 *  use BFS and a map to store the position
	 */
	public int widthOfBinaryTree(TreeNode root) {
		if(root == null) {
			return 0;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Map<TreeNode, Integer> map = new HashMap<TreeNode, Integer>();
		queue.offer(root);

		int currentWidth = 0;
		int maxWidth = 0;

		while(!queue.isEmpty()) {
			int size = queue.size();

			int start = 0;
			int end = 0;

			for (int i = 0; i < size; i++) {
				TreeNode node = queue.poll();
				if(i == 0) {
					start = map.get(node);
				}
				if (i == size - 1) {
					end = map.get(node);
				}

				// always building the next layer
				if(node.left != null){
					map.put(node.left, map.get(node) * 2);
					queue.offer(node.left);
				}
				if(node.right != null){
					map.put(node.right, map.get(node) * 2 + 1);
					queue.offer(node.right);
				}

			}

			currentWidth = end - start + 1;
			maxWidth = Math.max(currentWidth, maxWidth);
		}

		return maxWidth;
	}
}