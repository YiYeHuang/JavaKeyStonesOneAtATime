package algorithm.greedy.mst;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
Question 3
In this programming problem you'll code up Prim's minimum spanning tree algorithm.

Download the text file below.

edges.txt
This file describes an undirected graph with integer edge costs. It has the format

[number_of_nodes] [number_of_edges]

[one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]

[one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]

...

For example, the third line of the file is "2 3 -8874",
indicating that there is an edge connecting vertex #2 and vertex #3 that has cost -8874.

You should NOT assume that edge costs are positive, nor should you assume that they are distinct.

Your task is to run Prim's minimum spanning tree algorithm on this graph.
You should report the overall cost of a minimum spanning tree --- an integer, which may or may not be negative --- in the box below.

IMPLEMENTATION NOTES: This graph is small enough that the straightforward O(mn) time implementation of Prim's algorithm should work fine.
OPTIONAL: For those of you seeking an additional challenge, try implementing a heap-based version.
The simpler approach, which should already give you a healthy speed-up,
is to maintain relevant edges in a heap (with keys = edge costs).
The superior approach stores the unprocessed vertices in the heap, as described in lecture.
Note this requires a heap that supports deletions, and you'll probably need to maintain some kind of mapping between vertices and their positions in the heap.
 */
public class PrimMST {

    // node id, edges
    HashMap<Integer, ArrayList<int[]>> vertices;
    boolean[] explored;
    int numberOfVertices;
    int numberOfEdges;

    public PrimMST(String inputFileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(inputFileName));
        numberOfVertices = in.nextInt();
        numberOfEdges = in.nextInt();
        vertices = new HashMap<>();
        for (int i = 1; i <= numberOfVertices; i++) {
            vertices.put(i, new ArrayList<int[]>());
        }
        for (int i = 0; i < numberOfEdges; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int cost = in.nextInt();
            vertices.get(u).add(new int[]{v, cost});
            vertices.get(v).add(new int[]{u, cost});
        }
        in.close();
    }

    // node id, Cost
    HashMap<Integer, Integer> unexplored = new HashMap<>();

    public int computeMST() {
        explored = new boolean[numberOfVertices];
        // prim's mst does not define the start of a mst, so use the node 1;
        explored[0] = true;

        int sumCost = 0;
        // path 1. built the heap, and record edge value around the starting node, get all the edges value from it and store in the heap
        for (int i = 1; i <= numberOfVertices; i++) {
            int minCost = 1000000000;

            // edge[node id, cost]
            for (int[] edge : vertices.get(i)) {
                // build only the edge connected to root node (node 1)
                if (edge[0] == 1 && edge[1] < minCost) {
                    minCost = edge[1];
                }
            }
            unexplored.put(i, minCost);
        }

        // starting from root 2
        for (int i = 1; i < numberOfVertices; i++) {
            // keep finding the next min edge, ideally using a heap
            int[] u = getMinEdge();
            //
            for (int[] edge : vertices.get(u[0])) {
                int v = edge[0];
                if (!explored[v - 1]) {
                    int cost = edge[1];
                    if (cost < unexplored.get(v)) {
                        unexplored.put(v, cost);
                    }
                }
            }
            explored[u[0] - 1] = true;
            sumCost = sumCost + u[1];
        }
        return sumCost;
    }

    //
    public int[] getMinEdge() {
        int minCost = Integer.MAX_VALUE;
        int minIndex = -1;
        for (Map.Entry<Integer, Integer> a : unexplored.entrySet()) {
            if (a.getValue() <minCost)  {
                minCost = a.getValue();
                minIndex = a.getKey();
            }
        }

        unexplored.remove(minIndex);
        return new int[]{minIndex, minCost};
    }

    public static void main(String[] args) throws FileNotFoundException {
        PrimMST graph = new PrimMST("resource/edges_prim.txt");
        // Graph graph = new Graph("SmallInput.txt");
        System.out.println(graph.computeMST());
    }
}
