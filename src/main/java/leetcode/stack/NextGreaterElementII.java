package leetcode.stack;

import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/**
 * 503. Next Greater Element II

 Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

 Example 1:
 Input: [1,2,1]
 Output: [2,-1,2]
 Explanation: The first 1's next greater number is 2;
 The number 2 can't find next greater number;
 The second 1's next greater number needs to search circularly, which is also 2.
 */

@Medium
@StackTag
@HashTableTag
public class NextGreaterElementII {

	/**
	 * Similar to greater element I
	 * question can be understand as nextGreaterElement(int[] nums, int[] nums) by with cycle style
	 * which means nums is better check from back of the list
	 *
	 * so used a stack to push number from back of the list,
	 * pop is stack value is less than current, until find one, then push it self into stack as well.
	 *
	 */
	public static int[] nextGreaterElement(int[] nums) {
		Stack<Integer> value = new Stack<>();
		for(int i = nums.length - 1; i >= 0; --i) {
			value.push(nums[i]);
		}

		int[] result = new int[nums.length];
		for(int i = nums.length - 1; i >= 0; --i) {
			while(!value.isEmpty() && value.peek() <= nums[i]) {
				value.pop();
			}

			int right = (value.isEmpty()) ? -1 : value.peek();
			result[i] = right;
			value.push(nums[i]);
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums1 = {100,1,11,1,120,111,123,1,-1,-100};
		nextGreaterElement(nums1);
	}
}
