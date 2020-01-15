package leetcode.heap;

import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.HashTableTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
1296. Divide Array in Sets of K Consecutive Numbers

Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into sets of k consecutive numbers
Return True if its possible otherwise return False.



Example 1:

Input: nums = [1,2,3,3,4,4,5,6], k = 4
Output: true
Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
Example 2:

Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
Output: true
Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
Example 3:

Input: nums = [3,3,2,2,1,1], k = 3
Output: true
Example 4:

Input: nums = [1,2,3,4], k = 3
Output: false
Explanation: Each array should be divided in subarrays of size 3.


Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= k <= nums.length
 */

@ArrayTag
@HashTableTag
public class DivideArraySetsKConsecutiveNumbers {

    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length == 0 || nums.length % k != 0) return false;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Arrays.sort(nums);

        for (int num : nums) {
            if (map.get(num) > 0) {
                // from num to num + k - 1
                for (int i = 0; i < k; i++) {
                    if (!map.containsKey(num + i) || map.get(num + i) == 0) {
                        return false;
                    } else {
                        // update the count
                        map.put(num + i, map.get(num + i) - 1);
                    }
                }
            }
        }

        return true;
    }
}
