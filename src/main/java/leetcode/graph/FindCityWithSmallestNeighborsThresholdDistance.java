package leetcode.graph;

import leetcode.tag.level.Easy;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Graph;

import java.util.Arrays;

/*
1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti]
represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold,
If there are multiple such cities, return the city with the greatest number.

Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.



Example 1:



Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2]
City 1 -> [City 0, City 2, City 3]
City 2 -> [City 0, City 1, City 3]
City 3 -> [City 1, City 2]
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
Example 2:



Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1]
City 1 -> [City 0, City 4]
City 2 -> [City 3, City 4]
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3]
The city 0 has 1 neighboring city at a distanceThreshold = 2.


Constraints:

2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
 */

@Medium
@Graph
public class FindCityWithSmallestNeighborsThresholdDistance {

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int INF = (int) 1e6; // INF = n * maxWeight = 100 * 10^4 = 10^6
        int[][] dist = new int[n][n]; // dist[i][j] is the minimum distance from i to j
        // init the graph with infinite
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
            // also the dia is 0
            dist[i][i] = 0;
        }
        // loading the edge
        for (int[] edge : edges) {
            int v1 = edge[0]; // vertex 1 index
            int v2 = edge[1]; // vertex 2 index
            int w = edge[2]; // cost
            dist[v1][v2] = w;
            dist[v2][v1] = w;
        }

        // Floyd Warshall's shortest path algorithm
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // go through all i, j, get update the i, k
                    // check if i to k, k to j is shorter than i j
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minReachable = n;
        int ansIndex = 0;
        for (int i = 0; i < n; i++) {
            int currentReachCount = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) {
                    currentReachCount++;
                }
            }
            // go through each row, keep updating the minReachable
            if (currentReachCount <= minReachable) {
                minReachable = currentReachCount;
                ansIndex = i;
            }
        }

        return ansIndex;
    }
}
