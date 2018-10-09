package leetcode.dp;

import leetcode.tag.company.LinkedIn;
import leetcode.tag.type.Array;
import leetcode.tag.type.DP;

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

    /**
     * Time Complexity: O(n^2)
     * Space Complexity: O(n^2)
     *
     * time exceed
     */
    @Array
    @DP
    public static int maxProduct(int[] nums) {
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int[] dp = new int[nums.length];
            dp[i-1] = nums[i-1];
            for (int j = i; j < nums.length; j++) {
                dp[j] = dp[j-1]== 0 ? nums[j] : dp[j-1] * nums[j];
                if (dp[j] >= currentMax) {
                    currentMax = dp[j];
                }
                if (nums[j] >= currentMax) {
                    currentMax = nums[j];
                }
            }
        }

        return currentMax;
    }

    /**
     * The process is about about odd negative numbers or even negative numbers,
     * if it's odd, either the left end one or the right end one should be counted,
     * so it will be revealed by scanning from left and from right in 2 passes.
     *
     * 0 is a reset point
     */
    @Array
    public static int maxProductDoubleScan(int[] nums) {
        int max = Integer.MIN_VALUE, product = 1;
        int len = nums.length;

        for(int i = 0; i < len; i++) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        product = 1;

        for(int i = len - 1; i >= 0; i--) {
            max = Math.max(product *= nums[i], max);
            if (nums[i] == 0) product = 1;
        }

        return max;
    }

    /**
     *两个dp数组，其中f[i]表示子数组[0, i]范围内的最大子数组乘积，g[i]表示子数组[0, i]范围内的最小子数组乘积，
     * 初始化时f[0]和g[0]都初始化为nums[0]，
     * 其余都初始化为0。那么从数组的第二个数字开始遍历，那么此时的最大值和最小值只会在这三个数字之间产生，
     * 即
     * f[i-1]*nums[i]，
     * g[i-1]*nums[i]，
     * nums[i]。
     *
     * 所以我们用三者中的最大值来更新f[i]，用最小值来更新g[i]，然后用f[i]来更新结果res即可
     *
     * Besides keeping track of the largest product, we also need to keep track of the smallest product.
     * Why? The smallest product,
     * which is the largest in the negative sense could become the maximum when being multiplied by a negative number.
     */
    @Array
    public static int maxProductMaxMin(int[] A) {
        if (A.length == 0) {
            return 0;
        }

        int maxherepre = A[0];
        int minherepre = A[0];
        int maxsofar = A[0];
        int maxhere, minhere;

        for (int i = 1; i < A.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
            minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        return maxsofar;
    }



    public static void main(String[] args) {
        int[] test = {3,-2,-3, -1, 100, -3};
        System.out.println(maxProductMaxMin(test));
    }

}
