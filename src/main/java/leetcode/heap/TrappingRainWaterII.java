package leetcode.heap;

import leetcode.tag.level.Hard;
import leetcode.tag.type.BFS;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/*
407. Trapping Rain Water II

Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map,
compute the volume of water it is able to trap after raining.

Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]

Return 4.

The above image represents the elevation map [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]] before the rain.

After the rain, water is trapped between the blocks. The total volume of water trapped is 4.
 */

@Hard
@Heap
@BFS
public class TrappingRainWaterII {

    class Bar {
        int row;
        int col;
        int height;
        public Bar(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }

    public int trapRainWater(int[][] heightMap) {

        if (heightMap.length == 0) return 0;

        PriorityQueue<Bar> minHeap = new PriorityQueue<>((a, b)-> a.height - b.height);

        int h = heightMap.length;
        int w = heightMap[0].length;
        boolean[][] visited = new boolean[h][w];

        // Like Trapping rain water, in 2D all we care about is two side, in 3D, we care about broader.
        for (int i = 0; i < h; i++) {
            visited[i][0] = true;
            visited[i][w - 1] = true;
            minHeap.offer(new Bar(i, 0, heightMap[i][0]));
            minHeap.offer(new Bar(i, w - 1, heightMap[i][w - 1]));
        }
        for (int i = 0; i < w; i++) {
            visited[0][i] = true;
            visited[h - 1][i] = true;
            minHeap.offer(new Bar(0, i, heightMap[0][i]));
            minHeap.offer(new Bar(h - 1, i, heightMap[h - 1][i]));
        }

        // since using min heap, we can pop the shortest bar first, which is the lowest pointer where water can leak
        // when visited neighbour is lower, collect value.
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int result = 0;

        while(!minHeap.isEmpty()) {
            Bar bar = minHeap.poll();

            for (int[] dir : dirs) {
                int row = bar.row + dir[0];
                int col = bar.col + dir[1];

                if ((row >= 0 && row < h) && (col >= 0 && col < w) && !visited[row][col]) {
                    // not comparing the around unit, coz it does not matter if the surrounding is lower
                    // if the current spot is lower, this guarantee collect water, collect per each bar
                    visited[row][col] = true;
                    result += Math.max(0, bar.height - heightMap[row][col]);

                    // push a new bar in the min heap, we dont always push the current bar height
                    // since the current one is visited,
                    // - if current spot is higher, push current height as this blocks more water
                    // - if current spot is lower, push the original blocking bar height.
                    minHeap.offer(new Bar(row, col, Math.max(heightMap[row][col], bar.height)));
                }
            }
        }

        return result;
    }

}
