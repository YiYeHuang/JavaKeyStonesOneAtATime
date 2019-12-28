package leetcode.binarySearch;

import leetcode.tag.company.Amazon;
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

@Medium
@BinarySearch
public class FindMinimumRotatedSortedArray {

    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int low = 0;
        int high = nums.length-1;

        // If num[i-1] > num[i] < num[i+1], then num[i] is min
        // else
        //  mid < num[low] and mid < num[high], min on the left
        //  mid  > num[low] and mid > num[high], min on the right
        while(low < high){
            // deal with the edge cases:
            // if(low +1== high)
            //     return nums[low] > nums[high]? low : high;

            int mid = low + (high - low)/2;

            // mid is the low
            if(mid > 0 && nums[mid] < nums[mid-1] && nums[mid] < nums[mid+1]) {
                return nums[mid];
            }

            if (nums[low] <= nums[mid] && nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] test = {1,2,3};
        System.out.print(findMin(test));
    }
}