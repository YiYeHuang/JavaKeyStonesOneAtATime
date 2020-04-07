package algorithm.greedy.unionFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
In this programming problem and the next you'll code up the clustering algorithm from lecture for computing a max-spacing kk-clustering.

This file describes a distance function (equivalently, a complete graph with edge costs). It has the following format:

[number_of_nodes]

[edge 1 node 1] [edge 1 node 2] [edge 1 cost]

[edge 2 node 1] [edge 2 node 2] [edge 2 cost]

...

There is one edge (i,j)(i,j) for each choice of 1?i<j?n, where nn is the number of nodes.

For example, the third line of the file is "1 3 5250", indicating that the distance between nodes 1 and 3
(equivalently, the cost of the edge (1,3)) is 5250. You can assume that distances are positive,
but you should NOT assume that they are distinct.

Your task in this problem is to run the clustering algorithm from lecture on this data set,
where the target number kk of clusters is set to 4. What is the maximum spacing of a 4-clustering?

ADVICE: If you're not getting the correct answer,
try debugging your algorithm using some small test cases. And then post them to the discussion forum!

 */
public class KClustering {

    static class Edge implements Comparable<Edge> {
        private int p;
        private int q;
        private int dist;

        private Edge(int p, int q, int dist) {
            this.p = p;
            this.q = q;
            this.dist = dist;
        }

        public int compareTo(Edge another) {
            return dist - another.dist;
        }
    }

    public static int calculate(int numPoints, Edge[] pairs, int k) {
        if (numPoints < k || k < 1) {
            throw new IllegalArgumentException();
        }

        Arrays.sort(pairs);
        UnionFind uf = new UnionFind(numPoints);
        int i = 0;
        while (uf.components() > k) {
            uf.unify(pairs[i].p, pairs[i++].q);
        }
        while (uf.connected(pairs[i].p, pairs[i].q)) {
            i++;
        }
        System.out.printf("close pair of separated nodes:%d %d %d%n", pairs[i].p + 1, pairs[i].q + 1, pairs[i].dist);
        return pairs[i].dist;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("resource/clustering1.txt"));

        int numPoints = in.nextInt();
        int numPairs = (numPoints * (numPoints - 1)) / 2;

        Edge[] edges = new Edge[numPairs];
        for (int i = 0; i < numPairs; i++) {
            edges[i] = new Edge(in.nextInt() - 1, in.nextInt() - 1, in.nextInt());
        }
        in.close();

        int maxSpacing = calculate(numPoints, edges, 4);
        System.out.println("maxSpacing: " + maxSpacing);
    }

}
