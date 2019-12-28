package leetcode.dp;

import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/**

 53. Maximum Subarray

 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:
 */

@Easy
@DP
public class MaxSubArray
{

    /**
     * 极端的例子1: [-11, -23, -123, -13, -33, -1, -21] 当所有都是负数的时候最大值就是取 一位，-1
     * 我们便希望尽可能避免负数，取正数
     *
     * 子问题:   Max(A[], i, j) where A[i, j] sum is max
     * return的是sum, 问题便不需要用2D array求解
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     *
     * [0,0] max -2
     * [0,1] max 1    so we want to ditch the -2, and ditch the index 0, cuz it will always make the sum smaller
     * [1,2] max -2
     * [1,3] max 4    ditch -2 and ditch index 2
     *
     * so..............
     *
     *  Max(A[]) = Result[i - 1] > 0      Max( A [i])  or Max(A[i]) + Result[i - 1]
     *
     */
    public static int maxSubArray(int[] nums) {

        for(int i = 1; i < nums.length; i++) {
            if (nums[i - 1] < 0) {
                nums[i] = nums[i];
            } else {
                nums[i] += nums[i - 1];
            }
        }
        int max = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
        }

        return max;
    }

    public static void main(String[] args)
    {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray(nums));
    }
}
