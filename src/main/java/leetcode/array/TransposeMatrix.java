package leetcode.array;

/*
867. Transpose Matrix

Given a matrix A, return the transpose of A.

The transpose of a matrix is the matrix flipped over it's main diagonal, switching the row and column indices of the matrix.

Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: [[1,4,7],[2,5,8],[3,6,9]]
Example 2:

Input: [[1,2,3],[4,5,6]]
Output: [[1,4],[2,5],[3,6]]
 */

import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;

@Easy
@ArrayTag
public class TransposeMatrix {

  public int[][] transpose(int[][] A) {

    if (A.length == A[0].length) {
      for (int i = 0; i < A.length; i++) {
        for (int j = i; j < A[i].length; j++) {
          int temp = A[i][j];
          A[i][j] = A[j][i];
          A[j][i] = temp;
        }
      }
      return A;
    }

    /**
     * Input: [[1,2,3],[4,5,6]]
     * Output: [[1,4],[2,5],[3,6]]
     */
    int[][] B = new int[A[0].length][A.length];
    for (int i=0; i<A.length ; i++) {
      for (int j=0; j<A[0].length ; j++) {
        B[j][i] = A[i][j];
      }
    }
    return B;

  }
}
