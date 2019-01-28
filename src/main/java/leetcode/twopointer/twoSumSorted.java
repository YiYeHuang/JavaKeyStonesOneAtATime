package leetcode.twopointer;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.TwoPointer;

/**
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 *
 */

@Amazon

@Easy
@ArrayTag
@TwoPointer
public class twoSumSorted
{
    /**
     * Two pointer, Tow sum's solution can no longer be used
     * is sum is smaller than target, means it needs a larger result, low move up
     * else, high move down
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */

    public static int[] twoSum(int[] numbers, int target)
    {
        int low = 0;
        int high = numbers.length -1;

        int[] result = new int[2];

        while (low < high) {
            if (numbers[low] + numbers[high] == target) {
                result[0] = low+1;
                result[1] = high+1;
                break;
            } else if (numbers[low] + numbers[high] < target) {
                low++;
            } else {
                high--;
            }
        }

        return  result;
    }
    
    public static void main(String[] args)
    {
        int[] test =
        { 1,3,4, 5, 8, 11, 23 };
        System.out.println(twoSum(test, 13));
    }
}
