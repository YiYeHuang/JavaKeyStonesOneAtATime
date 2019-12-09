package leetcode.bfs.tree;

import baseObj.TreeNode;
import leetcode.tag.level.Hard;
import leetcode.tag.type.BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
297. Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format,
so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

@BFS
@Hard
public class SerializeAndDeserializeBinaryTree {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";

        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node == null) {
                res.append("E ");
                continue;
            }

            res.append(node.val).append(" ");
            queue.add(node.left);
            queue.add(node.right);
        }

        return res.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;

        Queue<TreeNode> q = new LinkedList<>();

        String[] values = data.split(" ");

        TreeNode root = new TreeNode(Integer.parseInt(values[0]));

        q.add(root);

        for (int i = 1; i < values.length; i++) {
            TreeNode parent = q.poll();
            if (!values[i].equals("E")) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            if (!values[++i].equals("E")) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {

        // [1,2,3,null,null,4,5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        SerializeAndDeserializeBinaryTree sd = new SerializeAndDeserializeBinaryTree();

        String value = sd.serialize(root);
        TreeNode got = sd.deserialize("1 2 3 E E 4 5");
    }
}
