package leetcode.twopointer;

import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 *
 * Note:
 *
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 *
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */

@Easy
@BinarySearch
public class TwoSumII_arrayIsSorted {

    /**
     * Instead of finding mid, use two pointer to get to the answer,
     * since the list is sorted, it will eventually find it
     */
    public static int[] twoSum(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        int[] result = new int[2];

        while (low < high) {

            if (numbers[low] + numbers[high] < target) {
                low++;
            } else if (numbers[low] + numbers[high] > target) {
                high--;
            } else {
                return new int[]{low + 1, high + 1};
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test = {2,7,11,15};
        twoSum(test, 9);
    }
}
