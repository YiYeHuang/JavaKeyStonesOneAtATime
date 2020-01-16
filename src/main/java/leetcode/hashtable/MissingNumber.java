package leetcode.hashtable;


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
    public static int missingNumber_math(int[] nums) {
        int len = nums.length;
        int sum = (0+len)*(len+1)/2;
        for(int i=0; i<len; i++)
            sum-=nums[i];
        return sum;
    }

    /**
     * The basic idea is to use XOR operation. We all know that
     *
     * a^b^b =a,
     *
     * which means two xor operations with the same number will eliminate the number and reveal the original number.
     * In this solution, I apply XOR operation to both the index and value of the array.
     * In a complete array with no missing numbers,
     * the index and value should be perfectly corresponding( nums[index] = index),
     * so in a missing array, what left finally is the missing number.
     */
    public int missingNumber_bit(int[] nums) {

        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }

    public int missingNumber_swap(int[] nums) {
        int lastIndex = nums.length;

        for(int i = 0; i < nums.length; ) {
            if (nums[i] == i)
                i++;
            else if (nums[i] < nums.length)
                nums[i] = nums[i] ^ nums[nums[i]] ^ (nums[nums[i]] = nums[i]);
            else
                lastIndex = i++;
        }

        return lastIndex;
    }

    public static void main(String[] args) {
        int[] test = {3,0,1};
        missingNumber(test);
    }
}
