package leetcode.twopointer;

import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
18. 4Sum

Given an array nums of n integers and an integer target, are there elements a, b, c,
and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

Note:

The solution set must not contain duplicate quadruplets.

Example:

Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.

A solution set is:
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
 */

@Medium
@TwoPointer
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();

        int len = nums.length;
        Arrays.sort(nums);
        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target) return res;

        for (int i = 0; i < len; i++) {
            int piece1 = nums[i];
            // avoid duplicate
            if (i > 0 && piece1 == nums[i - 1]) continue;
            // current piece too small
            if (piece1 + 3* max < target) continue;
            // current piece too large
            if (4 * piece1 > target) break;

            threeSum(nums, target - piece1, i + 1, len -1 , res, piece1);
        }

        return res;
    }

    public void threeSum(int[] nums, int target, int low, int high, List<List<Integer>> sumList, int res1) {

        if (low + 1 >= high) return;

        int max = nums[high];

        if (3 * nums[low] > target || 3 * max < target) return;

        for (int i = low; i < high - 1; i++) {
            int piece2 = nums[i];

            // avoid duplicate
            if (i > low && piece2 == nums[i - 1]) continue;
            // current piece too small
            if (piece2 + 2* max < target) continue;
            // current piece too large
            if (3 * piece2 > target) break;

            if (3 * piece2 == target) {
                // z is the boundary
                if (i + 1 < high && nums[i + 2] == piece2)
                    sumList.add(Arrays.asList(res1, piece2,piece2,piece2));
                break;
            }

            twoSum(nums, target - piece2, i+1, high, sumList, res1, piece2);
        }
    }


    public void twoSum(int[] nums, int target, int low, int high, List<List<Integer>> sumList, int res1, int res2) {

        if (low >= high) return;

        // end early
        if (2 * nums[low] > target || 2 * nums[high] < target) return;

        int i = low;
        int j = high;
        int sum;
        int checkDup;

        while( i < j) {
            sum = nums[i] + nums[j];
            if (sum == target) {
                sumList.add(Arrays.asList(res1, res2, nums[i], nums[j]));

                // cut duplicate
                checkDup = nums[i];
                while (i < j && checkDup == nums[i]) {
                    i++;
                }
                checkDup = nums[j];
                while (i < j && checkDup == nums[j]) {
                    j--;
                }
            }

            if (sum < target) i++;
            if (sum > target) j--;
        }
    }

}
