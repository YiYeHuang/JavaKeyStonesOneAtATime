package leetcode.binarySearch;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;

/**
 153. Find Minimum in Rotated Sorted Array
 
 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

 (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

 Find the minimum element.

 You may assume no duplicate exists in the array.

 Example 1:

 Input: [3,4,5,1,2]
 Output: 1

 Example 2:

 Input: [4,5,6,7,0,1,2]
 Output: 0
 */

@Amazon
@Facebook
@Bloomberg
@Microsoft

@Medium
@BinarySearch
public class FindMinimumRotatedSortedArray {

    /**
     * since the original list is sorted, if rotated,
     *
     * if num[mid] > start and > end, then it is at the bigger part, low to mid + 1.
     *
     * if not it is at the smaller part low to mid -1
     *
     * if cannot find, rotate at 0, no rotate
     *
     *
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int low = 0;
        int high = nums.length -1;

        while (low < high) {
            int mid = low + (high - low)/2;

            // not mid + 1 to avoid out of bounce
            if (mid !=0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }

            // high change that low is gonna tie to mid, do mid >= low
            if (nums[mid] >= nums[low] && nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid -1;
            }
        }

        return nums[low];
    }

    public static void main(String[] args) {
        int[] test = {1,2,3};
        System.out.print(findMin(test));
    }
}