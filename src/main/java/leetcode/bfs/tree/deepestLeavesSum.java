package leetcode.bfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;

import java.util.LinkedList;
import java.util.Queue;

/*
1302. Deepest Leaves Sum

Given a binary tree, return the sum of values of its deepest leaves.


Example 1:



Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
Output: 15
 */

@Medium
@DFS
@BFS
public class deepestLeavesSum {
    public int deepestLeavesSum_bfs(TreeNode root) {
        int result = 0;
        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            // reset everytime when hit a new level
            result = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                result += node.val;
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return result;
    }

    public int deepestLeavesSum_dfs(TreeNode root) {
        int maxDepth = depth(root);
        int[] res = {0};
        findSum(root, maxDepth, 1, res);
        return res[0];
    }

    private void findSum(TreeNode root, int maxDepth, int currentDepth, int[] res) {
        if (root == null) return;
        if (currentDepth == maxDepth) {
            res[0] += root.val;
        }
        findSum(root.left, maxDepth, currentDepth + 1, res);
        findSum(root.right, maxDepth, currentDepth + 1, res);
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
