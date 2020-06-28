package leetcode.dfs.tree;

import algorithm.sorting.BSTUtil;
import leetcode.basicDto.TreeNode;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;

/**
 * Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree,
 * and every node has no left child and only 1 right child.

 Example 1:
 Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

             5
            / \
          3    6
         / \    \
        2   4    8
       /        / \
      1        7   9

 Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

   1
    \
    2
     \
      3
       \
        4
         \
          5
           \
            6
             \
              7
               \
                8
                 \
                  9
 */

@Tree
@DFS
public class IncreasingOrderSearchTree {

	/**
	 * most standard template of dfs
	 */
	public static TreeNode increasingBST(TreeNode root) {
		if (root == null) return root;

		TreeNode dummyHead = new TreeNode(0);
		TreeNode prev = dummyHead;
		TreeNode walker = root;

		Stack<TreeNode> stack = new Stack<>();

		while (!stack.isEmpty() || walker != null )
		{
			if(walker!=null){
				stack.push(walker);
				walker=walker.left;
			} else {
				// hit smallest for the first iteration here
				TreeNode temp = stack.pop();
				prev.right = temp;
				prev=prev.right;
				//
				walker = temp.right;
				// node is kit to the reft most;
				temp.left = null;
			}
		}

		return dummyHead.right;
	}

	public static void main(String[] args) {
		int[] input = {0,6,2,8,0,4,7,9,-1,-1,2,6};
		TreeNode root = BSTUtil.toPositiveBST(input);
		increasingBST(root);
	}
}
