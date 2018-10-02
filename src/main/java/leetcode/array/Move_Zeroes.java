package leetcode.array;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums
 * should be [1, 3, 12, 0, 0].
 */
public class Move_Zeroes
{
    public static void moveZeroes(int[] nums)
    {
        int moves = 0;
        for (int i = 0;  i < nums.length; i++)
        {
            if (nums[i] != 0)
            {
                int temp = nums[moves];
                nums[moves] = nums[i];
                nums[i] = temp;
                moves ++;
            }
        }
    }

    public static void main(String[] args)
    {
        int[] test = {0, 1, 0, 3, 12};
        moveZeroes(test);
    }
}
