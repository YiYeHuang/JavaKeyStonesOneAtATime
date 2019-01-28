package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. Subarray Sum Equals K
 *
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
 *
 * Example 1:
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * Note:
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

@Easy
@HashTableTag
public class SubarraySumEqualsK {
    /**
     * prefix sum,
     * run time O(n^2)
     */
    public static int subarraySum(int[] nums, int k) {
        if (nums.length == 0) return 0;

        int result = 0 ;
        for (int i = 0; i < nums.length; i++) {
            int prefixSum = nums[i];
            if (prefixSum == k) result ++;
            for (int j = i+1; j < nums.length; j++) {
                prefixSum += nums[j];
                if (prefixSum == k) result ++;
            }
        }

        return result;
    }

    /**
     * idea of two sum, since sum(i,j)=sum(0,j)-sum(0,i)
     *
     * we just need to go through the array, calculate the current sum and save number of all seen PreSum to a HashMap.
     * Time complexity O(n), Space complexity O(n).
     */
    public static int subarraySumHash(int[] nums, int k) {
        if (nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap<>();

        int result = 0;
        int prefixSum = 0;

        // if first prefixSum - k is 0, need to save this result first
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {

            prefixSum += nums[i];
            if (map.containsKey(prefixSum - k)) {
                result += map.get(prefixSum - k);
            }

            // value if the frequency, which is the number of result
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return result;
    }


    public static void main(String[] args) {
        int[] test = {1,2,3};
        subarraySum(test, 3);
    }
}
