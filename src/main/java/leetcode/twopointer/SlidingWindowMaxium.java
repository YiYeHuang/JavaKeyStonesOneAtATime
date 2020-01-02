package leetcode.twopointer;


import leetcode.tag.level.Hard;
import leetcode.tag.type.Heap;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

/**

 239. Sliding Window Maximum

 Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

 Example:

 Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 Output: [3,3,5,5,6,7]
 Explanation:

 Window position                Max
 ---------------               -----
 [1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 Note:
 You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

 Follow up:
 Could you solve it in linear time?
 */

@Hard
@Heap
public class SlidingWindowMaxium {

	/**
	 * The priority queue solution is similar to the sliding window median solution, actually
	 */
	private PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b)-> b - a);
	public int[] maxSlidingWindowPQ(int[] nums, int k) {
		int n = nums.length;
		if (n <= 0) return new int[0];
		int[] result = new int[n - k + 1];

		for (int i = 0; i < nums.length; i++){
			addNum(nums[i]);
			if (i - k >= 0) {
				remove(nums[i - k]);
			}

			// deal with the extra position
			if (i - k + 1 >= 0) {
				result[i - k + 1] = maxHeap.peek();
			}
		}

		return result;
	}

	public void addNum(int num) {
		maxHeap.offer(num);
	}

	public void remove(int n) {
		maxHeap.remove(n);
	}

	/**
	 * Deque solution
	 * use dequeue to store the index and
	 */
	public static int[] maxSlidingWindowDQ(int[] nums, int k) {
		if(nums == null || k <= 0) return new int [0];
		int [] arr = new int[nums.length - k + 1];
		int in = 0;
		// use DQ to store the index
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i = 0; i < nums.length; i++){
			// move the index than is out of the k range
			while(!dq.isEmpty() && dq.peek() < i - k + 1) {
				dq.poll();
			}

			// only push the index that is larger than the next index.
			while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
				dq.pollLast();
			}

			// push the index
			dq.offer(i);

			// build the result
			if(i >= k - 1) {
				arr[in++] = nums[dq.peek()];
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] num = {1,3,-1,-3,5,3,6,7};

		maxSlidingWindowDQ(num, 3);
	}
}
