package leetcode.dp;

import leetcode.tag.level.Medium;
import leetcode.tag.type.DP;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;
import java.util.Map;

import static javax.swing.UIManager.put;

/**
 * 523. Continuous Subarray Sum
 *
 * Given a list of non-negative numbers and a target integer k,
 * write a function to check if the array has a continuous subarray of size at least 2 that sums up to the multiple of k,
 * that is, sums up to n*k where n is also an integer.
 *
 * Example 1:
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * Example 2:
 * Input: [23, 2, 6, 4, 7],  k=6
 * Output: True
 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
 * Note:
 * The length of the array won't exceed 10,000.
 * You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

@Medium
@DP
@HashTableTag
public class ContinuousSubarraySum {
    /**
     * similar to lc 560
     *
     * idea of two sum, since sum(i,j)=sum(0,j)-sum(0,i)
     *
     * we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap.
     * Time complexity O(n), Space complexity O(n).
     */
    public static boolean checkSubarraySumOPT(int[] nums, int k) {

        // mapping the sum to the index
        //in order to get the sum of a portion of the array, you need to do something like preSum[j] - preSum[i].
        // When you want to get the sum from the first element, you need to do preSum[j] - preSum[0].
        // And (0, -1) is the condition when the sum is from the first element of the array.
        // Since the first element is taking the index 0, so before adding anything, we need to give 0 a index of -1.
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(){{put(0,-1);}};
        int prefixSum = 0;

        for (int i=0;i<nums.length;i++) {
            prefixSum += nums[i];

            if (k != 0) {
                // the condition have multiple of k
                prefixSum = prefixSum % k;
            }

            // get the previous index.
            Integer prev = map.get(prefixSum);

            // make sure the current index - previous index has at least 2 item. (1 item does not count)
            if (prev != null) {
                if (i - prev > 1) return true;
            } else {
                map.put(prefixSum, i);
            }
        }
        return false;
    }

    /**
     * before optimized
     */
    public static boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0)   return false;

        int[] preSum = new int[nums.length+1];

        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + nums[i-1];
        }

        for (int i = 0; i < nums.length; i++) {
            // for array more than size 2
            for (int j = i+2; j <= nums.length; j++) {
                if (k == 0) {
                    if (preSum[j] - preSum[i] == 0) {
                        return true;
                    }
                } else if ((preSum[j] - preSum[i]) % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        int[] test = {23, 2, 4, 6, 7};
        checkSubarraySum(test, 6);
    }

}
