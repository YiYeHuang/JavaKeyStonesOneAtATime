package leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 47. Permutations II
 *
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 *
 * Example:
 *
 * Input: [1,1,2]
 * Output:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 */
public class PermutationsII {

    /**
     * Approach further, with unique, we cannot skip like subset as order does matter,
     * use extra space for marking storage
     */
    public List<List<Integer>> permuteUniqueBooleanMark(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums==null || nums.length==0) return res;
        boolean[] used = new boolean[nums.length];
        // sort for grouping the duplicate
        Arrays.sort(nums);
        backtrack(res, new ArrayList<Integer>(), nums, used);
        return res;
    }

    public void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used){
        if (list.size() == nums.length) {
            res.add(new ArrayList<Integer>(list));
            return;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) continue; // skip the used position
                if (i > 0 && nums[i - 1] == nums[i] && !used[i-1]) continue;

                used[i] = true;
                list.add(nums[i]);
                backtrack(res, list, nums, used);
                list.remove(list.size() - 1);
            }
        }
    }


}
