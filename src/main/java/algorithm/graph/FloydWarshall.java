package algorithm.graph;

import algorithm.graph.dijkstra.DijkstraShortestGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
In this assignment you will implement one or more algorithms for the all-pairs shortest-path problem.
Here are data files describing three graphs:

The first line indicates the number of vertices and edges, respectively.
Each subsequent line describes an edge (the first two numbers are its tail and head, respectively)
and its length (the third number). NOTE: some of the edge lengths are negative.
NOTE: These graphs may or may not have negative-cost cycles.

Your task is to compute the "shortest shortest path". Precisely, you must first identify which, if any,
of the three graphs have no negative cycles. For each such graph, you should compute all-pairs shortest
paths and remember the smallest one (i.e., compute minu,v?Vd(u,v), where d(u,v)d(u,v)
denotes the shortest-path distance from uu to vv).

If each of the three graphs has a negative-cost cycle, then enter "NULL" in the box below.
If exactly one graph has no negative-cost cycles, then enter the length of its shortest shortest path in the box below.
If two or more of the graphs have no negative-cost cycles, then enter
the smallest of the lengths of their shortest shortest paths in the box below.

OPTIONAL: You can use whatever algorithm you like to solve this question. If you have extra time,
try comparing the performance of different all-pairs shortest-path algorithms!

OPTIONAL: Here is a bigger data set to play with.

For fun, try computing the shortest shortest path of the graph in the file above.
 */
public class FloydWarshall {

    public FloydWarshall(String filePath) throws FileNotFoundException {
        Scanner in = new Scanner(new File(filePath));

        int nodes = in.nextInt();
        int edges = in.nextInt();

        long adj[][] = new long[nodes][nodes];

        // init edge cost with large value
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                adj[i][j] = 1000000000L;
            }
        }

        // filled in with known cost
        for(int i=0; i<edges; i++) {
            int x = in.nextInt() -1;
            int y = in.nextInt() -1;
            int c = in.nextInt();

            adj[x][y] = c;
        }

        long ans = Long.MAX_VALUE;
        // floydwarshall main
        // for every node, if there exist a path where i, k to k, j where the path is shorter, update
        // if there exist
        for(int k=0; k<nodes; k++) {
            for(int i=0; i<nodes; i++){
                for(int j=0; j<nodes; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
                    ans = Math.min(adj[i][j], ans);
                }
            }
        }

        boolean cycle = false;

        for(int i=0; i<nodes; i++)
            if(adj[i][i] < 0)
                cycle = true;

        if(cycle)
            System.out.println("NULL");
        else
            System.out.println(ans);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //Graph g = new Graph("SimpleInput.txt");
        FloydWarshall g = new FloydWarshall("resource/g3.txt");
    }

}
