package leetcode.tree.nAry;

import leetcode.basicDto.Node;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 589. N-ary Tree Preorder Traversal

 Given an n-ary tree, return the preorder traversal of its nodes' values.

 For example, given a 3-ary tree:
       1
   /   |   \
  3    2    4
 / |
 5   6

 Return its preorder traversal as: [1,3,5,6,2,4].

 Note:

 Recursive solution is trivial, could you do it iteratively?


 */

@Easy
@Tree
public class NAryTreePreorderTraversal {

	public List<Integer> preorder(Node root) {
		List<Integer> result = new ArrayList<>();
		load(root, result);
		return result;
	}

	private static void load(Node node, List<Integer> result) {
		if (node == null) return;

		result.add(node.val);
		if (node.children.size() == 0) return;

		for(Node cur:node.children) {
			load(cur, result);
		}
	}

	public List<Integer> preorderItr(Node root) {
		List<Integer> list = new ArrayList<>();
		if (root == null) return list;

		Stack<Node> stack = new Stack<>();
		stack.add(root);

		while (!stack.empty()) {
			root = stack.pop();
			list.add(root.val);
			for (int i = root.children.size() - 1; i >= 0; i--)
				stack.add(root.children.get(i));
		}

		return list;
	}
}
