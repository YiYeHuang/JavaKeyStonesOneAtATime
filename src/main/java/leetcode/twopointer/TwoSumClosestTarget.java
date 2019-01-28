package leetcode.twopointer;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Sorting;
import leetcode.tag.type.TwoPointer;

import java.util.Arrays;

/**
 * [LintCode] 533 Two Sum - Closest to target
 * Description
 * Given an array nums of n integers, find two integers in nums such that the sum is closest to a given number, target.
 *
 * Return the difference between the sum of the two integers and the target.
 *
 *
 *
 * Example
 * Given array nums = [-1, 2, 1, -4], and target = 4.
 *
 * The minimum difference is 1. (4 - (2 + 1) = 1).
 *
 *
 *
 * Challenge
 * Do it in O(nlogn) time complexity.
 */

@Easy
@TwoPointer
@Sorting
public class TwoSumClosestTarget {

    /**
     * find two integers: means array order can be modified, so, sorting is on the table, so is binary search
     */
    public int twoSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int low = 0;
        int high = nums.length - 1;

        int min = Integer.MAX_VALUE;

        while (low < high) {
            if (nums[low] + nums[high] < target) {
                // need a bigger one
                min = Math.min(min, target - nums[low] - nums[high]);
                low++;
            } else {
                min = Math.min(min, nums[low] + nums[high] - target);
                high--;
            }
        }

        return min;
    }
}
