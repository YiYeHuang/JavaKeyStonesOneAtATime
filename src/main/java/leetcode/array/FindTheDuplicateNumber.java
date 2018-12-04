package leetcode.array;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.TwoPointer;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
 *
 * Example 1:
 *
 * Input: [1,3,4,2,2]
 * Output: 2
 * Example 2:
 *
 * Input: [3,1,3,4,2]
 * Output: 3
 * Note:
 *
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */

@Microsoft
@Google
@Amazon
@Facebook

@Medium
@ArrayTag
@TwoPointer
@BinarySearch
public class FindTheDuplicateNumber {

    /**
     * KEYYYY of the problem
     * array nums containing n + 1 integers where each integer is between 1 and n (inclusive)
     * which means n is sorta mapping to the index of the array, n is no bigger to the size n
     * So this question is to find cycle in side a array,
     *
     * the total distance of the slow pointer traveled when encounter is
     * L1 + L2
     *
     * the total distance of the fast pointer traveled when encounter is
     * L1 + L2 + n * C
     *
     * Because the total distance the fast pointer traveled is twice as the slow
     * pointer, Thus:
     *
     * slow walker traveled 2  * (L1+L2) = L1 + L2 + n * C
     * =>
     * L1 + L2 = n * C
     * =>
     * L1 = (n - 1) C + (C - L2)
     *
     * count out C, L1 = C - L2
     */

    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        int head = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];

            if (slow == fast)
                break;
        }

        // Start at meeting point
        while (true) {
            slow = nums[slow];
            head = nums[head];
            if (slow == head)
                return slow;
        }
    }

    /**
     * binary search is not actually the best approach (understandable) of the question
     *
     * The idea is calculate a middle number between low and high,
     * then in for loop to check total numbers less or equal than middle.
     * If numbers less than middle,
     * then the duplicate must fall in the [low, middle] range;
     * else in [middle+1, high].
     *
     * For this solution,
     * time is O(nlgn)
     * space is O(1)
     */
    public static int bSearch(int[] a) {
        if (a.length == 0 || a == null)
            return 0;

        int low = 1;
        int high = a.length  - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            // Count the number in the range that less than mid
            int count = 0;

            for (int i = 0; i <a.length; i++) {
                if(a[i] <= mid) {
                    count++;
                }
            }

            if (count > mid)
                //duplicate occurs in [lower, mid]
                high = mid;
            else
                //duplicate occurs in [mid, high]
                low = mid + 1;
        }

        return low;
    }

    public static void main(String[] args) {
        int[] test = {1,3,4,2,1};
    }
}
