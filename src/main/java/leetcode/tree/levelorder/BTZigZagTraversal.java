package leetcode.tree.levelorder;

import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 103. Binary Tree Zigzag Level Order Traversal


 Share
 Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
      3
     / \
    9  20
   /     \
 15      7

 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 */

@Microsoft
@Amazon
@LinkedIn
@Google
@Facebook

@Medium
@StackTag
@Tree
@BFS
public class BTZigZagTraversal {

	/**
	 * Level order with flipping
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
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

		if (level % 2 == 0) {
			ans.get(level).add(curr.value);
		} else {
			ans.get(level).add(0, curr.value);
		}


		compute(ans,curr.left,level+1);
		compute(ans,curr.right,level+1);
	}
}