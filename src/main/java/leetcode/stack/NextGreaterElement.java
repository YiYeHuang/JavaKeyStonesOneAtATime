package leetcode.stack;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.StackTag;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 496. Next Greater Element I

 You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2.
 Find all the next greater numbers for nums1's elements in the corresponding places of nums2.

 The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2.
 If it does not exist, output -1 for this number.

 Example 1:
 Input: nums1 = [4,1,2], nums2 = [1,3,4,2].
 Output: [-1,3,-1]
 Explanation:
 For number 4 in the first array, you cannot find the next greater number for it in the second array, so output -1.
 For number 1 in the first array, the next greater number for it in the second array is 3.
 For number 2 in the first array, there is no next greater number for it in the second array, so output -1.
 Example 2:
 Input: nums1 = [2,4], nums2 = [1,2,3,4].
 Output: [3,-1]
 Explanation:
 For number 2 in the first array, the next greater number for it in the second array is 3.
 For number 4 in the first array, there is no next greater number for it in the second array, so output -1.
 */

@Easy
@StackTag
@HashTableTag
public class NextGreaterElement {

	/**
	 * Bad part of this question is that the original list order cannot be modified
	 * good part of this question is that num1 is a sub set of num 2
	 *
	 * Can use stack to stack up the number, and mapping into pair (number : the next larger number)
	 *
	 * then goes through the lookup list and find the pair in the map
	 */
	public static int[] nextGreaterElement(int[] findNums, int[] nums) {
		Map<Integer, Integer> map = new HashMap<>(); // map from x to next greater element of x
		Stack<Integer> stack = new Stack<>();
		for (int num : nums) {
			while (!stack.isEmpty() && stack.peek() < num)
				map.put(stack.pop(), num);
			stack.push(num);
		}
		for (int i = 0; i < findNums.length; i++)
			findNums[i] = map.getOrDefault(findNums[i], -1);
		return findNums;
	}

	public static void main(String[] args) {
		int[] nums1 = {4,1,2}, nums2 = {1,3,4,2};
		nextGreaterElement(nums1, nums2);
	}
}
