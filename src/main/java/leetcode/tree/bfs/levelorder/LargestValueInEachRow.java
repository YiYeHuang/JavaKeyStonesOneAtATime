package leetcode.tree.bfs.levelorder;

import baseObj.TreeNode;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**

 515. Find Largest Value in Each Tree Row


 Favorite

 Share
 You need to find the largest value in each row of a binary tree.

 Example:
 Input:

     1
    / \
   3   2
  / \   \
 5   3   9

 Output: [1, 3, 9]

 */

@LinkedIn

@Medium
@BFS
@Tree
public class LargestValueInEachRow {

	/**
	 * Level order, replace with max
	 */
	public List<Integer> largestValues(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		compute(ans,root,0);
		return ans;
	}

	public void compute(List<Integer> ans,TreeNode curr,int level)
	{
		if(curr==null) return;

		if(ans.size()==level) {
			ans.add(Integer.MIN_VALUE);
		}

		if (curr.value > ans.get(level)) {
			ans.set(level, curr.value);
		}

		compute(ans,curr.left,level+1);
		compute(ans,curr.right,level+1);
	}
}
