package leetcode.bitwise;

import leetcode.tag.level.Easy;
import leetcode.tag.type.BitManipulation;

/*
832. Flipping an Image

Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.

To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].

To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].

Example 1:

Input: [[1,1,0],
        [1,0,1],
        [0,0,0]]

Output: [[1,0,0],
         [0,1,0],
         [1,1,1]]

Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
Example 2:

Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
Notes:

1 <= A.length = A[0].length <= 20
0 <= A[i][j] <= 1
 */

@Easy
@BitManipulation
public class FlippingImage {

    // two pointer, same flip, diff do nothing
    public int[][] flipAndInvertImage(int[][] A) {
        for (int i = 0; i < A.length; i++) {
            int m = 0;
            int n = A[i].length - 1;
            while (m<=n) {
                if ( A[i][m] == A[i][n]) {
                    if (m==n) {
                        A[i][m] = A[i][m]^1;
                    } else {
                        A[i][m] = A[i][m]^1;
                        A[i][n] = A[i][n]^1;
                    }
                }
                m++;
                n--;
            }
        }

        return A;
    }
}
