package leetcode.binarySearch;


/*
Find Peak Element

Solution
A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5
Explanation: Your function can return either index number 1 where the peak element is 2,
             or index number 5 where the peak element is 6.
 */
public class FindPeakElement {

  public int findPeakElement(int[] nums) {
    int low = 0;
    int high = nums.length-1;

    // If num[i-1] < num[i] > num[i+1], then num[i] is peak
    // If num[i-1] < num[i] < num[i+1], then num[i+1...n-1] must contains a peak
    // If num[i-1] > num[i] > num[i+1], then num[0...i-1] must contains a peak
    // If num[i-1] > num[i] < num[i+1], then both sides have peak(n is num.length)
    while(low < high){
      // deal with the edge cases:
      if(low +1== high)
        return nums[low] > nums[high]? low : high;

      int mid = low + (high - low)/2;

      // mid is the peak
      if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
        return mid;
      }
      else if(nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1]) {
        // peak is at the right of the mid, since mid is not peak, low can be mid + 1
        low = mid+1;
      }
      else {
        // peak is at the left of the mid, since mid is not peak, high can be mid -1
        high = mid-1;
      }
    }
    return low;

  }
}
