package leetcode.heap;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Heap;
import leetcode.tag.type.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 215. Kth Largest Element in an Array

 Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

 Example 1:

 Input: [3,2,1,5,6,4] and k = 2
 Output: 5
 Example 2:

 Input: [3,2,3,1,2,4,5,5,6] and k = 4
 Output: 4
 Note:
 You may assume k is always valid, 1 ≤ k ≤ array's length.
 */

@Facebook
@Amazon
@Google
@Microsoft

@Medium
@Heap
@Sorting
public class KthLargestElementInArray {

	/**
	 * no brainer is sorting, but usually solve kth problem with heap
	 *
	 * so to get kth largest, means there are n - k larger than that, so use min heap
	 *
	 * - push to queue
	 * - keep popping out the smallest number outside size k
	 * - since every time you pop the smallest item, the remaining k elements heap, the top one is kth largest
	 *
	 * Space O(k)
	 * Com O((n - k) * logk)
	 */
	public static int findKthLargest(int[] nums, int k) {
		PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> x - y);

		for (int n : nums) {
			queue.add(n);
			if (queue.size() > k) {
				int a = queue.poll();
			}
		}

		return queue.poll();
	}

	/**
	 * Space O(k)
	 * Com O((n - k) * logk)
	 */
	public int findKthLargestSort(int[] nums, int k) {
		final int N = nums.length;
		Arrays.sort(nums);
		return nums[N - k];
	}

	/**
	 * binary search + quick sort
	 *
	 * - quick sort is all about choose pivot and put all item less than pivot to left and larger to right
	 * - Choose one side and continue do sorting
	 *
	 * ======================================================================
	 */
	public static int findKthLargestQuickSort(int[] a, int k) {
		int n = a.length;
		int p = quickSelect(a, 0, n - 1, n - k + 1);
		return a[p];
	}

	// return the index of the kth smallest number
	private static int quickSelect(int[] a, int lo, int hi, int k) {
		// use quick sort's idea
		// put nums that are <= pivot to the left
		// put nums that are  > pivot to the right
		int i = lo, j = hi, pivot = a[hi];
		while (i < j) {
			if (a[i] > pivot) {
				i++;
				swap(a, --i, --j);
			}
		}
		swap(a, i, hi);

		// count the nums that are <= pivot from lo
		int m = i - lo + 1;

		// pivot is the one!
		if (m == k) {
			return i;
		}
			// pivot is too big, so it must be on the left
		else if (m > k) {
			return quickSelect(a, lo, i - 1, k);
		} else {
			// pivot is too small, so it must be on the right
			return quickSelect(a, i + 1, hi, k - m);
		}
	}

	static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public static void main(String[] args)
	{
		int[] test = {3,2,1,5,6,4};
	}

}
