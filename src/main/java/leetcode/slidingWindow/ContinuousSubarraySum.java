package leetcode.slidingWindow;

import java.util.HashSet;
import java.util.Set;
import leetcode.tag.level.Medium;
import leetcode.tag.type.SlidingWindow;

/*
523. Continuous Subarray Sum
Medium

1177

1650

Add to List

Share
Given a list of non-negative numbers and a target integer k,
write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k,
that is, sums up to n*k where n is also an integer.



Example 1:

Input: [23, 2, 4, 6, 7],  k=6
Output: True
Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2:

Input: [23, 2, 6, 4, 7],  k=6
Output: True
Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.


Note:

The length of the array won't exceed 10,000.
You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
 */

@SlidingWindow
@Medium
public class ContinuousSubarraySum {

  public boolean checkSubarraySum(int[] nums, int k) {

    int n = nums.length;
    // the set is the sliding window

    int currentSum = 0, i = 0, j = 0, size = 0;

    while (i < n && j < n) {
      if (size >= 1) {

      }
    }
    return true;
  }

}
