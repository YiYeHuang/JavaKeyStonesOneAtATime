package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.Tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 449. Serialize and Deserialize BST

 Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

 Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized to a string and this string can be deserialized to the original tree structure.

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
		serialize(root, sb);
		return sb.toString();
	}

	/**
	 * preorder is the best way to serialize
	 */
	public void serialize(TreeNode root, StringBuilder sb) {
		if (root == null) return;
		sb.append(root.val).append(",");
		serialize(root.left, sb);
		serialize(root.right, sb);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data.isEmpty()) return null;
		Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
		return deserialize(q, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public TreeNode deserialize(Queue<String> q, int lower, int upper) {
		if (q.isEmpty()) {
			return null;
		}
		String s = q.peek();
		int val = Integer.parseInt(s);

		/**
		 * key here
		 * for pre order get get
		 *
		 * root left1 left2 leftX right1 rightX
		 *
		 * If we look at the value of the pre-order traversal we get this:
		 * rootValue (<rootValue) (<rootValue) (<rootValue) |separate line| (>rootValue) (>rootValue)
		 *
		 * so the upper bound of left side is root value
		 * the lower bound of right side is root value
		 */
		if (val < lower || val > upper) {
			return null;
		}
		q.poll();
		TreeNode root = new TreeNode(val);
		root.left = deserialize(q, lower, val);
		root.right = deserialize(q, val, upper);
		return root;
	}
}
