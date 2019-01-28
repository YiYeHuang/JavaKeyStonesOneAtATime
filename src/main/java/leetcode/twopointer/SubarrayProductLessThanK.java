package leetcode.twopointer;

import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

/**
 * 713. Subarray Product Less Than K
 *
 * Your are given an array of positive integers nums.
 *
 * Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.
 *
 * Example 1:
 * Input: nums = [10, 5, 2, 6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Note:
 *
 * 0 < nums.length <= 50000.
 * 0 < nums[i] < 1000.
 * 0 <= k < 10^6.
 */

@Medium
@TwoPointer
public class SubarrayProductLessThanK {

    /**
     * for s(i, j) i <= j, find all s(i, j) that is less than k
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums.length == 0) return 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            int prod = 1;
            while (j < nums.length) {
                prod *= nums[j];
                if (prod < k) result++;
                else break;
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test = {10, 5, 2, 6};
        numSubarrayProductLessThanK(test, 100);
    }
}
