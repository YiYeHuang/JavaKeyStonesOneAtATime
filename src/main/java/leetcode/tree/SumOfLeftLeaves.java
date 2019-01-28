package leetcode.tree;

import baseObj.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 404. Sum of Left Leaves
 *
 * Find the sum of all left leaves in a given binary tree.
 *
 * Example:
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
 */
public class SumOfLeftLeaves {

    /**
     * Recursive
     */
    int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        build(root);
        return sum;
    }

    public void build(TreeNode root) {
        if (root == null) return;

        if (root.left != null) {

            if (root.left.left == null && root.left.right.right == null) {
                sum += root.left.value;
            } else {
                build(root.left);
            }
        }

        build(root.right);
    }

    /**
     * DFS
     */
    public int sumOfLeftLeavesDFS(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        int result = 0;

        while(!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            if (curr.left != null) {
                if (curr.left.left == null && curr.left.right.right == null) {
                    result += curr.left.value;
                }
                stack.push(root.left);
            }
            if(curr.right != null) {
                // save checking time
                if (curr.right.left != null || curr.right.right != null)
                    stack.push(curr.right);
            }
        }

        return result;
    }

    /**
     * BFS
     */
    public int sumOfLeftLeavesBFS(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int result = 0;

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                if (curr.left.left == null && curr.left.right.right == null) {
                    result += curr.left.value;
                }
                queue.offer(root.left);
            }

                if(curr.right != null) queue.offer(curr.right);
        }

        return result;
    }

}