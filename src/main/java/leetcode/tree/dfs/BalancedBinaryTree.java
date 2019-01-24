package leetcode.tree.dfs;

import baseObj.TreeNode;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
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
@Google
@Microsoft
@Facebook

@Easy
@DFS
public class BalancedBinaryTree {

    /**
     * directly base off max height of the tree, the logic is very clear,
     * but two recursion is no good
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        if (Math.abs(depth(root.left) - depth(root.right)) >1)
            return false;
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
    private static final int UNBALANCED = -99;

    public boolean isBalancedOPT(TreeNode root) {
        if (root == null) {
            return true;
        }
        return getHeight(root) != UNBALANCED;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int left = getHeight(root.left);
        int right = getHeight(root.right);
        if (left == UNBALANCED || right == UNBALANCED || Math.abs(left-right) > 1) {
            return UNBALANCED;
        }
        return 1 + Math.max(left,right);
    }
}
