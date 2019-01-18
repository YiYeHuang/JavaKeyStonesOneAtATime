package leetcode.backtrack.combinationSum;

import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 216. Combination Sum III
 *
 * Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 *
 * Note:
 *
 * All numbers will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 * Example 2:
 *
 * Input: k = 3, n = 9
 * Output: [[1,2,6], [1,3,5], [2,3,4]]
 */

@Microsoft

@Medium
@BackTrack
public class combinationSumIII {

	public static List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(result, new ArrayList<>(), k, n,  1);
		return result;
	}

	public static void backtrack(List<List<Integer>> result,List<Integer> temp, int k, int remains, int startIndex) {
		if (remains < 0) return;
		if (remains == 0 && temp.size() == k) result.add(new ArrayList<>(temp));

		for (int i = startIndex; i < 9; i++){
			temp.add(i);
			// move position up 1
			backtrack(result, temp, k, remains - i,  i + 1);
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] test = {10, 1, 2, 7, 6, 1, 5};
		combinationSum3(2, 18);
	}
}
