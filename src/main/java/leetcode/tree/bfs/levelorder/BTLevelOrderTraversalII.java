package leetcode.tree.bfs.levelorder;

import baseObj.TreeNode;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;

import java.util.ArrayList;
import java.util.List;

/**

 107. Binary Tree Level Order Traversal II


 Favorite

 Share
 Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 		3
 	   / \
 	  9  20
     /     \
    15      7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 */

@Microsoft

@Easy
@BFS
@DFS
public class BTLevelOrderTraversalII {

	/**
	 * BFS: list + recursive
	 * transform from BTLevelOrderTraversal I, change the answer index
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> ans=new ArrayList<>();
		compute(ans,root,0);
		return ans;
	}

	public void compute(List<List<Integer>> ans,TreeNode curr,int level)
	{
		if(curr==null) return;

		// first in, push to the end
		if(ans.size()==level)
			ans.add(0,new ArrayList<Integer>());

		ans.get(ans.size()-level-1).add(curr.value);

		compute(ans,curr.left,level+1);
		compute(ans,curr.right,level+1);
	}

}
