package leetcode.binaryIndexTree;

import dataStructure.tree.FenwickTree;
import leetcode.tag.level.Hard;
import leetcode.tag.type.BinaryIndexTree;

import java.util.*;

/*
327. Count of Range Sum

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:

Input: nums = [-2,5,-1], lower = -2, upper = 2,
Output: 3
Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
 */

@Hard
@BinaryIndexTree
public class CountRangeSum {

    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        if(n == 0) return 0;
        // prefix array
        long[] prefixSum = new long[n];
        for(int i = 0; i < n; i++) {
            prefixSum[i] = (i > 0 ? prefixSum[i-1] : 0) + nums[i];
        }
        long[] sorted = Arrays.copyOf(prefixSum, prefixSum.length);
        Arrays.sort(sorted);
        // binary index tree
        map = new HashMap();
        int idx = 1;
        for(long sum : sorted) {
            if(!map.containsKey(sum)) map.put(sum, idx++);
        }
        // build tree
        BIT t = new BIT(idx);
        int res = 0;
        for(int i = 0; i < n; i++) {
            int l = binarySearch(sorted, prefixSum[i] - upper - 1);
            int r = binarySearch(sorted, prefixSum[i] - lower);
            res += t.sum(r) - t.sum(l);
            if(prefixSum[i] >= lower && prefixSum[i] <= upper) res += 1;
            t.add(map.get(prefixSum[i]), 1);
        }
        return res;
    }
    Map<Long, Integer> map;
    // find the last element <= val
    private int binarySearch(long[] arr, long val) {
        int l = 0, r = arr.length - 1;
        while(l < r) {
            int mid = l + (r - l) / 2 + 1;
            if(arr[mid] <= val) l = mid;
            else r = mid - 1;
        }
        if(arr[l] > val) return 0;
        return map.get(arr[l]);
    }

    class BIT {
        int n;
        int[] tree;
        BIT(int n) { this.n = n; tree = new int[n]; }

        protected int sum(int i) {
            int res = 0;
            while(i > 0) {
                res += tree[i];
                i -= i & -i;
            }
            return res;
        }

        protected void add(int i, int val) {
            while(i < n) {
                tree[i] += val;
                i += i & -i;
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {2,5,-1};
        CountRangeSum sm = new CountRangeSum();
        sm.countRangeSum(test, -2, 2);
    }
}
