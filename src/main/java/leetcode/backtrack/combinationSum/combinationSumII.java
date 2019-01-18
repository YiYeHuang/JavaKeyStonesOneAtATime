package leetcode.backtrack.combinationSum;

import leetcode.tag.company.LinkedIn;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. Combination Sum II
 *
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 *
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * A solution set is:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * A solution set is:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
@LinkedIn
@Microsoft

@Medium
@BackTrack
public class combinationSumII {

	/**
	 * combination sum + subset skip logic
	 *
	 *
	 */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    public static void backtrack(List<List<Integer>> result,List<Integer> temp, int[] candidates, int remains ,int startIndex) {
        if (remains < 0) return;
        if (remains == 0) result.add(new ArrayList<>(temp));

        for (int i = startIndex; i < candidates.length; i++) {
			// avoid -1 index and extra skip
			if (i > startIndex && candidates[i] == candidates[i - 1]) continue;
            temp.add(candidates[i]);
            // move position up 1
            backtrack(result, temp, candidates, remains - candidates[i],  i + 1); // incremenet, cuz every only use once
			// reuse
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
    	int[] test = {10, 1, 2, 7, 6, 1, 5};
    	combinationSum2(test, 8);
	}

}
