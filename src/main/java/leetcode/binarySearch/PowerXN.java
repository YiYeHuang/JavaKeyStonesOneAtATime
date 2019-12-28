package leetcode.binarySearch;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;

/**
 * Implement pow(x, n), which calculates x raised to the power n (xn).

 Example 1:

 Input: 2.00000, 10
 Output: 1024.00000
 Example 2:

 Input: 2.10000, 3
 Output: 9.26100
 Example 3:

 Input: 2.00000, -2
 Output: 0.25000
 Explanation: 2-2 = 1/22 = 1/4 = 0.25
 Note:

 -100.0 < x < 100.0
 n is a 32-bit signed integer, within the range [−231, 231 − 1]
 */

@Amazon

@Medium
@BinarySearch
public class PowerXN {

	/**
	 * without Math.pow, the core straight logic is x * x * x...... to n of x
	 *
	 * with doubling x, n can be divided into half and save time, the remaining logic is just to check even and odd
	 */
	public static double myPow(double x, int n) {
		if (n == 0) return 1;

		if (n == Integer.MIN_VALUE) {
			x = 1/x;
			return x*myPow(x, Math.abs(n + 1));
		} else if (n < 0) {
			n = Math.abs(n);
			x = 1/x;
		}

		return (n%2==0)? myPow(x*x, n/2):x*myPow(x*x, n/2);
	}

	public static void main(String[] args) {
		myPow(2.00000, -10);
	}
}
