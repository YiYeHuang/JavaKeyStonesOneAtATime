package leetcode.bfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 *
 * Given a binary tree, find the leftmost value in the last row of the tree.
 *
 * Example 1:
 * Input:
 *
 *     2
 *    / \
 *   1   3
 *
 * Output:
 * 1
 * Example 2:
 * Input:
 *
 *         1
 *        / \
 *       2   3
 *      /   / \
 *     4   5   6
 *        /
 *       7
 *
 * Output:
 * 7
 */

@Medium
@Tree
@BFS
public class FindBottomLeftTreeValue {

    /**
     *           1
     *         / \
     *        2   3
     *       /   / \
     *      4   5   6
     *         /
     *        7
     *      <---------
     *      BFS from right to left, so last remaining value is left most
     */
    public int findBottomLeftValue(TreeNode root) {
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            // consume all the current level node, which means one level is passed, level++
            while (size > 0) {
                root = queue.poll();
                size --;
                if (root.right != null) {
                    queue.offer(root.right);
                }

                // pushes left later, so the left side is the last left in queue
                if (root.left != null) {
                    queue.offer(root.left);
                }
            }
        }
        return root.val;
    }
}
