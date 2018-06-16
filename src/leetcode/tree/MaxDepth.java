package leetcode.tree;

import basic.TreeNode;

/**
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the root
 * node down to the farthest leaf node.
 *
 * 
 */
public class MaxDepth
{
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
