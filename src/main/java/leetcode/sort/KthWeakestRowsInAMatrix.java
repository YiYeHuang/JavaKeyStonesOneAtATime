package leetcode.sort;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Heap;
import leetcode.tag.type.Sorting;

import java.util.Arrays;

/*
1341. The K Weakest Rows in a Matrix

Given a m * n matrix mat of ones (representing soldiers) and zeros (representing civilians),
return the indexes of the k weakest rows in the matrix ordered from the weakest to the strongest.

A row i is weaker than row j, if the number of soldiers in row i is less than the number of soldiers in row j,
or they have the same number of soldiers but i is less than j. Soldiers are always stand in the frontier of a row, that is,
always ones may appear first and then zeros.

Example 1:

Input: mat =
[[1,1,0,0,0],
 [1,1,1,1,0],
 [1,0,0,0,0],
 [1,1,0,0,0],
 [1,1,1,1,1]],
k = 3
Output: [2,0,3]
Explanation:
The number of soldiers for each row is:
row 0 -> 2
row 1 -> 4
row 2 -> 1
row 3 -> 2
row 4 -> 5
Rows ordered from the weakest to the strongest are [2,0,3,1,4]
Example 2:

Input: mat =
[[1,0,0,0],
 [1,1,1,1],
 [1,0,0,0],
 [1,0,0,0]],
k = 2
Output: [0,2]
Explanation:
The number of soldiers for each row is:
row 0 -> 1
row 1 -> 4
row 2 -> 1
row 3 -> 1
Rows ordered from the weakest to the strongest are [0,2,3,1]

Constraints:

m == mat.length
n == mat[i].length
2 <= n, m <= 100
1 <= k <= m
matrix[i][j] is either 0 or 1.
 */

@Easy
@Sorting
@Heap
public class KthWeakestRowsInAMatrix {

    public static int[] kWeakestRows(int[][] mat, int k) {
        // index, sum
        int[][] pair = new int[mat.length][2];

        for (int i = 0; i < mat.length; i++) {
            int sum = 0;
            for (int j = 0; j < mat[0].length; j++) {
                sum += mat[i][j];
            }
            pair[i][0] = i;
            pair[i][1] = sum;
        }

        Arrays.sort(pair,(a, b) -> b[1] == a[1]? a[0] - b[0]: a[1] - b[1]);

        int[] ret = new int[k];
        for (int i=0; i < k; i++)
        {
            ret[i] = pair[i][0];
        }
        return ret;
    }
}
