package leetcode.backtrack.combinationSum;

import leetcode.tag.company.*;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. Combination Sum
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 *   [7],
 *   [2,2,3]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 */
@Amazon
@Google
@Microsoft
@Apple
@LinkedIn

@Medium
@BackTrack
public class combinationSum {
	/**
	 * Subset + login for deal with remaining
	 *
	 * if remaining is 0 add result
	 */
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(result, new ArrayList<>(), candidates, target, int start);
		return result;
	}

	public static void backtrack(List<List<Integer>> result,List<Integer> temp, int[] candidates, int remains ,int startIndex) {
		if (remains < 0) return;
		if (remains == 0) result.add(new ArrayList<>(temp));

		for (int i = startIndex; i < candidates.length; i++) {

			temp.add(candidates[i]);
			// move position up 1
			backtrack(result, temp, candidates, remains - candidates[i],  +i); // not increment to i + 1 because reuse
			temp.remove(temp.size() - 1);
		}
	}

	public static void main(String[] args) {
		int[] nums = {1,2,2};
	}
}
