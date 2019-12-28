package leetcode.backtrack;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. Generate Parentheses
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 */

@Medium
@BackTrack
public class GenerateParentheses {
    /**
     * go through all possible solutions,
     * follow the rules that
     * - whenever there is a (, it should be followed by a )
     */
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }

    public static void backtrack(List<String> list, String result, int open, int close, int max) {
        if (result.length() == max * 2) {
            // reach the result
            list.add(result);
            return;
        }

        if (open < max) {
            backtrack(list, result + "(", open + 1, close, max);
        }

        if (close < open) {
            backtrack(list, result + ")", open, close + 1, max);
        }
    }

    public static void main(String[] args) {
        generateParenthesis(3);
    }
}
