package leetcode.binarySearch;

import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;

import java.util.Collections;
import java.util.List;

/*
658. Find K Closest Elements

Given a sorted array, two integers k and x, find the k closest elements to x in the array.
The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
 */

@Medium
@BinarySearch
public class KindkClosestElements {
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        // java binarySearch will return index or where index where it should be inserted
        int index = Collections.binarySearch(arr, x);
        if(index < 0) index = -(index + 1);

        int left = index - 1, right = index;
        while(k-- > 0){
            if( left <0 || (right < arr.size() && Math.abs(arr.get(left) - x) > Math.abs(arr.get(right) - x) )) {
                // right side diff is smaller, expend to right
                right++;
            } else {
                // left side diff is smaller expend to left
                left--;
            }
        }

        return arr.subList(left+1, right);
    }
}
