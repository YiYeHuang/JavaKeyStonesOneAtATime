package algorithm.greedy.unionFind;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class KClusteringHuge {

    public static class UnionByRankWithPathCompression {
        private int[] parent;  // parent[i] = parent of i
        private int[] rank;    // rank[i] = rank of i
        private int size;      // number of groups

        public UnionByRankWithPathCompression(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            size = n;
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot == jRoot) {
                return;
            }
            if (rank[iRoot] < rank[jRoot]) {
                int tmp = iRoot;
                iRoot = jRoot;
                jRoot = tmp;
            } else if (rank[iRoot] == rank[jRoot]) {
                rank[iRoot]++;
            }
            parent[jRoot] = iRoot;
            size--;
        }

        public int find(int i) {
            int root = i;
            while (root != parent[root]) {
                root = parent[root];
            }
            while (parent[i] != root) {
                int p = parent[i];
                parent[i] = root;
                i = p;
            }
            return root;
        }

        public int size() {
            return size;
        }
    }


    /**
     * Find largest value of k such that there is a k-clustering with spacing at least 3.
     */
    public static int findMaxK(Map<Integer, Integer> node2Id, int[] neighborMask) {
        UnionByRankWithPathCompression uf = new UnionByRankWithPathCompression(node2Id.size());
        for (Map.Entry<Integer, Integer> e: node2Id.entrySet()) {
            int node = e.getKey();
            int idx = e.getValue();
            for (int neighborIdx: findNeighbors(node, node2Id, neighborMask)) {
                uf.union(idx, neighborIdx);
            }
        }
        return uf.size();
    }

    /**
     * Find neighbors of node.
     */
    private static List<Integer> findNeighbors(int node, Map<Integer, Integer> node2Id, int[] neighborMask) {
        List<Integer> neighbors = new LinkedList<>();
        for (int mask: neighborMask) {
            Integer candidate = node2Id.get(node ^ mask);
            if (candidate != null) {
                neighbors.add(candidate);
            }
        }
        return neighbors;
    }

    /**
     * Compute masks to be used for finding neighbors of a given node.
     * nbits: number of bits to represent a node.
     */
    private static int[] getNeighborMask(int nbits) {
        // Precompute masks to find neighbors of given node u whose Hamming distance to u is 1 or 2
        int numMasksWithOne1s = nbits;
        int numMaksWithTwo1s = (nbits * (nbits - 1)) / 2;
        int[] masks = new int[numMasksWithOne1s + numMaksWithTwo1s];
        for (int k = 0, i = 0; i < nbits; i++) {
            for (int j = i; j < nbits; j++) {
                masks[k++] = (1 << i) | (1 << j);
            }
        }
        return masks;
    }

    /**
     * Read input data fro file then call function findMaxK.
     */
    public static void main(String[] args) throws IOException {

        Scanner s = new Scanner(new File("resource/clustering_big.txt"));
        int n = s.nextInt();
        int nbits = s.nextInt();
        Map<Integer, Integer> node2Id = new HashMap<>(n);
        s.nextLine();
        StringBuilder bitSB = new StringBuilder();
        int nodeId = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < nbits; j++) {
                bitSB.append(s.nextInt());
            }
            Integer node = Integer.parseInt(bitSB.toString(), 2);
            bitSB.setLength(0);
            if (!node2Id.containsKey(node)) {
                node2Id.put(node, nodeId++);
            }
        }
        s.close();
        //+ DEBUG
        System.out.println("# of points:          " + n);
        System.out.println("# of Distinct points: " + node2Id.size());
        //- DEBUG
        int[] neighborMask = getNeighborMask(nbits);
        int maxK = findMaxK(node2Id, neighborMask);
        System.out.println("Larget value of k: " + maxK);
    }

}
