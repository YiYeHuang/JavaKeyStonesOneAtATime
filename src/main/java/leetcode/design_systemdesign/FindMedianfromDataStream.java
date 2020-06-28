package leetcode.design_systemdesign;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import leetcode.tag.level.Hard;
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

@Hard
@Heap
public class FindMedianfromDataStream {

	/**
	 * 		20    21    22   35
	 *       \    /     \    /
	 *         18         19
	 *          \        /
	 *              15        min
	 *
	 *              14        max
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
	//
	// 0: max add -> push to min    -> median get from min
	// 1: min add -> push to max    -> median get from min + max /2
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
		System.out.println(fm.findMedian());
		fm.addNum(2);
		fm.addNum(3);

		System.out.println(fm.findMedian());
	}

}
