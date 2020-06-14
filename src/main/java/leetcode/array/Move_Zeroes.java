package leetcode.array;

import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

/*
283. Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */

@TwoPointer
@Medium
public class Move_Zeroes
{
    public static void moveZeroes_alwaysSwap(int[] nums)
    {
        int moves = 0;
        for (int i = 0;  i < nums.length; i++)
        {
            if (nums[i] != 0)
            {
                int temp = nums[moves];
                nums[moves] = nums[i];
                nums[i] = temp;
                moves++;
            }
        }
    }

    public static void moveZeroes_alwasyAssign(int[] nums)
    {
        int moves = 0;
        for (int i = 0;  i < nums.length; i++)
        {
            if (nums[i] != 0)
            {
                nums[moves] = nums[i];
                moves++;
            }
        }

        for (int i = moves; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args)
    {
        int[] test = {0, 1, 0, 3, 12};
        moveZeroes_alwaysSwap(test);
    }
}
