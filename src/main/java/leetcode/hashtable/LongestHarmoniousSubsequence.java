package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;

/**
 594. Longest Harmonious Subsequence

 We define a harmonious array is an array where the difference between its maximum value and its minimum value is exactly 1.

 Now, given an integer array, you need to find the length of its longest harmonious subsequence among all its possible subsequences.

 Example 1:
 Input: [1,3,2,2,5,2,3,7]
 Output: 5
 Explanation: The longest harmonious subsequence is [3,2,2,2,3].

 */

@Easy
@HashTableTag
public class LongestHarmoniousSubsequence {

	/**
	 *
	 * [1,3,2,2,5,2,3,7]
	 *
	 * 1   3   2   5   7
	 * 1   2   3   1   1
	 *
	 * dynamically update the hashmap while visit the num list, calculate the
	 */
	public int findLHS(int[] nums) {
		HashMap<Integer, Integer> nmap = new HashMap<>();

		int maxResult = 0;
		for (int num: nums) {

			nmap.put(num, nmap.getOrDefault(num, 0) + 1);

			if (nmap.containsKey(num - 1)) {
				maxResult = Math.max(maxResult, nmap.get(num) + nmap.get(num - 1));
			}
			if (nmap.containsKey(num + 1)) {
				maxResult = Math.max(maxResult, nmap.get(num) + nmap.get(num + 1));
			}

		}

		return maxResult;
	}

	public static void main(String[] args) {

	}
}
