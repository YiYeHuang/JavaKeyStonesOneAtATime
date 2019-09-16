package algorithm.search;

public class BinarySearch
{
    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param a
     *            the array of integers, must be sorted in ascending order
     * @param key
     *            the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(int[] a, int key)
    {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi)
        {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2; // do this to avoid overflow
            if (key < a[mid])
            {
                hi = mid - 1;
            }
            else if (key > a[mid])
            {

                lo = mid + 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int midIndex = low + (high - low)/2;

            if (nums[midIndex] < target) {
                low = midIndex + 1;
            } else if (nums[midIndex] > target) {
                high = midIndex - 1;
            } else {
                return midIndex;
            }
        }

        return -1;
    }

    /**
     *
     * Binary search recursion
     */
    public static boolean binarySearch(int[ ] data, int target, int low, int high) {
        if (low > high) {
            return false;
        } else {
            int mid = low + (high - low) / 2; // do this to avoid overflow
            if (target == data[mid])
                return true;
            else if (target < data[mid])
                return binarySearch(data, target, low, mid - 1);
            else
                return binarySearch(data, target, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] test = {-1,0,3,5,9,12};
        search(test, 9);
    }
}
