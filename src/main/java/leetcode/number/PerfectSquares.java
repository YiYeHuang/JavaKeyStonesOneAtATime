package leetcode.number;

import leetcode.tag.company.Google;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DP;
import leetcode.tag.type.Mathematics;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 */

@Google
public class PerfectSquares {

    @DP
    @BFS
    @Mathematics
    public int numSquares() {
        return numSquares();
    }


    public static void main(String[] args) {
        System.out.println(squareAble(1));
    }

    private static boolean squareAble(int input) {
        if ((Math.sqrt(input) - Math.round(Math.sqrt(input))) == 0 ) {
            return true;
        } else {
            return false;
        }
    }
}
