package leetcode.dfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;

/*
783. Minimum Distance Between BST Nodes

Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.

Example :

Input: root = [4,2,6,1,3,null,null]
Output: 1
Explanation:
Note that root is a TreeNode object, not an array.

The given tree [4,2,6,1,3,null,null] is represented by the following diagram:

          4
        /   \
      2      6
     / \
    1   3

while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 */
@Easy
@DFS
@Tree
public class MinimumDistanceBetweenBST {

    public int minDiffInBST(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prevousCache = null;

        int min = Integer.MAX_VALUE;

        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (prevousCache != null) {
                min = Math.min(min, root.val - prevousCache.val);
            }

            prevousCache = root;
            root = root.right;
        }
        return min;
    }
}
