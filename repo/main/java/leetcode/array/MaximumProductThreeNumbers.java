package leetcode.array;

import Tag.type.Array;
import Tag.type.Mathematics;

import java.util.Arrays;

/**
 Given an integer array, find three numbers whose product is maximum and output the maximum product.

 Example 1:
 Input: [1,2,3]
 Output: 6
 Example 2:
 Input: [1,2,3,4]
 Output: 24
 Note:
 The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
 Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
 */
public class MaximumProductThreeNumbers {

	@Array
	@Mathematics
	public static int maximumProduct(int[] nums) {
		Arrays.sort(nums);

		int result1 = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
		int result2 = nums[0] * nums[1] * nums[nums.length - 1];

		return result1 > result2 ? result1 : result2;
	}

	/**
	 * Either +++ will get the max or  - - + will get the max
	 */
	@Array
	@Mathematics
	public static int maximumProductOrder(int[] nums) {
		int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE, min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
		for (int n : nums) {
			if (n > max1) {
				max3 = max2;
				max2 = max1;
				max1 = n;
			} else if (n > max2) {
				max3 = max2;
				max2 = n;
			} else if (n > max3) {
				max3 = n;
			}

			if (n < min1) {
				min2 = min1;
				min1 = n;
			} else if (n < min2) {
				min2 = n;
			}
		}
		return Math.max(max1*max2*max3, max1*min1*min2);
	}

	public static void main(String[] args) {
		int[] test = {722,634,-504,-379,163,-613,-842,-578,750,951,-158,30,-238,-392,-487,-797,-157,-374,999,-5,-521,-879,-858,382,626};
		System.out.print(maximumProduct(test));
	}

}
