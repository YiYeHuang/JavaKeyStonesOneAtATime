package leetcode.binarySearch;

import leetcode.tag.company.*;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.HashTableTag;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 */

@Facebook
@Amazon
@Google
@LinkedIn

@Easy
@BinarySearch
@HashTableTag
public class IntersectionOfTwoArrays {

    /**
     * @HashTable way to solve
     * Speed O(n)
     * Space 0(n)
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> lookup = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();
        for (int input : nums1) {
            lookup.add(input);
        }
        for (int input: nums2) {
            if (lookup.contains(input))
                intersection.add(input);
        }

        int[] result = new int[intersection.size()];
        int index = 0;
        for (int element : intersection) {
            result[index++] = element;
        }

        return  result;
    }

    public int[] intersectionTwoPointers(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }

    /**
     * @HashTable way to solve
     * Speed O(nlog(n))
     * Space 0(n)
     */
    public int[] intersectionBinarySearch(int[] nums1, int[] nums2) {
        Set<Integer> intersection = new HashSet<>();
        int size1 = nums1.length;
        int size2 = nums2.length;
        int[] target;
        int[] sort;
        if (size1 <= size2) {
            sort = nums2;
            Arrays.sort(sort);
            target = nums1;
        } else {
            sort = nums1;
            Arrays.sort(sort);
            target = nums2;
        }

        for (int input : target) {
            if (binarySearch(sort, input))
                intersection.add(input);
        }

        int[] result = new int[intersection.size()];
        int index = 0;
        for (int element : intersection) {
            result[index++] = element;
        }

        return  result;
    }

    public static boolean binarySearch(int[] a, int key)
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
                return true;
            }
        }
        return false;
    }
}
