package leetcode.tree.pathSum;

import baseObj.TreeNode;
import leetcode.tag.company.Google;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 113. Path Sum II
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Given the below binary tree and sum = 22,
 *
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \    / \
 * 7    2  5   1
 * Return:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */

@Google

@Medium
@Tree
@DFS
public class PathSumII {

    /**
     * DFS but does feels like backtrack a little
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        build(result, new ArrayList<>(), sum, root);
        return result;
    }

    /**
     * pre order
     */
    public void build(List<List<Integer>> result, List<Integer> temp, int sum, TreeNode root) {
        if (root == null) return;
        temp.add(root.val);

        // add up all
        if (root.left == null && root.right == null && sum - root.val == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }

        //?????????
        if(root.left!=null) {
            build(result, temp, sum - root.val, root.left);
            temp.remove(temp.size() - 1);
        }
        //?????????
        if(root.right!=null) {
            build(result, temp, sum - root.val, root.right);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * Iterator solution
     */
    public List<List<Integer>> pathSumItr(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int SUM = 0;
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur!=null || !stack.isEmpty()){
            while(cur!=null){
                stack.push(cur);
                path.add(cur.val);
                SUM+=cur.val;
                cur=cur.left;
            }
            cur = stack.peek();
            if(cur.right!=null && cur.right!=pre){
                cur = cur.right;
                continue;
            }
            if(cur.left==null && cur.right==null && SUM==sum)
                res.add(new ArrayList<Integer>(path));

            pre = cur;
            stack.pop();
            path.remove(path.size()-1);
            SUM-=cur.val;
            cur = null;

        }
        return res;
    }
}
