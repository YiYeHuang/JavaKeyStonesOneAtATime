package leetcode.dp;

/**
 276. Paint Fence

 There is a fence with n posts, each post can be painted with one of the k colors.

 You have to paint all the posts such that no more than two adjacent fence posts have the same color.

 Return the total number of ways you can paint the fence.

 Note:
 n and k are non-negative integers.

 Example:

 Input: n = 3, k = 2
 Output: 6
 Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

 post1  post2  post3
 -----      -----  -----  -----
 1         c1     c1     c2
 2         c1     c2     c1
 3         c1     c2     c2
 4         c2     c1     c1
 5         c2     c1     c2
 6         c2     c2     c1
 */
public class PaintFence {

	/**
	 * k = 3
	 *
	 * n = 0    o
	 *
	 * n = 1    c1	c2	c3											k ways
	 *
	 * n = 2    same color	c1	c2	c3
	 * 						c1	c2	c3								k ways    ----> same
	 *																								|-----> k * k ways
	 * 			diff  color	c1				c2			c3
	 * 		    			(c2, c3)		(c1, c3)	(c1, c2)	k * (k - 1) ways ----> diff
	 *
	 *
	 *    		based on n = 2 same color
	 * n = 3	c1		c2		c3
	 * 			c1		c2		c3
	 * 		    c3,c2	c1,c3	c2,c1								same * (k - 1) ways
	 *
	 *
	 * 		    based on n = 3 diff color
	 * 			 c1				 c2				 c3
	 * 		   	(c2, c3)		(c1, c3)		(c1, c2)
	 * 			(c1, c2 , c3)	(c1, c2 , c3)	(c1, c2 , c3)		diff*(k)
	 *
	 * 	n = 3    -> same * (k - 1) ways + diff*(k) =  k * (k - 1) + k * (k - 1) * k
	 * 											   =  ( k - 1 ) + (k  	+  k * k)
	 * 											   				   i -1	  i - 2
	 *
	 *
	 *
	 * 	num_ways(i) = num_ways_diff(i) + num_ways_same(i)
	 *
	 *
	 *
	 */
	public int numWays(int n, int k) {

		if (n == 0) return 0;

		if (n == 1) return k;

		if (n == 2) return k*k;

		int table[] = new int[n+1];
		table[0] = 0;
		table[1] = k;
		table[2] = k*k;
		for (int i = 3; i <= n; i++) {
			table[i] = (table[i-1] + table[i-2]) * (k-1);
		}
		return table[n];
	}
}
