package leetcode.heap;

import leetcode.tag.level.Hard;
import leetcode.tag.type.Design;
import leetcode.tag.type.Heap;
import leetcode.tag.type.SlidingWindow;

import java.util.PriorityQueue;

/**
 480. Sliding Window Median

 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 Examples:
 [2,3,4] , the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Your job is to output the median array for each window in the original array.

 For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.

 Window position                Median
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 Therefore, return the median sliding window as [1,-1,-1,3,5,6].

 Note:
 You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.

 */

@Hard
@Heap
@Design
@SlidingWindow
public class SlidingWindowMedian {
	/**
	 * Same idea to use two priority queue,
	 */
	// length always n/2
	private PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b)-> a - b);
	// length always n/2 or n/2 + 1
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b)-> b - a);

	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		if (n <= 0) return new double[0];
		double[] result = new double[n - k + 1];

		for (int i = 0; i < nums.length; i++){
			addNum(nums[i]);
			if (i - k >= 0) {
				remove(nums[i - k]);
			}

			// deal with the extra position
			if (i - k + 1 >= 0) {
				result[i - k + 1] = findMedian();
			}
		}

		return result;
	}

	// EVEN: min heap get value
	// ODD: max heap get value
	public void addNum(int num) {
		if (minHeap.isEmpty()) {
			minHeap.offer(num);
			return;
		} else if (minHeap.peek() <= num) {
			minHeap.offer(num);
		} else {
			maxHeap.offer(num);
		}
		rebalance();
	}

	public double findMedian() {
		if (minHeap.size() == maxHeap.size())
			return (minHeap.peek()/ 2.0 + maxHeap.peek()/ 2.0) ;
		else
			return minHeap.peek();
	}

	public void remove(int n) {
		// min heap, min is at top, n is in min heap
		if (minHeap.peek() <= n) {
			// means max part needs to cut of one
			minHeap.remove(n);
		} else {
			maxHeap.remove(n);
		}
		rebalance();
	}

	public void rebalance() {
		if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		} else if (maxHeap.size() > minHeap.size()) {
			minHeap.offer(maxHeap.poll());
		}
	}
}
