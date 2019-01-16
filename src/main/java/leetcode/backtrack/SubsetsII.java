package leetcode.backtrack;

import leetcode.tag.company.Bloomberg;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 */
@Bloomberg

@Medium
@BackTrack
public class SubsetsII {
    /**
     * Backtracking question upgrade (with duplicate), go through all possible solutions
     *
     * be req
     * - need to add element
     * - need to remove element
     * - need to have empty set
     *
     * - trick: sort, sort to make sure all duplicate are grouped
     * - trick: move staring point
     * - trick: skip duplicate
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public static void backtrack(List<List<Integer>> result,List<Integer> temp, int[] nums, int startIndex) {
        // the first call will add the []
        result.add(new ArrayList<>(temp));
        for (int i = startIndex; i < nums.length; i++) {
            // avoid -1 index and extra skip
            if (i > startIndex && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            // move position up 1
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> res = subsets(nums);
    }
}
