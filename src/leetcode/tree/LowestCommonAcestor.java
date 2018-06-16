package leetcode.tree;

import java.util.ArrayList;
import java.util.List;

import basic.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes
 * in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes v and w as the lowest node in T that has both v
 * and w as descendants (where we allow a node to be a descendant of itself).”
 * 
 *       _______3______ 
 *    /                   \ 
 * ___5__                ___1__ 
 * /     \              /      \ 
 *6      _2            0        8 
 *      /   \ 
 *     7     4 
 *             \
 *               3
 * For example, the
 * lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of
 * nodes 5 and 4 is 5, since a node can be a descendant of itself according to
 * the LCA definition.
 *
 */
public class LowestCommonAcestor
{
    /**
     * initial thinking
     * 
     * 3, 5, 1, 6, 2, 0, 8, null, null, 7, 4, null, null, null, null
     * 
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
    {
        List<TreeNode> list = new ArrayList<TreeNode>();
        list.add(root);
        int counter = 0;
        //while()
        return null;
    }

    public TreeNode lowestCommonAncestorANSWER(TreeNode root, TreeNode p, TreeNode q)
    {
        if(root.value<Math.min(p.value,q.value)) return lowestCommonAncestor(root.right,p,q);
        if(root.value>Math.max(p.value,q.value)) return lowestCommonAncestor(root.left,p,q);
        return root;
    }

    public TreeNode lowestCommonAncestorMath(TreeNode root, TreeNode p, TreeNode q)
    {
        while ((root.value - p.value) * (root.value - q.value) > 0)
            root = p.value < root.value ? root.left : root.right;
        return root;
    }

    public static void main(String[] args)
    {
        
    }
}
