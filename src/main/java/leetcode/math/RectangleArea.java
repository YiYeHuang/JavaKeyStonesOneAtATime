package leetcode.math;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Mathematics;

/*
223. Rectangle Area

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area

Example:

Input: A = -3, B = 0, C = 3, D = 4, E = 0, F = -1, G = 9, H = 2
Output: 45
Note:

Assume that the total area is never beyond the maximum possible value of int.
 */

@Mathematics
@Medium
public class RectangleArea {

  public int computeAreaJoin(int A, int B, int C, int D, int E, int F, int G, int H) {
    int hTop = Math.min(D, H);
    int hBot = Math.max(B, F);
    int wTop = Math.min(C, G);
    int wBot = Math.max(A, E);

    /*
     * new point will be (wTop, hBot) (wBot, hTop);
     */
    if (hTop < hBot || wTop < wBot) {
      return 0;
    } else {
      return (hTop - hBot) * (wTop - wBot);
    }
  }

  public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    return (C-A)*(D-B) + (G-E)*(H-F) - computeAreaJoin(A,B,C,D,E,F,G,H);
  }
}
