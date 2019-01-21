package leetcode.dp;

import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/**
 256. Paint House

 There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

 The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

 Note:
 All costs are positive integers.

 Example:

 Input: [[17,2,17],[16,16,5],[14,3,19]]
 Output: 10
 Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
 Minimum cost: 2 + 5 + 3 = 10.

 */

@LinkedIn

@Easy
@DP
public class PaintHouse {

	/**
	 * Min = Min(i - 1) + Min(i)
	 *
	 * however Min(i - 1) is bind by color rule
	 *
	 * Min(0) = min in cost[0] RGB RGB
	 * Min(1) = min combination of R0G1, R0B1, G0R1, G0B1, B0R1, B0G1
	 *  .
	 *  .
	 *  .
	 *
	 * look back one item
	 */
	public static int minCost(int[][] costs) {
		if (costs.length == 0 || costs[0].length == 0) return -1;
		for(int i = 1; i < costs.length; i++) {
			calMin(costs[i-1], costs[i]);
		}

		int last = costs.length -1;
		return Math.min(Math.min(costs[last][0], costs[last][1]), costs[last][2]);
	}

	public static void calMin(int[] a, int[] b) {
		b[0] = Math.min(b[0] + a[1], b[0] + a[2]);
		b[1] = Math.min(b[1] + a[0], b[1] + a[2]);
		b[2] = Math.min(b[2] + a[0], b[2] + a[1]);
	}

	public static void main(String[] args) {
		int[][] costs = {{17,2,17},{16,16,5},{14,3,19}};
		minCost(costs);
	}
}
