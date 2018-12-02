package leetcode.binarySearch;

import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 * Let's call an array A a mountain if the following properties hold:
 *
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 *
 * Example 1:
 *
 * Input: [0,1,0]
 * Output: 1
 * Example 2:
 *
 * Input: [0,2,1,0]
 * Output: 1
 * Note:
 *
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 */

@Google
@Bloomberg
@Facebook

@Easy
@BinarySearch
public class PeakIndexinMountainArray {

    /**
     * Mid is now used to compare if up hill or down hill.
     *
     * the key if compare mid with adjacent value
     */
    public static int peakIndexInMountainArray(int[] A) {
        int low = 0;
        int high = A.length - 1;

        while (low < high) {
            int mid = low + (high - low)/2;

            // up fill
            if (A[mid] < A[mid+1]) {
                low = mid + 1;
            } else {
                // down hill
                high = mid;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] test = {3, 4, 5, 4, 2, 1, 0};
        System.out.print(peakIndexInMountainArray(test));
    }
}
