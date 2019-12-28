package leetcode.binarySearch;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BinarySearch;
import leetcode.tag.type.TwoPointer;

/**
 240. Search a 2D Matrix II

 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 Example:

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.
 */

@Amazon

@Medium
@BinarySearch
@TwoPointer
public class Search2DMatrixII {

	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

		int row = 0;
		int col = matrix[0].length - 1;

		while (row < matrix.length && col >= 0) {
			int value = matrix[row][col];
			if (target == value) {
				return true;
			} else if (target < value) {
				col --;
			} else {
				row ++;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int[][] test = {
				{1,   4,  7, 11, 15},
				{2,   5,  8, 12, 19},
				{3,   6,  9, 16, 22},
				{10, 13, 14, 17, 24},
				{18, 21, 23, 26, 30}};
		searchMatrix(test, 20);
	}

}
