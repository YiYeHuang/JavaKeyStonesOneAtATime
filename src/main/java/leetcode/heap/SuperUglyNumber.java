package leetcode.heap;

import leetcode.tag.company.Google;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Heap;
import leetcode.tag.type.Mathematics;

import java.util.PriorityQueue;

/**
 313. Super Ugly Number

 Write a program to find the nth super ugly number.

 Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k.

 Example:

 Input: n = 12, primes = [2,7,13,19]
 Output: 32
 Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12
 super ugly numbers given primes = [2,7,13,19] of size 4.
 Note:

 1 is a super ugly number for any given primes.
 The given numbers in primes are in ascending order.
 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 */
@Google

@Medium
@Heap
@Mathematics
public class SuperUglyNumber {

	public static int nthSuperUglyNumberHeap(int n, int[] primes) {
		int[] ugly = new int[n];

		PriorityQueue<Num> pq = new PriorityQueue<>();
		for (int i = 0; i < primes.length; i++) pq.add(new Num(primes[i], 1, primes[i]));
		ugly[0] = 1;

		for (int i = 1; i < n; i++) {
			ugly[i] = pq.peek().val;
			while (pq.peek().val == ugly[i]) {
				Num nxt = pq.poll();
				pq.add(new Num(nxt.p * ugly[nxt.idx], nxt.idx + 1, nxt.p));
			}
		}

		return ugly[n - 1];
	}

	private static class Num implements Comparable<Num> {
		int val;
		int idx;
		int p;

		public Num(int val, int idx, int p) {
			this.val = val;
			this.idx = idx;
			this.p = p;
		}

		@Override
		public int compareTo(Num that) {
			return this.val - that.val;
		}

		@Override
		public String toString() {
			return "[" + val + " " + idx + " " + p + "]";
 		}
	}

	public static void main(String[] args) {
		int[] test = {3,5,13,19,23,31,37,43,47,53};
		nthSuperUglyNumberHeap(30, test);
	}
}
