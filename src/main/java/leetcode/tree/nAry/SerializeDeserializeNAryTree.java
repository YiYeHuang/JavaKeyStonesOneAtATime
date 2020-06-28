package leetcode.tree.nAry;

import leetcode.basicDto.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Tree;

/*
428. Serialize and Deserialize N-ary Tree

Serialization is the process of converting a data structure or object
into a sequence of bits so that it can be stored in a file or memory buffer,
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize an N-ary tree.
An N-ary tree is a rooted tree in which each node has no more than N children.
There is no restriction on how your serialization/deserialization algorithm should work.
You just need to ensure that an N-ary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following 3-ary tree


as [1 [3[5 6] 2 4]]. Note that this is just an example, you do not necessarily need to follow this format.

Or you can follow LeetCode's level order traversal serialization format,
where each group of children is separated by the null value.



For example, the above tree may be serialized as
[1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14].

You do not necessarily need to follow the above suggested formats,
there are many more different formats that work so please be creative and come up with different approaches yourself.


Constraints:

The height of the n-ary tree is less than or equal to 1000
The total number of nodes is between [0, 10^4]
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 */

@Hard
@Tree
public class SerializeDeserializeNAryTree {

  // Encodes a tree to a single string.
  public static  String serialize(Node root) {
    if (root == null) return null;
    StringBuilder sb = new StringBuilder();
    serialize(root,sb);
    return sb.toString().substring(0, sb.length() - 1);
  }

  private static void serialize(Node root, StringBuilder sb) {
    if (root == null) {
      return;
    } else {
      sb.append(root.val).append(",").append(root.children.size()).append(",");
      for (Node child : root.children) {
        serialize(child, sb);
      }
    }
  }

  // Decodes your encoded data to tree.
  public static Node deserialize(String data) {
    if(data == null || data.length() == 0)
      return null;

    String[] result =data.split(",");
    Queue<String> q=new LinkedList<>(Arrays.asList(result));
    return deserialize(q);
  }

  private static Node deserialize(Queue<String> q){
    Node root=new Node();
    root.val=Integer.parseInt(q.poll());
    int size=Integer.parseInt(q.poll());
    root.children=new ArrayList<>(size);
    for(int i=0;i<size;i++){
      root.children.add(deserialize(q));
    }

    return root;
  }

  public static void main(String[] args) {
    Node root1 = new Node();
    root1.val = 1;
    root1.children = new ArrayList<>();

    Node root3 = new Node();
    root3.val = 3;
    root3.children = new ArrayList<>();

    Node root2 = new Node();
    root2.val = 2;
    root2.children = new ArrayList<>();

    Node root4 = new Node();
    root4.val = 4;
    root4.children = new ArrayList<>();

    Node root5 = new Node();
    root5.val = 5;
    root5.children = new ArrayList<>();
    Node root6 = new Node();
    root6.val = 6;
    root6.children = new ArrayList<>();


    root1.children.add(root3);
    root1.children.add(root2);
    root1.children.add(root6);

    root3.children.add(root5);

    root3.children.add(root6);


    String result = serialize(null);
  }
}
