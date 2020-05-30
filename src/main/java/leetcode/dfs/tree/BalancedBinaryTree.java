package leetcode.dfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;

/**
 * 110. Balanced Binary Tree
 *
 * Given a binary tree, determine if it is height-balanced.
 *
 * For this problem, a height-balanced binary tree is defined as:
 *
 * a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
 *
 * Example 1:
 *
 * Given the following tree [3,9,20,null,null,15,7]:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * Return true.
 *
 * Example 2:
 *
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * Return false.
 *
 */

@Easy
@DFS
public class BalancedBinaryTree {

    /**
     * n^2
     * directly base off max height of the tree, the logic is very clear,
     * but two recursion is no good
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(depth(root.left) - depth(root.right)) >1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int depth(TreeNode root){
        if (root == null)
            return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    /**
     *
     * We determine recursively the height of the root node but when the recursion is coming upwards
     * we return UNBALANCED instead of the actual height if we know that the tree is already known to be unbalanced.
     */
    private static final int UNBALANCED = -1;

    public boolean isBalanced_2(TreeNode root) {
        return heightCheck(root) != UNBALANCED;
    }

    // return the height of the tree if balanced
    int heightCheck(TreeNode root) {
        if (root == null) return 0;

        int leftHight = heightCheck(root.left);
        int rightHight = heightCheck(root.right);
        int diff = Math.abs(leftHight - rightHight);

        if ((leftHight != UNBALANCED && rightHight != UNBALANCED ) &&  (diff <= 1)) {
            return Math.max(leftHight,rightHight) + 1;
        }

        return UNBALANCED;
    }
}
