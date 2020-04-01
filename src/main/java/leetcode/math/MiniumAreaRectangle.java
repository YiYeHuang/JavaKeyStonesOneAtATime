package leetcode.math;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Mathematics;

/*
939. Minimum Area Rectangle
Medium

526

104

Add to List

Share
Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.
Accepted
 */

@Medium
@Mathematics
public class MiniumAreaRectangle {

  /*
   *
   *    .          (3, 1)x2y2
   *    .           .
   *    .           .
   *    .           .
   *  (1, 1)x1 y1   .
   *
   *
   *   (1, 3)      (3, 1)x2y2
   *    .           .
   *    .           .
   *    .           .
   *  (1, 1)x1 y1  (3, 1)
   *
   *
   * (1, 3) = x1 y2 -> p3
   * (3, 1) = x2 y1 -> p4
   */

  public class Node{
    int x;
    int y;
    Node(int a,int b) {x=a;y=b;}

    @Override
    public boolean equals(Object obj)
    { //System.out.println("inside");
      return (((Node) obj).x == this.x)&&(((Node) obj).y == this.y);
    }

    @Override
    public int hashCode(){
      return Objects.hash(x,y);
    }

  }

  public int minAreaRect(int[][] points) {
    int min=Integer.MAX_VALUE;
    Set<Node> nodes = new HashSet<>();
    for(int i=0;i<points.length;i++) {
      nodes.add(new Node(points[i][0],points[i][1]));
    }

    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        int x1 = points[i][0];
        int y1= points[i][1];
        int x2= points[j][0];
        int y2= points[j][1];
        Node pt3 = new Node(x1,y2);
        Node pt4 = new Node(x2,y1);

        if(nodes.contains(pt3) && nodes.contains(pt4))
        {   int area= Math.abs(x1-x2)*Math.abs(y1-y2);
          //System.out.println("Inside "+ x1 +" "+y1+" "+x2+" "+y2+" "+area);

          if(min>area && area!=0)
            min=area;
        }
      }

    }

    return (min==Integer.MAX_VALUE ? 0:min);
  }

}
