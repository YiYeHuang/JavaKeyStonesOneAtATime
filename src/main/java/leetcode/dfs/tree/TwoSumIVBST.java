package leetcode.dfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
653. Two Sum IV - Input is a BST

Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True


Example 2:

Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False
 */

@Easy
@Tree
@DFS
public class TwoSumIVBST {


    public boolean findTarget_twoPtr(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();

        inorder(root, nums);

        int i = 0;
        int j = nums.size() - 1;

        while (i <= j) {
            if(nums.get(i) + nums.get(j) == k) return true;
            if(nums.get(i) + nums.get(j) < k) i++;
            else j--;
        }

        return false;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;

        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }

    public boolean findTarget_dfs(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }

    public boolean dfs(TreeNode root, HashSet<Integer> set, int k){
        if(root == null) return false;
        if(set.contains(k - root.val)) return true;

        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}
