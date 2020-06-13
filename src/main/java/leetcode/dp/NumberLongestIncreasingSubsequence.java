package leetcode.dp;

import leetcode.tag.level.Medium;
import leetcode.tag.type.DP;

/*
673. Number of Longest Increasing Subsequence
Medium

1232

74

Add to List

Share
Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1,
and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.
 */

@DP
@Medium
public class NumberLongestIncreasingSubsequence {

  public static int findLengthOfLCIS(int[] nums) {
    int n = nums.length;
    int result = 0;
    int max_len = 0;

    int[] dp =  new int[n];
    int[] cnt = new int[n];

    for(int i = 0; i<n; i++){
      dp[i] = cnt[i] = 1;
      for(int j = 0; j <i ; j++){
        if(nums[i] > nums[j]){

          if(dp[i] == dp[j] + 1) {
            cnt[i] += cnt[j];
          }

          if(dp[i] < dp[j] + 1){
            dp[i] = dp[j] + 1;
            cnt[i] = cnt[j];
          }

        }
      }

      if(max_len == dp[i]) {
        result += cnt[i];
      }
      if(max_len < dp[i]) {
        max_len = dp[i];
        result = cnt[i];
      }
    }
    return result;
  }

  public static void main(String[] args) {
    int[] test = {1,3,5,4,7};
    findLengthOfLCIS(test);
  }
}
