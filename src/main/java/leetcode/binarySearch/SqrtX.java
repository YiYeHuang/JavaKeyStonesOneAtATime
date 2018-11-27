package leetcode.binarySearch;

import leetcode.tag.company.*;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.LinkedListTag;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 *
 * Example 1:
 *
 * Input: 4
 * Output: 2
 * Example 2:
 *
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 *              the decimal part is truncated, 2 is returned.
 */
@Google
@Apple
@LinkedIn
@Bloomberg
@Microsoft

@Easy
@BinarySearch
public class SqrtX {

    /**
     * Oppisite of Power, keep cutting the result to half
     */
    public static int mySqrt(int x) {
        if (x == 1) return 1;
        int low = 1;
        int high = x;

        while (low <= high) {
            int mid = (low + high)/2;

            if (mid == x/mid)
                return mid;

            if (mid < x/mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        System.out.print(mySqrt(80));
    }
}
