package leetcode.array;

import Tag.company.LinkedIn;
import Tag.type.Array;
import Tag.type.DP;

/**
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
 *
 * Example 1:
 *
 * Input: [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product 6.
 * Example 2:
 *
 * Input: [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */

@LinkedIn
public class MaximumProductSubarray {

    @Array
    @DP
    public static int maxProduct(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int currentMax = nums[0];

        int currentProduct = nums[0];

        // TODO
        for (int i = 1; i < nums.length; i++) {

        }

        return currentMax;
    }

}
