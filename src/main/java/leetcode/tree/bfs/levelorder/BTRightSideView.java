package leetcode.tree.bfs.levelorder;

import baseObj.TreeNode;
import leetcode.tag.company.*;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. Binary Tree Right Side View
 *
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 *
 * Example:
 *
 * Input: [1,2,3,null,5,null,4]
 * Output: [1, 3, 4]
 * Explanation:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
@Facebook
@Amazon
@Apple
@Microsoft
@Bloomberg

@Medium
@Tree
@DFS
@BFS
public class BTRightSideView {

    /**
     * Level order template,  keep updating latest, since right is always visited last, the remaining is always right
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        bulid(ans,root,0);
        return ans;
    }

    public void bulid(List<Integer> input, TreeNode curr,int level) {

        if(curr==null) return;

        if(input.size()==level) {
            input.add(Integer.MIN_VALUE);
        }

        input.set(level, curr.value);

        bulid(input,curr.left,level+1);
        bulid(input,curr.right,level+1);
    }
}
