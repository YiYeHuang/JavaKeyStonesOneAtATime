package algorithm.sorting;

import leetcode.basicDto.TreeLinkNode;
import leetcode.basicDto.TreeNode;

public class BSTUtil {

	public static TreeNode toPositiveBST(int[] array) {
		TreeNode head = new TreeNode(array[1]);

		construct(head, array, 1);

		return head;
	}

	private static void construct(TreeNode node, int[] array, int index) {
		if (null == node || index > (array.length - 1)/2) return;

		if (-1 != array[2*index]) {
			node.left = new TreeNode(array[2*index]);
		} else {
			node.left = null;
		}

		if (-1 != array[2*index + 1]) {
			node.right = new TreeNode(array[2*index + 1]);
		} else {
			node.right = null;
		}

		construct(node.left, array, 2*index);
		construct(node.right, array, 2*index + 1);
	}

	public static TreeLinkNode toPositiveTreeNodeBST(int[] array) {
		TreeLinkNode head = new TreeLinkNode(array[1]);

		constructTreeNode(head, array, 1);

		return head;
	}

	private static void constructTreeNode(TreeLinkNode node, int[] array, int index) {
		if (null == node || index > (array.length - 1)/2) return;

		if (-1 != array[2*index]) {
			node.left = new TreeLinkNode(array[2*index]);
		} else {
			node.left = null;
		}

		if (-1 != array[2*index + 1]) {
			node.right = new TreeLinkNode(array[2*index + 1]);
		} else {
			node.right = null;
		}

		constructTreeNode(node.left, array, 2*index);
		constructTreeNode(node.right, array, 2*index + 1);
	}
}
