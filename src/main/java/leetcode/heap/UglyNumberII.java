package leetcode.heap;

import leetcode.tag.company.Google;
import leetcode.tag.level.Medium;
import leetcode.tag.type.DP;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/**
 264. Ugly Number II

 Write a program to find the n-th ugly number.

 Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

 Example:

 Input: n = 10
 Output: 12
 Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 Note:

 1 is typically treated as an ugly number.
 n does not exceed 1690.
 */

@Google

@Medium
@DP
@Heap
public class UglyNumberII {
	/**
	 * Previous result matter problem
	 *
	 * only count 2 3 5 and 2 3 5's result
	 */
	public static int nthUglyNumberDP(int n) {
		int[] ugly = new int[n];
		ugly[0] = 1;

		// how to use the count is the key, as we should only go with 2, 3, 5 and the result of 2 3 5
		// count2 3 5 are actually the index
		int count2 = 0;
		int count3 = 0;
		int count5 = 0;

		// also cache the result of 2, 3, 5 factor
		int factor2 = 2;
		int factor3 = 3;
		int factor5 = 5;

		for (int i = 1; i < n; i++) {
			int next = Math.min(Math.min(factor2, factor3), factor5);
			ugly[i] = next;

			if (factor2 == next) {
				count2++;
				factor2 = 2*ugly[count2];
			}
			if (factor3 == next) {
				count3++;
				factor3 = 3*ugly[count3];
			}
			if (factor5 == next) {
				count5++;
				factor5 = 5*ugly[count5];
			}
		}

		return ugly[n-1];
	}

	/**
	 * min heap solution, keep the min result at top, keep pushing the previous result * 2 * 3 and * 5
	 *
	 * trade off is memory usage
	 */
	public static int nthUglyNumberPQ(int n) {
		PriorityQueue<Long> minHeap = new PriorityQueue<>();
		minHeap.add(1l);


		for (int i = 0; i < n; i++) {
			Long val = minHeap.poll();

			// add this step to remove duplicate;
			while(!minHeap.isEmpty() && minHeap.peek()==val) val = minHeap.poll();

			minHeap.add(val * 2);
			minHeap.add(val * 3);
			minHeap.add(val * 5);
		}

		return minHeap.poll().intValue();
	}

	public static void main(String[] args) {
		nthUglyNumberPQ(10);
	}
}
