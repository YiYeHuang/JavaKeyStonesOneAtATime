package leetcode.twopointer;

import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

/*
Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.


The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.

Example:

Input: [1,8,6,2,5,4,8,3,7]
Output: 49
 */
@Medium
@TwoPointer
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea= 0;

        // Need to go all over the list to avoid case like
        // [1,8,6,2,11111,11111,8,3,7]
        while (left < right) {
            maxArea = Math.max(maxArea, Math.min(height[left], height[right]) * (right - left));

            // before move, maxArea = height[left] * (right - left)
            // when move by 1, right - left is reduce by 1, but left is at least increase by 1, so can not be smaller
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

}
