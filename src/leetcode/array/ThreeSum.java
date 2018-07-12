package leetcode.array;

import Tag.company.Amazon;
import Tag.company.Bloomberg;
import Tag.company.Facebook;
import Tag.company.Microsoft;
import Tag.level.Medium;
import Tag.type.Array;
import Tag.type.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * Note:
 *
 * The solution set must not contain duplicate triplets.
 *
 * Example:
 *
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 *
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */

@Facebook
@Microsoft
@Amazon
@Bloomberg
@Medium
public class ThreeSum {

    /**
     * sorted is the easiest way, use two pointer to go through like two sum
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     */
    @Array
    @TwoPointer
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

                        //duplicate check
                        while (lo < hi && nums[lo] == nums[lo+1]){
                            lo++;
                        }
                        //duplicate check
                        while (lo < hi && nums[hi] == nums[hi-1]) {
                            hi--;
                        }
                        lo++;
                        hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        // since sorted, need a bigger number
                        lo++;
                    } else {
                        // need a smaller number
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test =
                {-2,0,1,1,2};
        threeSum(test);
    }
}
