package leetcode.dp;

import java.util.ArrayList;
import java.util.List;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.DFS;
import leetcode.tag.type.DP;

/*
300. Longest Increasing Subsequence
Medium

3224

74

Favorite

Share
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

@Medium
@DP
@BinarySearch
public class LongestIncreasingSebsequence {

    public static void lengthOfLIS1(int[] nums) {
        if(nums==null || nums.length==0){
            return;
        }
        int[] dp = new int[nums.length];
        int max = 1;
        for(int index=0; index<nums.length;index++){
            dp[index]=1;
            for(int dpIndex=0; dpIndex<index; dpIndex++){
                if(nums[dpIndex]<nums[index]){
                    dp[index]=Math.max(dp[index],dp[dpIndex]+1);
                    max=Math.max(dp[index],max);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = dp.length - 1; i >=0; i--) {
            if (dp[i] == max) {
                result.add(nums[i]);
                max--;
            }
        }

        System.out.println(result);
    }

    // if number[j] > number[i] then number[j] > all number[i] counter before i
    public static int lengthOfLIS(int[] nums) {
        if(nums==null || nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];

        for(int i=0; i<nums.length;i++) {
            dp[i] = 1;
            for(int j=0; j<i; j++){
                if (nums[i] > nums[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        return 1;
    }

    public static void main(String[] args)
    {
        int[] test = {10, 9, 2, 5, 3, 7, 101, 18, 19, 1, 3};
        lengthOfLIS(test);
    }
}
