package leetcode.tree;

import baseObj.TreeNode;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. Binary Tree Paths
 *
 * Given a binary tree, return all root-to-leaf paths.
 *
 * Note: A leaf is a node with no children.
 *
 * Example:
 *
 * Input:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * Output: ["1->2->5", "1->3"]
 *
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
 */

@Tree
@DFS
public class BinaryTreePath {

    /**
     * recursion, preorder itr to visit all the the nodes in the tree.
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        build(result, "", root);
        return result;
    }

    public void build(List<String> result, String path, TreeNode curr) {
        if (curr == null ) return;
        if (curr.left == null && curr.right == null) {
            result.add(path + curr.value);
        }

        build(result, path + curr.value + "->", curr.left);
        build(result, path + curr.value + "->", curr.right);
    }
}
