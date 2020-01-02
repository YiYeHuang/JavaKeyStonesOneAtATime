package leetcode.backtrack;


import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 *
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: nums = [1,2,3]
 * Output:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 */
@Medium
@BackTrack
public class SubSets {

    /**
     * Basic of backtracking question, go through all possible solutions
     *
     * be req
     * - need to add element
     * - need to remove element
     * - need to have empty set
     * - trick: move staring point
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    public static void backtrack(List<List<Integer>> result,List<Integer> temp, int[] nums, int startIndex) {
        // the first call will add the []
        result.add(new ArrayList<>(temp));
        for (int i = startIndex; i < nums.length; i++) {
            temp.add(nums[i]);
            // move position up 1
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> res = subsets(nums);
    }
}
