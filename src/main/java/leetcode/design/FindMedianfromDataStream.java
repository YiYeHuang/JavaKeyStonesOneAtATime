package leetcode.design;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Apple;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Hash;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/**
 295. Find Median from Data Stream

 Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

 For example,
 [2,3,4], the median is 3

 [2,3], the median is (2 + 3) / 2 = 2.5

 Design a data structure that supports the following two operations:

 void addNum(int num) - Add a integer number from the data stream to the data structure.
 double findMedian() - Return the median of all elements so far.


 Example:

 addNum(1)
 addNum(2)
 findMedian() -> 1.5
 addNum(3)
 findMedian() -> 2
 */

@Amazon
@Apple
@Google
@Bloomberg
@Microsoft

@Hard
@Heap
public class FindMedianfromDataStream {

	/**
	 * 		20    21    22   35
	 *       \    /     \    /
	 *         18         19
	 *          \        /
	 *              15
	 *
	 *              14
	 *            /    \
	 *          10     11
	 *        /  \     /  \
	 *      4    5    9    1
	 *
	 *
	 */
	// length always n/2
	private PriorityQueue<Integer> minHeap;
	// length always n/2 or n/2 + 1
	private PriorityQueue<Integer> maxHeap;
	private boolean even = true;

	/** initialize your data structure here. */
	public FindMedianfromDataStream() {
		minHeap = new PriorityQueue<Integer>((a, b)-> a - b);
		maxHeap = new PriorityQueue<Integer>((a, b)-> b - a);
	}

	// EVEN: min heap get value
	// ODD: max heap get value
	public void addNum(int num) {
		if (even) {
			maxHeap.offer(num);
			minHeap.offer(maxHeap.poll());
		} else {
			minHeap.offer(num);
			maxHeap.offer(minHeap.poll());
		}
		even = !even;
	}

	public double findMedian() {
		if (even)
			return (minHeap.peek() + maxHeap.peek()) / 2.0;
		else
			return minHeap.peek();
	}

	public static void main(String[] args) {
		FindMedianfromDataStream fm = new FindMedianfromDataStream();
		fm.addNum(1);
		fm.addNum(2);
		fm.addNum(3);

		System.out.println(fm.findMedian());
	}
}
