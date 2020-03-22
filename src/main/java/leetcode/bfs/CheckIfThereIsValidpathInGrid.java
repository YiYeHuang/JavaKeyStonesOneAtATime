package leetcode.bfs;

import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;

import java.util.LinkedList;
import java.util.Queue;

/*
1391. Check if There is a Valid Path in a Grid

Given a m x n grid. Each cell of the grid represents a street. The street of grid[i][j] can be:
1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0,0). A valid path in the grid is a path which starts
from the upper left cell (0,0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.
Notice that you are not allowed to change any street.
Return true if there is a valid path in the grid or false otherwise.

Example 1:

Input: grid = [[2,4,3],[6,5,2]]
Output: true
Explanation: As shown you can start at cell (0, 0) and visit all the cells of the grid to reach (m - 1, n - 1).
Example 2:


Input: grid = [[1,2,1],[1,2,1]]
Output: false
Explanation: As shown you the street at cell (0, 0) is not connected with any street of any
other cell and you will get stuck at cell (0, 0)
Example 3:

Input: grid = [[1,1,2]]
Output: false
Explanation: You will get stuck at cell (0, 1) and you cannot reach cell (0, 2).
Example 4:

Input: grid = [[1,1,1,1,1,1,3]]
Output: true
Example 5:

Input: grid = [[2],[2],[2],[2],[2],[2],[6]]
Output: true

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
1 <= grid[i][j] <= 6
 */

@BFS
@Medium
public class CheckIfThereIsValidpathInGrid {

    int[][][] dirs = {
            {{0, -1}, {0, 1}},
            {{-1, 0}, {1, 0}},
            {{0, -1}, {1, 0}},
            {{0, 1}, {1, 0}},
            {{0, -1}, {-1, 0}},
            {{0, 1}, {-1, 0}}
    };
    /**
     *
     * 1 goes column -1 +1
     * 2 goes row -1 +1
     * 3 goes col -1 row +1
     * 4 goes col +1 row +1
     * 5 goes col -1 row -1
     * 6 goes col +1 row -1
     */
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int rodeType = grid[x][y] - 1;
            // go through each 2 direction of the
            for (int[] dir : dirs[rodeType]) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                // boarder check
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }
                // check is visited
                if (visited[nextX][nextY]) {
                    continue;
                }
                // reverse
                boolean portConnect = false; //go to the next cell and come back to orign to see if port directions are same
                int neighbourRoadType = grid[nextX][nextY] - 1;
                for (int[] reverseDir : dirs[neighbourRoadType]) {
                    if (nextX + reverseDir[0] == x && nextY + reverseDir[1] == y) {
                        portConnect = true;
                    }
                    if (portConnect) {
                        visited[nextX][nextY] = true;
                        q.add(new int[]{nextX, nextY});
                    }
                }
            }
        }

        // if road is invalid, the last step cannot be visited
        return visited[m - 1][n -1];
    }

    public boolean hasValidPath2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int num = grid[x][y] - 1;
            for (int[] dir : dirs[num]) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if (visited[nx][ny]) continue;
                boolean portConnect = false; //go to the next cell and come back to orign to see if port directions are same
                for (int[] backDir : dirs[grid[nx][ny] - 1]) {
                    if (nx + backDir[0] == x && ny + backDir[1] == y) portConnect = true;
                    if (portConnect) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return visited[m - 1][n - 1];
    }
}
