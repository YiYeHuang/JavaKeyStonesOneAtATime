package leetcode.dp;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/**
 746. Min Cost Climbing Stairs

 On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

 Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and you can either start from the step with index 0, or the step with index 1.

 Example 1:
 Input: cost = [10, 15, 20]
 Output: 15
 Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
 Example 2:
 Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 Output: 6
 Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
 Note:
 cost will have a length in the range [2, 1000].
 Every cost[i] will be an integer in the range [0, 999].
 */

@Amazon

@Easy
@DP
public class MinCostClimbingStairs {

	/**
	 *      [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
	 *
	 *   [0, 1, 100, ]
	 *
	 * Min Cost = MinCost[i - 1] + MainCost[i]
	 *
	 * Since we can take 1 or 2 steps, look back two item compare result minResult[i - 1], minResult[i - 2]
	 *
	 */
	public static int minCostClimbingStairs(int[] cost) {
		if(cost == null || cost.length == 0) return 0;
		int[] costResult = new int[cost.length+1];

		costResult[1]=cost[0];

		for(int i=2;i<=cost.length;i++) {
			costResult[i] = cost[i-1] + Math.min(costResult[i-2], costResult[i-1]);
		}
		return Math.min(costResult[costResult.length-1], costResult[costResult.length-2]);
	}

	public static void main(String[] args) {
		int[] test = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		minCostClimbingStairs(test);
	}
}
