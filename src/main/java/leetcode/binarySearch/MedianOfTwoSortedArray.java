package leetcode.binarySearch;

import leetcode.tag.level.Hard;
import leetcode.tag.type.BinarySearch;

/*
4. Median of Two Sorted Arrays

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

@Hard
@BinarySearch
public class MedianOfTwoSortedArray {

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int aLength = nums1.length;
    int bLength = nums2.length;

    int low = 0;
    int high = aLength;

    // we want to search on the shorter list
    while (low <= high) {
      int partitionA = (low + high)/2;
      int partitionB = (aLength + bLength + 1) /2 - partitionA; // plus one for odd number

      int maxLeftA = partitionA == 0 ? Integer.MIN_VALUE : nums1[partitionA - 1];
      int minRightA = partitionA == aLength ? Integer.MAX_VALUE : nums1[partitionA];

      int maxLeftB = partitionB == 0 ? Integer.MIN_VALUE : nums2[partitionB - 1];
      int minRightB = partitionB == bLength ? Integer.MAX_VALUE : nums2[partitionB];

      if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
        // get the point the the left side of the cut are all smaller than the right side of the cut
        if ((aLength + bLength) % 2 == 0) {
          return ((double)Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB))/2;
        } else {
          return (double)Math.max(maxLeftA, maxLeftB);
        }
      } else if (maxLeftA > minRightB) {
        // means for array we are searching in, the value is too big
        high = partitionA - 1;
      } else {
        low = partitionA + 1;
      }
    }

    return Integer.MIN_VALUE;
  }

  public static void main(String[] args) {
    int[] test1 = {2};
    int[] test2 = {};
    findMedianSortedArrays(test1, test2);

  }

}
