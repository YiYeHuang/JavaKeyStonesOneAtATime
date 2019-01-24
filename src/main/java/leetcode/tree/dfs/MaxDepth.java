package leetcode.tree.dfs;

import baseObj.TreeNode;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
@BFS
public class MaxDepth
{
    /**
     * alway understand as find tree height
     *
     * Recur version
     */
    public int maxDepthRecur(TreeNode root)
    {
        if (null == root)
        {
            return 0;
        }
        else
        {
            return 1 + Math.max(maxDepthRecur(root.left), maxDepthRecur(root.right));
        }
    }

    /**
     * DFS, use stack two stack to track node and height.
     *
     * moving down with keep popping stack, same level saves same height value
     */
    public int maxDepthDFS(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> value = new Stack<>();

        // level 1
        stack.push(root);
        value.push(1);
        int maxHeight = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            int tempHight = value.pop();

            maxHeight = Math.max(tempHight, maxHeight);

            if (node.left != null) {
                stack.push(node.left);
                value.push(tempHight + 1);
            }

            if (node.right != null) {
                stack.push(node.right);
                value.push(tempHight + 1);
            }
        }

        return maxHeight;
    }

    /**
     * BFS
     */
    public int maxDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            // consume all the current level node, which means one level is passed, level++
            while (size > 0) {
                TreeNode node = queue.poll();
                size --;
                if (node.left != null) {
                    queue.offer(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }

        return level;
    }

}
