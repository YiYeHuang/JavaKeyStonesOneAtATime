package leetcode.array;

/**
 * Rotate an array of n elements to the right by k steps.
 * 
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to
 * [5,6,7,1,2,3,4].
 * 
 * Note: Try to come up as many solutions as you can, there are at least 3
 * different ways to solve this problem.
 */
public class rotateArray
{
    public static void rotate(int[] nums, int k)
    {
        for (int i = 0; i <= k / 2; i++)
        {
            int temp = nums[i];
            nums[i] = nums[k - i];
            nums[k - i] = temp;
        }

        int counter = 0;
        for (int i = k + 1; i < (k + nums.length) / 2; i++)
        {
            int temp = nums[i];
            nums[i] = nums[nums.length - counter - 1];
            nums[nums.length - counter - 1] = temp;
            counter++;
        }

        for (int i = 0; i < nums.length / 2; i++)
        {
            int temp = nums[i];
            nums[i] = nums[nums.length - i - 1];
            nums[nums.length - i - 1] = temp;
        }
    }

    /**
     * [1,2,3,4,5,6,7] and k = 3, 
     * first we reverse [1,2,3,4] ---> [4,3,2,1]; 
     * then we reverse  [5,6,7] ---> [7,6,5], 
     * finally reverse the array as a whole, it becomes[4,3,2,1,7,6,5] ---> [5,6,7,1,2,3,4].
     */
    public static void rotateAnswer(int[] nums, int k)
    {
        if (nums == null || nums.length < 2)
        {
            return;
        }

        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private static void reverse(int[] nums, int i, int j)
    {
        int tmp = 0;
        while (i < j)
        {
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args)
    {
        int[] test =
        { 1, 2, 3, 4, 5, 6, 7 };
        rotateAnswer(test, 0);
    }
}
