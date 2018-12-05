package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

/**
 Given a binary tree, find its maximum depth.

 The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

 Note: A leaf is a node with no children.

 Example:

 Given binary tree [3,9,20,null,null,15,7],

     3
    / \
   9  20
  /     \
 15     7
 return its depth = 3.
 */

@LinkedIn
@Google

@Easy
@Tree
@DFS
public class MaxDepth
{
    /**
     * alway understand as find tree height
     */
    public int maxDepth(TreeNode root)
    {
        if (null == root)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }
}
