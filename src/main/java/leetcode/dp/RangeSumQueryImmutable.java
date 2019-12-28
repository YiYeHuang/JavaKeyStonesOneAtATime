package leetcode.dp;

import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/*
 303. Range Sum Query - Immutable

 Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

 Example:
 Given nums = [-2, 0, 3, -5, 2, -1]

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 Note:
 You may assume that the array does not change.
 There are many calls to sumRange function.
 */


@Easy
@DP
public class RangeSumQueryImmutable {

	/**
	 * [-2, 0, 3, -5, 2, -1]
	 * accumulate
	 * [-2, -2, 1, -4, -2, -3]
	 *
	 *  -2		i0
	 *  -2		i0 + i1
	 *  1		i0 + i1 + i2
	 *  -4		i0 + i1 + i2 + i3
	 *  -2		i0 + i1 + i2 + i3 + i4
	 *  -3		i0 + i1 + i3 + i3 + i4 + i5
	 *
	 *  (0, 5) can get result at once
	 *  (3, 5)  = i4 + i5 + i6
	 *          = i0 + i1 + i2 + i3 + i4 + i5 - (i0 + i1 + i2)
	 *          = -3 - (1) = -4
	 *
	 */
	int[] result;
	public RangeSumQueryImmutable(int[] nums) {
		for(int i = 1; i < nums.length; i++)
			nums[i] += nums[i - 1];

		result = nums;
	}

	public int sumRange(int i, int j) {
		if(i == 0)
			return result[j];

		return result[j] - result[i - 1];
	}
}
