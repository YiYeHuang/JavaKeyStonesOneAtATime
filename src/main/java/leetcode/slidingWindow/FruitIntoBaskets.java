package leetcode.slidingWindow;

import leetcode.tag.level.Medium;
import leetcode.tag.type.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 904. Fruit Into Baskets
 *
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 */

@Medium
@SlidingWindow
public class FruitIntoBaskets {
    /**
     * Problem
     * "Start from any index, we can collect at most two types of fruits. What is the maximum amount"
     *
     * Translation
     * Find out the longest length of subarrays with at most 2 different numbers?
     *
     * Solution of sliding window will be easier to understand.
     * Here I share another solution wihtout hash map.
     * Hope it's not damn hard to understand.
     */
    public static int totalFruit(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0, j;

        /**
         * [i,j] is the sliding window, and it's the current longest window.
         * Suppose we found current longest window [i, j], then this length will be kept, because,
         * as j++, if the sliding window contains more than 2, then also we will do i++, so that is to say,
         * the current longest length will be kept the same,
         * in another word, when [i,j] reached a maximum, it will not become smaller
         * on the other side, when j++, if the current window contains less than two,
         * then i will not increase(because the if statement), in this way,
         * the longest length of sliding window will get updated
         *
         */
        for (j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            if (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                count.remove(tree[i++], 0);
            }
        }
        return j - i;
    }

    public static int totalFruit2(int[] tree) {
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {3,3,3,1,2,1,1,2,3,3,4};
        totalFruit2(test);
    }

}
