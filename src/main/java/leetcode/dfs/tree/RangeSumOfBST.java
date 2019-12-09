package leetcode.dfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;


/*
938. Range Sum of BST

Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.



Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23


Note:

The number of nodes in the tree is at most 10000.
The final answer is guaranteed to be less than 2^31.
 */

@Easy
@DFS
@Tree
public class RangeSumOfBST {

    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) return 0; // base case.
        return (L <= root.val && root.val <= R ? root.val : 0) + rangeSumBST(root.right, L, R) + rangeSumBST(root.left, L, R);
    }

    public int rangeSumBST_stack(TreeNode root, int L, int R) {
        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        int sum = 0;
        while (!stk.isEmpty()) {
            TreeNode n = stk.pop();
            if (n == null) {
                continue;
            }
            if (n.val > L) {
                stk.push(n.left);
            } // left child is a possible candidate.
            if (n.val < R) {
                stk.push(n.right);
            } // right child is a possible candidate.
            if (L <= n.val && n.val <= R) {
                sum += n.val;
            }
        }
        return sum;
    }
}
