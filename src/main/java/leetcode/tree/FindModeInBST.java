package leetcode.tree;

import algorithm.sorting.BSTUtil;
import baseObj.TreeNode;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s)
 * (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.


 For example:
 Given BST [1,null,2,2],

  1
   \
   2
 /
 2


 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */

@Google

@Easy
@Tree
public class FindModeInBST {

	public static int max = 0;

    /**
     * With extra solution, load the tree into map, keep increment the max of the tree
     *
     * Convert into Arraylist
     */
	public static int[] findMode(TreeNode root) {
		if (root == null) return new int[0];

		Map<Integer, Integer> result = new HashMap<>();
		TreeNode walker = root;
		load(walker, result);

		ArrayList<Integer> list = new ArrayList<Integer>();
		Iterator<Map.Entry<Integer, Integer>> itr = result.entrySet().iterator();
		while(itr.hasNext()) {
			Map.Entry<Integer, Integer> next = itr.next();
			if (max >= 1 && next.getValue() == max) {
				list.add(next.getKey());
			}
		}

		int[] res = new int[list.size()];
		int counter = 0;
		for (Integer item:list) {
			res[counter]=item;
			counter++;
		}
		return res;
	}

    /**
     * in order traversal to add all nodes into map
     */
	public  static void load(TreeNode root, Map<Integer, Integer> result) {
		if (null == root) return;

		load(root.left, result);
		if (result.containsKey(root.val)) {
			result.put(root.val, 1 + result.get(root.val));
			if (result.get(root.val) >= max) {
				max = result.get(root.val);
			}
		}
		else {
			result.put(root.val, 1);
			if (1 > max) {
				max=1;
			}
		}

		load(root.right, result);
	}

	public static void main(String[] args) {
		int[] input = {0,6,2,8,0,4,7,9,-1,-1,2,6};
		TreeNode root = BSTUtil.toPositiveBST(input);
		findMode(root);
	}
}
