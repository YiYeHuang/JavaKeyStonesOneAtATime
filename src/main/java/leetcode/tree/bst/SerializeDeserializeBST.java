package leetcode.tree.bst;

import leetcode.basicDto.TreeNode;
import java.util.Stack;
import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 449. Serialize and Deserialize BST

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your
 serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be
 serialized to a string and this string can be deserialized to the original tree structure.

 The encoded string should be as compact as possible.

 Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
@Medium
@StackTag
@Tree
public class SerializeDeserializeBST {

	private static final String SEP = ",";
	private static final String NULL = "null";

	/**
	 *      5
	 *   2     6
	 * 1  3      7
	 *
	 * serialize is easy, preorder, or inorder
	 *
	 * 5 2 1 3 6 7
	 *
	 * 1 2 3 5 6 7
	 *
	 */
	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		if (root == null) return NULL;
		//traverse it recursively if you want to, I am doing it iteratively here
		Stack<TreeNode> st = new Stack<>();
		st.push(root);
		while (!st.empty()) {
			root = st.pop();

			sb.append(root.val).append(SEP);

			if (root.right != null) st.push(root.right);
			if (root.left != null) st.push(root.left);
		}
		return sb.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.equals(NULL)) return null;

		String[] strs = data.split(SEP);
		Queue<Integer> q = new LinkedList<>();
		for (String e : strs) {
			q.offer(Integer.parseInt(e));
		}
		return buildTree(q);
	}


	//   5
	//  3 6
	// 2   7
	public TreeNode buildTree(Queue<Integer> q) {
		if (q.isEmpty()) return null;

		// from example 5 3 2 6 7
		TreeNode currentRoot = new TreeNode(q.poll());//root (5)
		Queue<Integer> samllerQueue = new LinkedList<>();

		while (!q.isEmpty() && q.peek() < currentRoot.val) {
			samllerQueue.offer(q.poll());
		}

		//smallerQueue : 3,2   storing elements smaller than 5 (root)
		currentRoot.left = buildTree(samllerQueue);
		//q: 6,7   storing elements bigger than 5 (root)
		currentRoot.right = buildTree(q);
		return currentRoot;
	}
}
