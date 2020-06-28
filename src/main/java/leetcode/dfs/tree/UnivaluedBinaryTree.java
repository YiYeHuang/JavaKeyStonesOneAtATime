package leetcode.dfs.tree;

import leetcode.basicDto.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
965. Univalued Binary Tree

A binary tree is univalued if every node in the tree has the same value.

Return true if and only if the given tree is univalued.



Example 1:


Input: [1,1,1,1,1,null,1]
Output: true
Example 2:


Input: [2,2,2,5,2]
Output: false
 */

@Easy
@Tree
public class UnivaluedBinaryTree {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prevousCache = null;

        while(root != null || !stack.isEmpty()) {
            while(root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if (prevousCache != null && prevousCache.val != root.val) return false;

            prevousCache = root;
            root = root.right;
        }
        return true;
    }

    public boolean isUnivalTree_queue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n.val != root.val) { return false; }
            if (n.left != null) { q.offer(n.left); }
            if (n.right != null) { q.offer(n.right); }
        }
        return true;
    }

    public boolean isUnivalTree_recur(TreeNode root) {
        return dfs(root, root.val);
    }
    private boolean dfs(TreeNode n, int v) {
        if (n == null) return true;
        if (n.val != v) { return false; }
        return dfs(n.left, v) && dfs(n.right, v);
    }
}
