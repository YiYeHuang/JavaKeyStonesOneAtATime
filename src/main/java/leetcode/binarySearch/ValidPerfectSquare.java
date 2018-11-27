package leetcode.binarySearch;

import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 *Given a positive integer num, write a function which returns True if num is a perfect square else False.
 *
 * Note: Do not use any built-in library function such as sqrt.
 *
 * Example 1:
 *
 * Input: 16
 * Output: true
 * Example 2:
 *
 * Input: 14
 * Output: false
 */

@LinkedIn

@Easy
@BinarySearch
public class ValidPerfectSquare {

    /**
     * similiar to the square question, just need to really make sure to add mod condition
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        int low = 1;
        int high = num;

        while (low <= high) {
            int mid = (low + high)/2;

            if (mid == num/mid && num%mid == 0)
                return true;

            if (mid < num/mid) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return false;
    }
}
