package leetcode.dp;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Apple;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;
import leetcode.tag.type.Hash;

/**
 Count the number of prime numbers less than a non-negative number, n.

 Example:

 Input: 10
 Output: 4
 Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 */
@Microsoft
@Apple

@Easy
@Hash
@DP
public class CountPrime {

	/**
	 * Use the extra space to calculate not prime number
	 *
	 * - 1, 2, 3 are prime, starting there
	 * 		- 1, 2, 3 times the remaining are all not prime
	 * 	    - go through the list and mark
	 * 	    - go through the list and count
	 */
	public static int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
		int count = 0;
		for (int i = 2; i < n; i++) {
			if (notPrime[i] == false) {
				count++;
				for (int j = 2; i*j < n; j++) {
					notPrime[i*j] = true;
				}
			}
		}

		return count;
	}

	public static void main(String[] args) {
		countPrimes(16);
	}
}
