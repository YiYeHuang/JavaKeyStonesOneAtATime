package leetcode.binarySearch;

import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;

/*
33 Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */

@BinarySearch
@Medium
public class SearchRotatedSortedArray {

  public int search(int[] nums, int target) {
    int low = 0;
    int high = nums.length - 1;
    while (low <= high) {

      int mid = low + (high - low) / 2;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] > target) {
        // by orginal case, mid too large, we should do high = mid -1.
        // but since the list is altered, need to relocate low
        //  ---- not normal case ----
        //  - mid is bigger than target,
        //  - mid might be actually on the left side of target, the left half is monotonic
        //  - make sure low is less than mid and low is larger than target,
        //  - target is on the right side
        //  - sikp the left side, low = mid + 1

        // nums[low] <= nums[mid] ===> left is monotonic;
        // nums[low] > target     ===> mid is larger than target but actually at left of target
        if (nums[low] <= nums[mid] && nums[low] > target) {
          // not regular case
          low = mid + 1;
        } else {
          // normal case
          high = mid - 1;
        }
      } else if (nums[mid] < target){
        // the right half is monotonic,
        // same reason, mid actuall on the righ side of target,
        // need to kip the current mid and adjust the high to mid -1
        if (nums[high] >= nums[mid] && nums[high] < target) {
          // nre regular case
          high = mid - 1;
        } else {
          // normal case
          low = mid + 1;
        }
      }
    }
    return -1;
  }
}
