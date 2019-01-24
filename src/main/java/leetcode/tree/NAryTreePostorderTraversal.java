package leetcode.tree;

import baseObj.Node;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 590. N-ary Tree Postorder Traversal

 Given an n-ary tree, return the postorder traversal of its nodes' values.

 For example, given a 3-ary tree:
        1
    /   |   \
   3    2    4
  / |
 5   6




 Return its postorder traversal as: [5,6,3,2,4,1].

 Note:

 Recursive solution is trivial, could you do it iteratively?

 */

@Easy
@Tree
public class NAryTreePostorderTraversal {

	public List<Integer> postorder(Node root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}

	private static void load(Node node, List<Integer> result) {
		if (node == null) return;

		for(Node cur:node.children) {
			load(cur, result);
		}
		result.add(node.val);
	}
}
