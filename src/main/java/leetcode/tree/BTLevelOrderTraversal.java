package leetcode.tree;

import algorithm.sorting.BSTUtil;
import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 102. Binary Tree Level Order Traversal
 *
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */
@LinkedIn
@Amazon
@Facebook
@Microsoft
@Google

@Medium
@DFS
@BFS
@Tree
public class BTLevelOrderTraversal {

    /**
     * DFS Solution, go through each level and compute result
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        int level = maxHight(root);
        for (int i = 0; i < level; i++) {
            List<Integer> currLevel = new ArrayList<>();
            levelOrder(root, i, currLevel);
            result.add(currLevel);
        }

        return result;
    }

    public static void levelOrder(TreeNode root, int level, List<Integer> input) {
        if (root == null) {
            return ;
        } else {
            if (level == 0) {
                input.add(root.value);
                return;
            }
            levelOrder(root.left, level -1, input);
            levelOrder(root.right, level -1, input);
        }
    }

    public static int maxHight(TreeNode root) {
        if (root == null){
            return 0;
        } else {
            return 1 + Math.max(maxHight(root.left), maxHight(root.right));
        }
    }

    /**
     * opt of last solution
     *
     * use array size to do avoid the tree level extra traversal.
     */
    public List<List<Integer>> levelOrderOPT(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        compute(ans,root,0);
        return ans;
    }

    public void compute(List<List<Integer>> ans,TreeNode curr,int level)
    {
        if(curr==null) return;

        if(ans.size()==level) {
            ans.add(new ArrayList<Integer>());
        }

        ans.get(level).add(curr.value);

        compute(ans,curr.left,level+1);
        compute(ans,curr.right,level+1);
    }

    public static void main(String[] args) {
        int[] input = {0,6,2,8,0,4,7,9,-1,-1,2,6};
        TreeNode root = BSTUtil.toPositiveBST(input);
        levelOrder(root);
    }
}
