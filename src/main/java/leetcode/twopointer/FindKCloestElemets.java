package leetcode.twopointer;

import java.util.ArrayList;
import java.util.List;
import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

/*
658. Find K Closest Elements

Given a sorted array arr, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.



Example 1:

Input: arr = [1,2,3,4,5], k = 4, x = 3
Output: [1,2,3,4]
Example 2:

Input: arr = [1,2,3,4,5], k = 4, x = -1
Output: [1,2,3,4]


Constraints:

1 <= k <= arr.length
1 <= arr.length <= 10^4
Absolute value of elements in the array and x will not exceed 104
 */

@Medium
@TwoPointer
public class FindKCloestElemets {

  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int low = 0;
    int high = arr.length;

    // two pointer to lock down the range
    while(high - low >= k) {

      // look for the smaller diff in the range
      if (Math.abs(arr[low] - x) > Math.abs(arr[high] - x)) {
        low++;
      } else {
        high --;
      }
    }

    List<Integer> result = new ArrayList<>(k);
    for (int i = low; i <= high; i++) {
      result.add(arr[i]);
    }
    return result;

  }

}
