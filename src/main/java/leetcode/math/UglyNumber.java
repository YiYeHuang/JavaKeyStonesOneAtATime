package leetcode.math;

/**
 263. Ugly Number

 Write a program to check whether a given number is an ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example 1:

 Input: 6
 Output: true
 Explanation: 6 = 2 × 3
 Example 2:

 Input: 8
 Output: true
 Explanation: 8 = 2 × 2 × 2
 Example 3:

 Input: 14
 Output: false
 Explanation: 14 is not ugly since it includes another prime factor 7.
 Note:

 1 is typically treated as an ugly number.
 Input is within the 32-bit signed integer range: [−231,  231 − 1].
 */
public class UglyNumber {

	/**
	 * Learn from fib math mark down solution,
	 * this failed at -2147483648
	 */
	public static boolean isUglyMarkDown(int num) {

		boolean[] ungly = new boolean[Math.abs(num)];

		for (int i = 2; i < num; i++) {
			if (i == 2 || i == 5 || i == 3) {
				ungly[i] = true;
				for (int j = 2; i*j < num; j++) {
					ungly[i*j] = true;
				}
			}

			if (ungly[i] == false && (num % i != 0)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Math solution
	 * num divide by 2, 3 and 5 as often as possible and then check whether we arrived at 1.
	 */
	public static boolean isUglyMarkDownMath(int num) {
		if (num == 0) return false;
		if (num == 1) return true;

		while (num != 1) {
			if (num % 2 == 0) num /= 2;
			else if (num % 3 == 0) num /= 3;
			else if (num % 5 == 0) num /= 5;
			else {
				return false;
			}
		}

		return true;
	}

}
