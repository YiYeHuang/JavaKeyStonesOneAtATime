package leetcode.tree.bst;

import baseObj.TreeNode;

import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 * Example 1:
 *
 * Input: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * Output: 1
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 */
@Medium
@BinarySearch
@DFS
@Tree
public class KthSmallestElementinBST {

    /**
     * Typical DFS traversal template with stack
     *
     * operate with counter
     * Extra memory usage
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> smallestCount = new Stack<>();

        // go all the way left
        while (root != null) {
            smallestCount.push(root);
            root = root.left;
        }

        while (k != 0) {
            TreeNode next = smallestCount.pop();
            k--;
            if (k== 0) {
                return next.val;
            }

            TreeNode right = next.right;
            while (right != null) {
                smallestCount.push(right);
                right = right.left;
            }
        }

        return -1;
    }

    public int kthSmallestTemplate(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if(--k == 0) break;
            root = root.right;
        }
        return root.val;
    }

    /**
     * ===============================================================================
     */
    public int kthSmallestRecur(TreeNode root, int k) {
        int count = countNodes(root.left);

        if (k < count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k -1 - count);// 1 is counted as current node
        }

        return root.val;
    }

    public int countNodes(TreeNode n) {
        if (n == null) return 0;

        return 1 + countNodes(n.left) + countNodes(n.right);
    }

    /**
     * ===============================================================================
     */

    private static int number = 0;
    private static int count = 0;

    public int kthSmallestPUREInOrder(TreeNode root, int k) {
        count = k;
        helper(root);
        return number;
    }

    public void helper(TreeNode n) {
        if (n.left != null) helper(n.left);
        count--;
        if (count == 0) {
            number = n.val;
            return;
        }
        if (n.right != null) helper(n.right);
    }
}
