package leetcode.tree.bfs.levelorder;

import baseObj.TreeLinkNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 117. Populating Next Right Pointers in Each Node II
 * 
 Given a binary tree

 struct TreeLinkNode {
 TreeLinkNode *left;
 TreeLinkNode *right;
 TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 Example:

 Given the following binary tree,

       1
     /  \
    2    3
   / \    \
 4   5    7
 After calling your function, the tree should look like:

        1 -> NULL
      /  \
     2 -> 3 -> NULL
    / \    \
   4-> 5 -> 7 -> NULL
 */

@Medium
@DFS
@Tree
public class PopulateNextRightPointersEachNodeII {

	/**
	 * Level order traversal, the universal solution
	 */
	public static void connect(TreeLinkNode root) {
		List<List<TreeLinkNode>> ans=new ArrayList<>();
		compute(ans,root,0);
		for (int i = 0; i < ans.size(); i ++) {
			for (int j = 0; j < ans.get(i).size() - 1; j++) {
				ans.get(i).get(j).next = ans.get(i).get(j + 1);
			}
		}
	}

	public static void compute(List<List<TreeLinkNode>> ans,TreeLinkNode curr,int level)
	{
		if (curr == null) return;

		if (ans.size() == level) {
			ans.add(new ArrayList<>());
		}

		ans.get(level).add(curr);
		compute(ans,curr.left,level+1);
		compute(ans,curr.right,level+1);
	}
}
