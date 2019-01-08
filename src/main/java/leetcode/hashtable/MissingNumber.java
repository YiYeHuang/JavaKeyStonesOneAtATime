package leetcode.hashtable;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.BitManipulation;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Mathematics;

/**
 * 268. Missing Number
 *
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 *
 * Example 1:
 *
 * Input: [3,0,1]
 * Output: 2
 * Example 2:
 *
 * Input: [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */

@Amazon

@Easy
@ArrayTag
@Mathematics
@HashTableTag
@BitManipulation
public class MissingNumber {

    public static int missingNumber(int[] nums) {
        int[] result = new int[nums.length + 1];

        for (int i = 0; i < result.length; i ++) {
            result[i] = -1;
        }

        for (int i = 0; i < nums.length; i ++) {
            result[nums[i]] = nums[i];
        }

        int target = -1;

        for (int i = 0; i < result.length; i ++) {
            if (nums[i]==-1) {
                target =  i;
                break;
            }
        }

        return target;
    }

    /**
     * Since 0 + ...... + n the total is fix, it is easy to deduct everything and find out the result
     */
    public static int missingNumberMath(int[] nums) {
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }

    public static void main(String[] args) {
        int[] test = {3,0,1};
        missingNumber(test);
    }
}
