package leetcode.dfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

/*
1022. Sum of Root To Leaf Binary Numbers

Given a binary tree, each node has value 0 or 1.  Each root-to-leaf path represents a binary number starting with the most significant bit.  For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.

For all leaves in the tree, consider the numbers represented by the path from the root to that leaf.

Return the sum of these numbers.



Example 1:



Input: [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22


Note:

The number of nodes in the tree is between 1 and 1000.
node.val is 0 or 1.
The answer will not exceed 2^31 - 1.
 */

@Tree
@DFS
@Easy
public class SumOfRootToLeafBinaryNumbers {

    // recursively java binary to decimal
    public int sumRootToLeaf(TreeNode root) {
        return pathSumRootToLeaf(root, 0);
    }

    // key is also to stop sum when root is actually null or root is currently leaf both kid is null
    // 2^n * nth bit + 2^n-1 * n-1th bit + ... + 2^0 * 0th bit
    // turn sumLeft(sum) + sumRight(sum) as each node will  be actually used twice
    private int pathSumRootToLeaf(TreeNode root, int sum){
        if(root == null) return 0;

        sum = 2 * sum + root.val;
        if(root.left == null && root.right == null){
            return sum;
        }

        return pathSumRootToLeaf(root.left, sum) + pathSumRootToLeaf(root.right, sum);
    }
}
