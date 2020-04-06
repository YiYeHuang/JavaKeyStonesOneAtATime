package leetcode.lineSweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Heap;
import leetcode.tag.type.LineSweep;

/*
850. Rectangle Area II

We are given a list of (axis-aligned) rectangles.  Each rectangle[i] = [x1, y1, x2, y2] ,
where (x1, y1) are the coordinates of the bottom-left corner,
and (x2, y2) are the coordinates of the top-right corner of the ith rectangle.

Find the total area covered by all rectangles in the plane.
Since the answer may be too large, return it modulo 10^9 + 7.



Example 1:

Input: [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
Output: 6
Explanation: As illustrated in the picture.
Example 2:

Input: [[0,0,1000000000,1000000000]]
Output: 49
Explanation: The answer is 10^18 modulo (10^9 + 7), which is (10^9)^2 = (-7)^2 = 49.
Note:

1 <= rectangles.length <= 200
rectanges[i].length = 4
0 <= rectangles[i][j] <= 10^9
The total area covered by all rectangles will never exceed 2^63 - 1 and thus will fit in a 64-bit signed integer.
 */

@Hard
@LineSweep
@Heap
public class RectangleAreaII {

    class Node {
      int x, y, val;
      Node(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
      }

      public String toString() {
        return x + " " + y + " " + val;
      }
    }

    public int rectangleArea(int[][] rectangles) {
      int M = 1000000007;
      List<Node> data = new ArrayList<>();

      for (int[] r : rectangles) {
        data.add(new Node(r[0], r[1], 1)); // x1, y1, bottom left, entry point
        data.add(new Node(r[0], r[3], -1)); // x1, y2, up left, leaving point
        data.add(new Node(r[2], r[1], -1)); // x2, y1, bottom right, leaving point
        data.add(new Node(r[2], r[3], 1));  // x2, y2, up left, entry point
      }

      Collections.sort(data, (a, b) -> {
        if (a.x == b.x) {
          return b.y - a.y;
        }
        return a.x - b.x;
      });

      TreeMap<Integer, Integer> map = new TreeMap<>();
      int preX = -1;
      int preY = -1;
      int result = 0;

      for (int i = 0; i < data.size(); i++) {
        Node p = data.get(i);
        System.out.println(p);

        map.put(p.y, map.getOrDefault(p.y, 0) + p.val);

        if (i == data.size() - 1 || data.get(i + 1).x > p.x) {
          if (preX > -1) {
            result += ((long)preY * (p.x - preX)) % M;
            result %= M;
          }
          preY = getY(map);
          preX = p.x;
        }
      }
      return result;
    }

    private int getY(TreeMap<Integer, Integer> map) {
      int result = 0;
      int previousCutPoint = -1;
      int count = 0;

      for (Map.Entry<Integer, Integer> e : map.entrySet()) {
        if (previousCutPoint >= 0 && count > 0) {
          result += e.getKey() - previousCutPoint;
        }
        count += e.getValue();
        previousCutPoint = e.getKey();
      }
      return result;
    }

  public static void main(String[] args) {
    RectangleAreaII rII = new RectangleAreaII();

    int[][] test = {{0,0,2,2},{1,0,2,3},{1,0,3,1}};

    rII.rectangleArea(test);
  }

}
