package leetcode.array;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length. Do not allocate extra space for
 * another array, you must do this in place with constant memory.
 * 
 * For example, Given input array nums = [1,1,2], Your function should return
 * length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 */
public class Remove_Duplicate_elementInSortedArray
{
    /**
     * this one is easy, 
     */
    public static int removeDuplicates(int[] nums)
    {
        int length_counter = 1;
        for (int i = 1; i < nums.length; i++)
        {
            if (nums[i] > nums[i-1])
            {
                length_counter++;
                nums[length_counter-1] = nums[i];
            }
        }

        return length_counter;
    }

    /**
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * For example,
     * Given sorted array nums = [1,1,1,2,2,3],
     * Your function should return length = 5, with the first 
     * five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
     */
    public static int removeDuplicatesAllow2(int[] nums)
    {
        int i = 0;
        for (int n : nums)
        {
            if (i < 2 || n > nums[i - 2])
            {
                nums[i++] = n;
            }
        }

        return i;
    }
    
    public static void main(String[] args)
    {
        int[] test = {1,1,1,2,2,3};
        //System.out.println(removeDuplicates(test));
        System.out.println((0+3)/2);
    }
}
