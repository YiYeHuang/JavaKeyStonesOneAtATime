package leetcode.tree;

import leetcode.basicDto.TreeNode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Tree;

/*
297. Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example:

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */

@Tree
@Hard
public class SerializeDeserializeBT {


  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    serialize(root, sb);
    return sb.toString();
  }

  // preorder
  // use # to represent null
  private void serialize(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append("#").append(",");
    } else {
      sb.append(root.val).append(",");
      serialize(root.left, sb);
      serialize(root.right, sb);
    }
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    Queue<String> q = new LinkedList<>(Arrays.asList(data.split(",")));
    return deserialize(q);
  }

  private TreeNode deserialize(Queue<String> q) {
    String s = q.poll();
    if (s.equals("#")) return null;
    TreeNode root = new TreeNode(Integer.parseInt(s));
    root.left = deserialize(q);
    root.right = deserialize(q);
    return root;
  }


  public static void main(String[] args) {

    //        1
    //   2        3
    // 5   4
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);

    root.left.right = new TreeNode(4);
    root.left.left = new TreeNode(5);

    SerializeDeserializeBT sbt = new SerializeDeserializeBT();
    String result = sbt.serialize(root);
  }
}
