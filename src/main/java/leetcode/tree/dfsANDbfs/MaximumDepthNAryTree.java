package leetcode.tree.dfsANDbfs;

import baseObj.Node;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 559. Maximum Depth of N-ary Tree

 Given a n-ary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 For example, given a 3-ary tree:
           1
       /   |   \
      3    2    4
     / |
    5   6
 We should return its max depth, which is 3


 Note:

 The depth of the tree is at most 1000.
 The total number of nodes is at most 5000.
 */

@Easy
@BFS
public class MaximumDepthNAryTree {

	public int maxDepth(Node root) {
		if (root == null ) return 0;

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		int hight = 0;

		while( !queue.isEmpty()) {
			int size = queue.size();
			while (size > 0) {
				Node node = queue.poll();
				size--;
				for (Node curr:node.children) {
					queue.offer(curr);
				}
			}
			hight++;
		}

		return hight;
	}
}
