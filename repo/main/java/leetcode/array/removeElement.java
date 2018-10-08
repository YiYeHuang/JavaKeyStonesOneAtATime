package leetcode.array;

/**
 * Given an array and a value, remove all instances of that value in place and
 * return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * The order of elements can be changed. It doesn't matter what you leave beyond
 * the new length.
 * 
 * Example: Given input array nums = [3,2,2,3], val = 3
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 2.
 */
public class removeElement
{
    /**
     * The basic idea is when elem is found at index i, let A[i] = the last
     * element in the modifying array, then repeat searching until elem is not
     * found.
     * 
     * the way I did is not consider "It doesn't matter what you leave beyond the new length."
     */
    public static int removeElement(int[] A, int elem)
    {
        int len = A.length;
        for (int i = 0; i < len; ++i)
        {
            while (A[i] == elem && i < len)
            {
                A[i] = A[--len];
            }
        }
        return len;
    }

    public static void main(String[] args)
    {
        int[] test =
        { 3, 3, 3, 3, 1, 2 };
        System.out.println(removeElement(test, 3));
    }
}
