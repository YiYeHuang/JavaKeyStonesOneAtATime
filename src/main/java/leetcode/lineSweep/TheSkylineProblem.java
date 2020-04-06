package leetcode.lineSweep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import leetcode.tag.level.Hard;
import leetcode.tag.type.LineSweep;

/*
218. The Skyline Problem

A city's skyline is the outer contour of the silhouette formed by all the buildings
in that city when viewed from a distance. Now suppose you are given the locations and
 height of all the buildings as shown on a cityscape photo (Figure A),
 write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings Skyline Contour
The geometric information of each building is represented by a triplet of integers
[Li, Ri, Hi],
where Li and Ri are the x coordinates of the left and right edge of the ith building,
respectively, and Hi is its height.
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as:
[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of
[ [x1,y1], [x2, y2], [x3, y3], ... ]
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment.
Note that the last key point, where the rightmost building ends,
is merely used to mark the termination of the skyline, and always has zero height.
Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure B should be represented as:
[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline.
For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable;
the three lines of height 5 should be merged into one in the final output as such:
[...[2 3], [4 5], [12 7], ...]
 */

@Hard
@LineSweep
public class TheSkylineProblem {

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

  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<Node> data = new ArrayList<>();

    // [2 9 10] ->
    // [2,0] in, [9,0] in
    // [2, 10] out, [9, 10] out
    for (int[] r : buildings) {
      data.add(new Node(r[0], 0, 1)); // x1, y1, bottom left, entry point
      data.add(new Node(r[1], 0, -1)); // x2, y1, bottom right, leaving point
      data.add(new Node(r[0], r[2], -1)); // x1, y2, up left, leaving point
      data.add(new Node(r[1], r[2], 1)); // x2, y2, up left, entry point
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
    int lastHight = -1;

    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < data.size(); i++) {
      Node p = data.get(i);
      // System.out.println(p);

      map.put(p.y, map.getOrDefault(p.y, 0) + p.val);

      if (i == data.size() - 1 || data.get(i + 1).x > p.x) {
        if (preX > -1) {
          if (lastHight == -1 || lastHight != preY) {
            System.out.println("RESULT " + preX + " " + preY);
            List<Integer> resultSet = new ArrayList<>();
            resultSet.add(preX);
            resultSet.add(preY);
            result.add(resultSet);

            lastHight = preY;
          }
        }

        preY = calcY(map);
        preX = p.x;

        if (preY == 0 && i == data.size() - 1 ) {
          System.out.println("RESULT get to 0 " + preX + " " + preY);
          List<Integer> resultSet = new ArrayList<>();
          resultSet.add(preX);
          resultSet.add(preY);
          result.add(resultSet);
        }
      }
    }



    return result;
  }

  private int calcY(TreeMap<Integer, Integer> map) {
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
    TheSkylineProblem rII = new TheSkylineProblem();

    int[][] test = { {2,9,10}, {3 ,7 ,15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8} };

    rII.getSkyline(test);
  }
}
