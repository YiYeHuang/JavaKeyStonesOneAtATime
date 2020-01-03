package leetcode.twopointer;

import leetcode.tag.level.Hard;
import leetcode.tag.type.TwoPointer;
/*
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

 */

@Hard
@TwoPointer
public class TappingRainWater {

    public int trap(int[] height) {

        if (height == null || height.length == 0) {
            return 0;
        }

        int result = 0;
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;

        int left = 0;
        int right = height.length - 1;

        // question is very close to 11, container with most water
        // we assuming nothing blocks between left most and right most
        // the water always bound by the lower bar, water will not overflow from higher
        // so always go to the higher bar
        // count the diff of each index, if leftMax or rightMax refreshed, that position will accumulate 0 volume of water
        // meet at middle
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                result += leftMax - height[left];
                left++;
            } else {
                result += rightMax - height[right];
                right--;
            }
        }

        return result;
    }

}
