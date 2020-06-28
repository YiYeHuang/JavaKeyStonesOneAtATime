package leetcode.math;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Mathematics;


/*
836. Rectangle Overlap

A rectangle is represented as a list [x1, y1, x2, y2],
where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) are the coordinates of its top-right corner.

Two rectangles overlap if the area of their intersection is positive.
To be clear, two rectangles that only touch at the corner or edges do not overlap.

Given two (axis-aligned) rectangles, return whether they overlap.

Example 1:

Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
Output: true
Example 2:

Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
Output: false
 */


@Mathematics
@Easy
public class RectangleOverlap {


  /**
   * Given 2 segment (left1, right1), (left2, right2), how can we check whether they overlap?
   * If these two intervals overlap, it should exist an number x,
   *
   * left1 < x < right1 && left2 < x < right2
   * left1 < x < right2 && left2 < x < right1
   *
   * *** left1 < right2 && left2 < right1 ***
   *
   * transfer
   *
   * *** top1 < bottom2  ***
   *
   */
  public boolean isRectangleOverlap(int[] rec1, int[] rec2) {

    int l1 = rec1[0];
    int b1 = rec1[1];
    int r1 = rec1[2];
    int t1 = rec1[3];

    int l2 = rec2[0];
    int b2 = rec2[1];
    int r2 = rec2[2];
    int t2 = rec2[3];

    // similar to meeting schedule
    // the minimum right point is less then max
    //
    //   l1          r1
    //     -----------
    //           l2          r2
    //            ------------
    int width = Math.min(r1,r2) - Math.max(l1,l2);
    int height = Math.min(t1,t2) - Math.max(b1,b2);
    return width > 0 && height > 0;
  }

}
