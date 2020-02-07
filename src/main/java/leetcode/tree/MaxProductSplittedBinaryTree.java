package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Tree;

/*
1343. Maximum Product of Splitted Binary Tree

Given a binary tree root. Split the binary tree into two subtrees by
removing 1 edge such that the product of the sums of the subtrees are maximized.

Since the answer may be too large, return it modulo 10^9 + 7.

Example 1:

Input: root = [1,2,3,4,5,6]
Output: 110
Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
Example 2:

Input: root = [1,null,2,3,4,null,null,5,6]
Output: 90
Explanation:  Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
Example 3:

Input: root = [2,3,9,10,7,8,6,5,4,11,1]
Output: 1025
Example 4:

Input: root = [1,1]
Output: 1

Constraints:

Each tree has at most 50000 nodes and at least 2 nodes.
Each node's value is between [1, 10000].
 */

@Medium
@Tree
public class MaxProductSplittedBinaryTree {

    long sum;
    long maxProd;

    // two path:
    // - get sum
    // - check sum
    public void getSum(TreeNode root) {
        if(root == null) return;
        sum += (long)root.val;
        getSum(root.left);
        getSum(root.right);
    }

    /**
     * root.val + left + right = current sub tree sum max
     * Total sum - subTree Max = remaining tree max
     *
     * update product max
     */
    public long checkSubMax(TreeNode root) {
        if (root == null) return 0;
        long l = checkSubMax(root.left);
        long r = checkSubMax(root.right);
        maxProd = Math.max(maxProd, (l + r + root.val) * (sum - l - r - root.val));

        return l + r + root.val;
    }

    public int maxProduct(TreeNode root) {
        sum = 0;
        maxProd = 0;
        getSum(root);
        long prod = checkSubMax(root);

        // Since the answer may be too large, return it modulo 10^9 + 7.
        return (int)(maxProd % ((int)Math.pow(10, 9) + 7));
    }
}
