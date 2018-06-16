package leetcode.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. Your
 * function should return true if any value appears at least twice in the array,
 * and it should return false if every element is distinct.
 * 
 */
public class FindDeplicateInArray
{
    /**
     * Space usage as O(n), speed O(n)
     */
    public boolean containsDuplicate(int[] nums)
    {
        if (nums.length == 0)
            return false;
        Set<Integer> dic = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++)
        {
            if (!dic.contains(nums[i]))
            {
                dic.add(nums[i]);
            } else
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Algorithm
     * 
     * This approach employs sorting algorithm. Since comparison sorting algorithm
     * like heapsort is known to provide O(n \log n)O(nlogn) worst-case performance,
     * sorting is often a good preprocessing step. After sorting, we can sweep the
     * sorted array to find if there are any two consecutive duplicate elements.
     */
    public boolean containsDuplicate_sort(int[] nums)
    {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i)
        {
            if (nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    /**
     * Given an array of integers and an integer k, find out whether there are two
     * distinct indices i and j in the array such that nums[i] = nums[j] and the
     * absolute difference between i and j is at most k.
     */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
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


    public boolean containsNearbyDuplicate_sort(int[] nums, int k) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; ++i)
        {
            int temp = 1;
            while (temp <= k)
            {
                if (nums[i] == nums[i+temp])
                {
                    return true;
                }
            }
        }
        return false;
    } 
}
