package leetcode.array;

/**
 * You may assume that nums1 has enough space (size that is greater or equal to
 * m + n) to hold additional elements from nums2. The number of elements
 * initialized in nums1 and nums2 are m and n respectively.
 * 
 *
 */
public class mergeTwoSortedArrayInplace
{
    /**
     * work backwards merge, 
     * since array 1 is bigger than array 2, when array 2 is finished, array 1 dont have to move
     * check if array 2 is empty only.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n)
    {
        int nums1Ptr = m - 1;
        int nums2Ptr = n - 1;
        int resultPtr = m + n - 1;

        while (nums1Ptr > -1 && nums2Ptr > -1)
        {
            nums1[resultPtr--] = (nums1[nums1Ptr] > nums2[nums2Ptr]) ? nums1[nums1Ptr--] : nums2[nums2Ptr--];
        }
        while (nums2Ptr > -1)
        {
            nums1[resultPtr--] = nums2[nums2Ptr--];
        }
    }
}
