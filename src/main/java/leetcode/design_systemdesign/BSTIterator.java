package leetcode.design_systemdesign;

import baseObj.TreeNode;
import leetcode.tag.company.*;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Tree;

import java.util.Stack;

/**
 *Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 * Credits:
 * Special thanks to @ts for adding this problem and creating all test cases.
 */

@Amazon
@Microsoft
@Facebook
@Bloomberg
@LinkedIn

@Medium
@Tree
@DFS
/**
 * O(h) in memory mean we cannot push all the nodes in and do the one by one iteration,
 * so idea is go down with one branch and find the current min and push all right sub branch to the stack
 */
public class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    
    public BSTIterator(TreeNode root) {
        TreeNode cur = root;
        while(cur != null){
            stack.push(cur);
            if(cur.left != null){
                cur = cur.left;
            }
            else {
                break;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode cur = node;

        // this is the key step, the first iteration only check all the left side, to find the next smallest,
        // need to push the next right node in and push all of it's left branch
        if(cur.right != null) {
            cur = cur.right;
            while(cur != null){
                stack.push(cur);
                if(cur.left != null){
                    cur = cur.left;
                }
                else {
                    break;
                }
            }
        }

        return node.val;
    }
}
