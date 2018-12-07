package leetcode.hashtable;

import leetcode.tag.company.*;
import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.Hash;

import java.util.HashMap;
import java.util.Map;

/**
 Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:

 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].
 */

@Amazon
@Facebook
@Microsoft
@Bloomberg
@LinkedIn
@Apple
@Easy
public class TwoSum
{
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    @ArrayTag
    @Hash
    public static int[] twoSum(int[] numbers, int target)
    {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < numbers.length; i++)
        {
            if (map.containsKey(target - numbers[i]))
            {
                result[1] = i + 1;
                result[0] = map.get(target - numbers[i]);
                return result;
            }
            map.put(numbers[i], i + 1);
        }
        return result;
    }

    public int[] twoSumSlow(int[] nums, int target)
    {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                if ((nums[i] + nums[j]) == target)
                {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }
}
