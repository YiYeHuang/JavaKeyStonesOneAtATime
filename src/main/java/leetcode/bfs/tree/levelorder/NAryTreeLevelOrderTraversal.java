package leetcode.bfs.tree.levelorder;

import baseObj.Node;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 429. N-ary Tree Level Order Traversal

 Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

 For example, given a 3-ary tree:
        1
    /   |   \
    3    2    4
   / |
 5   6

 We should return its level order traversal:

 [
 [1],
 [3,2,4],
 [5,6]
 ]

 */

@Easy
@BFS
public class NAryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(Node root) {
		if (root == null ) return new ArrayList<>();

		Queue<Node> queue = new LinkedList<>();
		queue.offer(root);
		List<List<Integer>> result = new ArrayList<>();

		while( !queue.isEmpty()) {
			int size = queue.size();
			List<Integer> temp = new ArrayList<>();
			while (size > 0) {
				Node node = queue.poll();
				temp.add(node.val);
				size--;
				for (Node curr:node.children) {
					queue.offer(curr);
				}
			}

			result.add(temp);
		}

		return result;
	}
}
