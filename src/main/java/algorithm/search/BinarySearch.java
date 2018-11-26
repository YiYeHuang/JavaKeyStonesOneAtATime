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
}
