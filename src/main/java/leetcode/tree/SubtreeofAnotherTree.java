package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

import java.util.Stack;

/**
 572. Subtree of Another Tree

 Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

 Example 1:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 Given tree t:
 4
 / \
 1   2
 Return true, because t has the same structure and node values with a subtree of s.
 Example 2:
 Given tree s:

 3
 / \
 4   5
 / \
 1   2
 /
 0
 Given tree t:
 4
 / \
 1   2
 */

@Easy
@Tree
public class SubtreeofAnotherTree {

	/**
	 * Based on isSame tree
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null) return false;
		if (isSameTree(s, t)) return true;

		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}
		if (p == null || q == null) {
			return false;
		}
		if (p.value == q.value) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		} else {
			return false;
		}
	}

	/**
	 * itr solution, serizalized tree and use string contain to do
	 */
	public boolean isSubtreeItr(TreeNode s, TreeNode t) {
		String spreorder = generatepreorderString(s);
		String tpreorder = generatepreorderString(t);

		return spreorder.contains(tpreorder) ;
	}
	public String generatepreorderString(TreeNode s){
		StringBuilder sb = new StringBuilder();
		Stack<TreeNode> stacktree = new Stack();
		stacktree.push(s);
		while(!stacktree.isEmpty()){
			TreeNode next = stacktree.pop();
			if(next==null)
				sb.append(",#"); // Appending # inorder to handle same values but not subtree cases
			else
				sb.append(","+next.value);
			if(next!=null){
				stacktree.push(next.right);
				stacktree.push(next.left);
			}
		}
		return sb.toString();
	}
}
