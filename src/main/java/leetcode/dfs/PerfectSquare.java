package leetcode.dfs;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BFS;
import leetcode.tag.type.DP;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 279. Perfect Squares

 Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

 Example 1:

 Input: n = 12
 Output: 3
 Explanation: 12 = 4 + 4 + 4.
 Example 2:

 Input: n = 13
 Output: 2
 Explanation: 13 = 4 + 9.
 */
@Amazon

@Medium
@DP
@BFS
public class PerfectSquare {
	public int numSquares(int n) {
		if(n<0) return 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(n);

		int count =0;
		/**
		 * starting from target number n=13, enqueue all numbers that are 'n minus a perfect square number', 13-1, 13-4, 13-9, and increment count.
		 dequeue numbers and do the same, until we found 0 then return the count.
		 */
		while (!queue.isEmpty()) {
			int size = queue.size();
			// go through the queue and push in each result of previous remain
			for (int i = size; i > 0; i--) {
				int num = queue.poll();
				for(int k= 1; k*k < num; i++) {
					// when hit match, increment counter
					if (num == i*i) return ++count;
					queue.offer(num - i*i);
				}
			}
			count++;
		}
		return -1;
	}

	/**
	 * DP[13] = DP[13-1x1]+DP[1] = DP[12]+1 = 3;
	 *
	 * DP[13] = DP[13-2x2]+DP[2x2] = DP[9]+1 = 2;
	 *
	 * DP[13] = DP[13-3x3] + DP[3x3] = DP[4] + 1 = 2;
	 *
	 *
	 * 子问题：
	 * D[n] = 1 + min (dp[i-j*j] for j*j<=i)
	 *
	 dp[0] = 0
	 dp[1] = dp[0]+1 = 1
	 dp[2] = dp[1]+1 = 2
	 dp[3] = dp[2]+1 = 3
	 dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
	 = Min{ dp[3]+1, dp[0]+1 }
	 = 1
	 dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
	 = Min{ dp[4]+1, dp[1]+1 }
	 = 2
	 .
	 .
	 .
	 dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
	 = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
	 = 2
	 .
	 .
	 .
	 dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
	 */
	public static  int numSquaresDP(int n) {
		// 造状态转移
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1; i <= n; ++i) {
			int min = Integer.MAX_VALUE;
			int j = 1;
			while(i - j*j >= 0) {
				min = Math.min(min, dp[i - j*j] + 1);
				++j;
			}
			dp[i] = min;
		}
		return dp[n];
	}

	public static void main(String[] args) {
		numSquaresDP(13);
	}
}
