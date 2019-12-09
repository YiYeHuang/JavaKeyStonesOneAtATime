package leetcode.bfs.tree.levelorder;

import baseObj.TreeNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**

 637. Average of Levels in Binary Tree

 Favorite

 Share
 Given a non-empty binary tree, return the average value of the nodes on each level in the form of an array.
 Example 1:
 Input:
 3
 / \
 9  20
 /    \
 15      7
 Output: [3, 14.5, 11]
 Explanation:
 The average value of nodes on level 0 is 3,  on level 1 is 14.5, and on level 2 is 11. Hence return [3, 14.5, 11].
 Note:
 The range of node's value is in the range of 32-bit signed integer.

 */

@Amazon

@Easy
@BFS
@Tree
public class AverageLevelsinBinaryTree {

	/**
	 * Level order
	 *
	 * within same level order traversal, calculate both sum and count, and do the Maths
	 */
	public List<Double> averageOfLevels(TreeNode root) {
		if (root == null) return new ArrayList<Double>();

		List<Double> sum = new ArrayList<Double>();
		List<Double> count = new ArrayList<Double>();
		compute(sum, count, root, 0);

		for(int i=0;i<sum.size();i++) {
			sum.set(i,sum.get(i)/count.get(i));
		}
		return sum;
	}


	public void compute(List<Double> sum, List<Double> count,TreeNode curr,int level)
	{
		if(curr==null) return;

		if(sum.size()==level)
		{
			sum.add(0.0);
			count.add(0.0);
		}

		sum.set(level, sum.get(level)+(double)curr.val);
		count.set(level, count.get(level)+1);

		compute(sum,count,curr.left,level+1);
		compute(sum,count,curr.right,level+1);
	}

	/**
	 * BFS queue
	 *
	 *
	 */

	public List<Double>  maxDepth(TreeNode root) {
		if(root == null) {
			return new ArrayList<Double>();
		}
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);

		List<Double> avg = new ArrayList<Double>();

		while (!queue.isEmpty()) {
			int size = queue.size();
			// consume all the current level node, which means one level is passed, level++
			Double sum = 0.0;
			Double count = 0.0;
			while (size > 0) {
				TreeNode node = queue.poll();
				sum += node.val;
				count ++;
				size --;
				if (node.left != null) {
					queue.offer(node.left);
				}

				if (node.right != null) {
					queue.offer(node.right);
				}
			}
			avg.add(sum/count);
		}

		return avg;
	}
}