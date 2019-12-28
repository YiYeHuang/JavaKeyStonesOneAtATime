package leetcode.binarySearch;

import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */

@Easy
@HashTableTag
@BinarySearch
@Sorting
public class IntersectionOfTwoArraysII {

    /**
     * Binary search way
     *
     * Iterator the smaller list for intersection
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> result = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int size1 = nums1.length;
        int size2 = nums2.length;
        int[] smaller;
        int[] larger;
        if (size1 <= size2) {
            smaller = nums1;
            larger = nums2;
        } else {
            smaller = nums2;
            larger = nums1;
        }

        int index = -1;
        int start = 0;
        for (int input : smaller) {
            index = binarySearchIndex(larger, input);
            start++;
            if (index != -1)
                break;
        }

        if (index == -1)
            return new int[0];
        else {
            for (int i = start; i < smaller.length; i++) {
                if (larger[index] == smaller[i]) {
                    result.add(smaller[i]);
                } else {
                    break;
                }
            }
        }

        int[] resultArray = new int[result.size()];

        int i = 0;
        for (int item : result) {
            resultArray[i++] = item;
        }

        return resultArray;
    }

    public static int binarySearchIndex(int[] a, int key)
    {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2; // do this to avoid overflow
            if (key < a[mid])
            {
                hi = mid - 1;
            }
            else if (key > a[mid])
            {

                lo = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }

    /**
     * HashMap Solution
     */
    public int[] intersectHashTable(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < nums1.length; i++)
        {
            if(map.containsKey(nums1[i])) map.put(nums1[i], map.get(nums1[i])+1);
            else map.put(nums1[i], 1);
        }

        for(int i = 0; i < nums2.length; i++)
        {
            if(map.containsKey(nums2[i]) && map.get(nums2[i]) > 0)
            {
                result.add(nums2[i]);
                map.put(nums2[i], map.get(nums2[i])-1);
            }
        }

        int[] r = new int[result.size()];
        for(int i = 0; i < result.size(); i++)
        {
            r[i] = result.get(i);
        }

        return r;
    }

}
