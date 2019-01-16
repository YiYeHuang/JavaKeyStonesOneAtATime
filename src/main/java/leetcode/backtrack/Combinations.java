package leetcode.backtrack;

import leetcode.tag.company.Google;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */

@Google

@Medium
@BackTrack
public class Combinations {

    /**
     * Direct template solution, go through all possible solution
     * - no removal
     * - meet k then add to result
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), n, 1 , k);
        return list;
    }

    public static void backtrack(List<List<Integer>> result, List<Integer> combination, int n, int start, int k) {
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));
            return;
        } else {
            // goes through all the result list all the time
            for (int i = start; i <= n; i++) {
                List<Integer> newPermutation = new ArrayList<>(combination);
                newPermutation.add(i);
                backtrack(result, newPermutation, n, i + 1, k);
            }
        }
    }

    public static void backtrackOPT(List<List<Integer>> result, List<Integer> combination, int n, int start, int k) {
        if (k == 0) {
            result.add(new ArrayList<>(combination));
            return;
        } else {
            // goes through all the result list all the time
            for (int i = start; i <= n; i++) {
                combination.add(i);
                backtrack(result, combination, n, i + 1, k - 1);
                combination.remove(combination.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        combine(4, 2);
    }
}
