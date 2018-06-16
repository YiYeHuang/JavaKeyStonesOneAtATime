package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 * 
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 */
public class MajorityItem
{
    public static int majorityElement(int[] nums)
    {
        if (nums.length == 1)
        {
            return nums[0];
        }
        Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
        for (int num : nums)
        {
            if (!resultMap.containsKey(num))
            {
                resultMap.put(num, 1);
            }
            else
            {
                resultMap.put(num, resultMap.get(num) + 1);
                if (resultMap.get(num) > nums.length / 2)
                {
                    return num;
                }
            }
        }
        return -1;
    }

    // Moore voting algorithm
    public static int majorityElement3(int[] nums)
    {
        int count = 0, ret = 0;
        for (int num : nums)
        {
            if (count == 0)
                ret = num;
            if (num != ret)
                count--;
            else
                count++;
        }
        return ret;
    }

    public static void main(String[] args)
    {
        int[] test =
        { 3,2,3 };
        majorityElement3(test);
    }
}
