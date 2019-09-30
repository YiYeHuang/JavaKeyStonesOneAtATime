package leetcode.binaryIndexTree;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Design;
import leetcode.tag.type.Tree;

/*
307. Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

The update(i, val) function modifies nums by updating the element at index i to val.

Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8
 */

@Medium
@Tree
@Design
public class RangeSumQuery_Mutable {

    int[] nums;
    int[] BIT;
    int n;

    public RangeSumQuery_Mutable(int[] nums) {
        this.nums = nums;

        n = nums.length;
        BIT = new int[n + 1];
        for (int i = 0; i < n; i++) {
            updateTree(i, nums[i]);
        }
    }

    public void updateTree(int i, int val) {
        i++;
        while (i <= n) {
            BIT[i] += val;
            i += (i & -i);
        }
    }

    public void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateTree(i, diff);
    }

    public int getSum(int i) {
        int sum = 0;
        i++;
        while (i > 0) {
            sum += BIT[i];
            i -= (i & -i);
        }
        return sum;
    }

    public int sumRange(int i, int j) {
        return getSum(j) - getSum(i - 1);
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 5};

        RangeSumQuery_Mutable mu = new RangeSumQuery_Mutable(test);
        System.out.println(mu.sumRange(0,1));
    }
}
