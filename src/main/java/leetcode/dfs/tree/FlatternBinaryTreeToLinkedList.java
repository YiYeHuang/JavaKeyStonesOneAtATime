package leetcode.dfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;

/*
114. Flatten Binary Tree to Linked List
Given a binary tree, flatten it to a linked list in-place.

For example, given the following tree:

    1
   / \
  2   5
 / \   \
3   4   6
The flattened tree should look like:

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
 */

@Medium
@Tree
@DFS
public class FlatternBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stk = new Stack<TreeNode>();
        stk.push(root);
        while (!stk.isEmpty()){
            TreeNode curr = stk.pop();
            if (curr.right!=null)
                stk.push(curr.right);
            if (curr.left!=null)
                stk.push(curr.left);
            if (!stk.isEmpty())
                curr.right = stk.peek();
            curr.left = null;  // dont forget this!!
        }
    }

    public void flatten_itr(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }

            if (!stack.isEmpty()) {
                current.right = stack.peek();
            }

            current.left = null;
        }
    }


    /**
     *     1
     *    / \
     *   2   5
     *  / \   \
     * 3   4   6
     * -----------
     * pre = 5
     * cur = 4
     *
     *     1
     *    /
     *   2
     *  / \
     * 3   4
     *      \
     *       5
     *        \
     *         6
     * -----------
     * pre = 4
     * cur = 3
     *
     *     1
     *    /
     *   2
     *  /
     * 3
     *  \
     *   4
     *    \
     *     5
     *      \
     *       6
     * -----------
     * cur = 2
     * pre = 3
     *
     *     1
     *    /
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     * -----------
     * cur = 1
     * pre = 2
     *
     * 1
     *  \
     *   2
     *    \
     *     3
     *      \
     *       4
     *        \
     *         5
     *          \
     *           6
     */
    private TreeNode prev = null;

    public void flatten_recur(TreeNode root) {
        if (root == null)
            return;
        flatten_recur(root.right);
        flatten_recur(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

}
