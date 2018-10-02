package leetcode.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. Your
 * function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 */
public class ContainsDuplicate
{
    public boolean containsDuplicate(int[] nums)
    {
        Set<Integer> duplicateCheck = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++)
        {
            if (duplicateCheck.contains(nums[i]))
            {
                return true;
            } else
            {
                duplicateCheck.add(nums[i]);
            }
        }
        return false;
    }

    /**
     * Given an array of integers and an integer k, find out whether there are
     * two distinct indices i and j in the array such that nums[i] = nums[j] and
     * the absolute difference between i and j is at most k.
     */
    public boolean containsNearbyDuplicateII(int[] nums, int k)
    {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++)
        {
            if (map.containsKey(nums[i]))
            {
                if (i - map.get(nums[i]) <= k)
                {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
