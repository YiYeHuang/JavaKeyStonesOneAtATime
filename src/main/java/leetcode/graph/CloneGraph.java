package leetcode.graph;


import baseObj.Node;
import baseObj.NodeGraph;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DFS;
import leetcode.tag.type.Graph;

import java.util.*;


/*
133. Clone Graph

Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph.
Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.




Example:


Input:
{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},
{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}


Explanation:
Node 1's value is 1, and it has two neighbors: Node 2 and 4.
Node 2's value is 2, and it has two neighbors: Node 1 and 3.
Node 3's value is 3, and it has two neighbors: Node 2 and 4.
Node 4's value is 4, and it has two neighbors: Node 1 and 3.


Note:

The number of nodes will be between 1 and 100.
The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
You must return the copy of the given node as a reference to the cloned graph.
 */

@Medium
@Graph
@DFS
@BFS
public class CloneGraph {
    public Map<Integer, NodeGraph> cloneMap = new HashMap<>();

    // same as clone random list.
    public NodeGraph cloneGraph_DFS(NodeGraph node) {
        return clone(node);
    }

    // Clone first, recursion clone and find node
    private NodeGraph clone(NodeGraph node) {
        if (node == null) return null;

        if (cloneMap.containsKey(node.val)) {
            return cloneMap.get(node.val);
        }

        NodeGraph newNode = new NodeGraph(node.val, new ArrayList<>());
        cloneMap.put(newNode.val, newNode);

        for (NodeGraph neighbor : node.neighbors) {
            newNode.neighbors.add(clone(neighbor));
        }

        return newNode;
    }

    // Go through original, map the clone node
    public NodeGraph cloneGraph_BFS(NodeGraph root) {
        if (root == null) return null;

        // use a queue to help BFS
        Queue<NodeGraph> queue = new LinkedList<NodeGraph>();
        queue.add(root);

        // use a map to save cloned nodes
        //  original   clone
        Map<NodeGraph, NodeGraph> map = new HashMap<NodeGraph, NodeGraph>();

        // clone the root
        map.put(root, new NodeGraph(root.val, new ArrayList<>()));

        while (!queue.isEmpty()) {
            NodeGraph node = queue.poll();

            // handle the neighbors
            for (NodeGraph neighbor : node.neighbors) {
                if (!map.containsKey(neighbor)) {
                    // clone the neighbor
                    map.put(neighbor, new NodeGraph(neighbor.val, new ArrayList<>()));
                    // add it to the next level
                    queue.add(neighbor);
                }

                // copy the neighbor
                map.get(node).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(root);
    }
}
