package leetcode.backtrack;

import leetcode.tag.company.*;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 *
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */

@LinkedIn
@Google
@Facebook
@Amazon
@Bloomberg

@Medium
@BackTrack
public class Permutations {

    /**
     * General approach,
     * [1] -> [1, 2] -> [1, 2, 3]               recursion 1
     *  \
     *   \  remove 1
     *    -> [2] -> [2, 3] -> [2, 3, 1]         recursion 2
     *     \
     *      \  remove 2
     *       ->  [3] -> [3, 1] -> [3, 1, 2]     recursion 3
     *       .
     *       .
     *       .
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<Integer>(), nums);
        return list;
    }

    public static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums) {
        if (temp.size() == nums.length) {
            // make sure add new collection
            result.add(new ArrayList<>(temp));
            return;
        } else {
            // goes through all the result list all the time
            for (int i = 0; i < nums.length; i++) {
                if (temp.contains(nums[i])) {
                    continue;
                }
                temp.add(nums[i]);
                backtrack(result, temp, nums);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static List<List<Integer>> permuteOPT(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        backtrackOPT(list, 0, new ArrayList<>(), nums);
        return list;
    }

    public static void backtrackOPT(List<List<Integer>> result, int start, List<Integer> permutation, int[] nums) {
        if (permutation.size() == nums.length) {
            result.add(permutation);
            return;
        } else {
            // goes through all the result list all the time
            for (int i = 0; i < permutation.size(); i++) {
                List<Integer> newPermutation = new ArrayList<>(permutation);
                newPermutation.add(i, nums[start]);
                backtrackOPT(result, start + 1, newPermutation, nums);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        permute(nums);
    }
}
