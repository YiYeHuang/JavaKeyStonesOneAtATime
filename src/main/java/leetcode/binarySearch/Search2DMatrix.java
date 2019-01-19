package leetcode.binarySearch;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;

/**
 74. Search a 2D Matrix

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 Example 1:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 Output: true
 Example 2:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 Output: false

 */
@Microsoft
@Amazon
@Facebook
@Google

@Medium
@BinarySearch
public class Search2DMatrix {

	/**
	 * 0   1   2
	 * 3   4   5   --> 0 1 2 3 4 5 6 7 8
	 * 6   7   8
	 *
	 * so the begin is 0 and end is 8
	 * the first mid is 4, but mapping to index is (1, 1)
	 * if larger than the next mid is 6, mapping to index (2, 0)
	 * to the mid value is matrix[mid/col][mid%col];
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		// error case
		if (matrix.length == 0 || matrix[0].length == 0) return false;

		int row = matrix.length;
		int col = matrix[0].length;

		int begin = 0, end = row * col - 1;

		while (begin <= end) {
			int mid = begin + (end - begin) /2 ;
			int mid_value = matrix[mid/col][mid%col];

			if (mid_value == target) {
				return true;
			} else if (mid_value < target) {
				begin = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return false;
	}
}
