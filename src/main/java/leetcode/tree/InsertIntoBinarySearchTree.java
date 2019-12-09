package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Google;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Tree;

/**
 * Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.
 *
 * Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.
 *
 * For example,
 *
 * Given the tree:
 *         4
 *        / \
 *       2   7
 *      / \
 *     1   3
 * And the value to insert: 5
 * You can return this binary search tree:
 *
 *          4
 *        /   \
 *       2     7
 *      / \   /
 *     1   3 5
 * This tree is also valid:
 *
 *          5
 *        /   \
 *       2     7
 *      / \
 *     1   3
 *          \
 *           4
 */

@Google
@Amazon

@Medium
@Tree
public class InsertIntoBinarySearchTree {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            traceInsert(root, val);
        }

        return root;
    }
    private void traceInsert(TreeNode currentRoot, int value) {
        if (value > currentRoot.val) {
            if (null == currentRoot.right) {
                currentRoot.right = new TreeNode(value);
            } else {
                traceInsert(currentRoot.right, value);
            }
        } else if (value < currentRoot.val) {
            if (null == currentRoot.left) {
                currentRoot.left = new TreeNode(value);
            } else {
                traceInsert(currentRoot.left, value);
            }
        }
    }
}
