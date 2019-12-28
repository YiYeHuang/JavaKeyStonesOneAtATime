package leetcode.tree;

import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;
import baseObj.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 */

public class SymmetricTree {

    @Tree
    @DFS
    @BFS
    /**
     * O(n)
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelp(root.right, root.left);
    }

    private boolean isSymmetricHelp(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null )
            return true;
        if (n1 == null || n2 == null ) {
            return false;
        }

        return (n1.val == n2.val) && isSymmetricHelp(n1.left, n2.right) && isSymmetricHelp(n1.right, n2.left);
    }

    /**
     * BFS + queue
     *
     */
    public boolean isSymmetricItr(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
