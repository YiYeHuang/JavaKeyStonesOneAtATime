package leetcode.dfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

/*
669. Trim a Binary Search Tree

Given a binary search tree and the lowest and highest boundaries as L and R,
trim the tree so that all its elements lies in [L, R] (R >= L).
You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input:
    1
   / \
  0   2

  L = 1
  R = 2

Output:
    1
      \
       2
Example 2:
Input:
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output:
      3
     /
   2
  /
 1
 */

@DFS
@Tree
@Easy
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }

        if (root.val < L) return trimBST(root.right, L, R); // return the possible right node, to be new root or otherNode's correct kids
        if (root.val > R) return trimBST(root.left, L, R); // return the possible left node, to be new root or otherNode's correct kids

        // Trim happens here, form the new tree
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }
}
