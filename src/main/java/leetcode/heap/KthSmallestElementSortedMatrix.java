package leetcode.heap;


import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/**
 *
 378. Kth Smallest Element in a Sorted Matrix

 Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

 Note that it is the kth smallest element in the sorted order, not the kth distinct element.

 Example:

 matrix = [
 [ 1,  5,  9],
 [10, 11, 13],
 [12, 13, 15]
 ],
 k = 8,

 return 13.
 */

@Medium
@BinarySearch
@Heap
public class KthSmallestElementSortedMatrix {
	static class  Pair{
		int x, y, val;
		public Pair (int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}

	}

	public static int kthSmallestheap(int[][] matrix, int k) {
		int n = matrix.length;
		PriorityQueue<Pair> heap = new PriorityQueue<Pair>((a, b) -> a.val - b.val);

		// insert the first row into the heap
		for (int j = 0; j < n; j++) {
			heap.offer(new Pair(0, j, matrix[0][j]));
		}
		// move down the row and insert into the heap
		for(int i = 0; i < k-1; i++) {
			Pair t = heap.poll();

			if(t.x == n-1) continue;

			heap.offer(new Pair(t.x+1, t.y, matrix[t.x+1][t.y]));
		}
		return heap.poll().val;
	}

	public static int kthSmallestBinarySearch(int[][] matrix, int k) {
		int lo = matrix[0][0], hi = matrix[matrix.length - 1][matrix[0].length - 1] + 1;
		while(lo < hi) {
			int mid = lo + (hi - lo) / 2;
			int count = 0;

			int j = matrix[0].length - 1;
			for(int i = 0; i < matrix.length; i++) {
				while(j >= 0 && matrix[i][j] > mid) {
					j--;
				}
				count += (j + 1);
			}

			if(count < k) {
				lo = mid + 1;
			} else {
				hi = mid;
			}
		}
		return lo;
	}

	public static void main(String[] args) {
		int[][] test = { {1,  5,  9}, {10, 11, 13}, {12, 13, 15} };
		kthSmallestheap(test, 8);
	}
}
