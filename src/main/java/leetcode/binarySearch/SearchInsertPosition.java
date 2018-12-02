package leetcode.binarySearch;

import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 *
 * You may assume no duplicates in the array.
 *
 * Example 1:
 *
 * Input: [1,3,5,6], 5
 * Output: 2
 * Example 2:
 *
 * Input: [1,3,5,6], 2
 * Output: 1
 * Example 3:
 *
 * Input: [1,3,5,6], 7
 * Output: 4
 * Example 4:
 *
 * Input: [1,3,5,6], 0
 * Output: 0
 */
@Google

@Easy
@BinarySearch
public class SearchInsertPosition {

    /**
     * Binary search basic modification question
     * go as far to sorta locate low, still need the last piece of non perfect logic.
     */
    public static int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        if (target < nums[low]) return 0;
        if (target > nums[high]) return nums.length;

        boolean find;
        while (low <= high) {
            int mid = low + (high - low)/2;

            if (target > nums[mid]) {
                low = mid + 1;
            } else if ( target < nums[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        if (nums[low] > target) return low;
        else return low + 1;
    }

    public int searchInsertClean(int[] A, int target) {
        int low = 0, high = A.length-1;
        while(low<=high){
            int mid = (low+high)/2;
            if(A[mid] == target) return mid;
            else if(A[mid] > target) high = mid-1;
            else low = mid+1;
        }
        return low;
    }

    public static void main(String[] args) {
        int[] test = {1, 3, 5,7 };
        System.out.print(searchInsert(test, 4));
    }
}
