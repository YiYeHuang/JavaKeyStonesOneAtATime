package algorithm.sorting;

public class MergeSort
{

    void mergeSort(int arr[]) {
        split(arr, 0, arr.length - 1);
    }

    /**
     * Best: O(nlog(n))
     * Worst: O(nlog(n))
     * Average: O(nlog(n))
     * Space: O(nlog(n)) extra space when merge
     * Stable: yes
     *
     *
     * Like QuickSort, Merge Sort is a Divide and Conquer algorithm.
     * It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves. The merge()
     * function is used for merging two halves. The merge(arr, l, m, r) is key process that assumes that
     * arr[l..m] and arr[m+1..r] are sorted and merges the two sorted sub-arrays into one. See following
     *
     *
     * merge_sort(p...r) = merge(merge_sort(p...q), merge_sort(q+1...r))
     *
     * divide and concur, divide and do bottom up
     *
     * T(n)=Cn+nlog2n
     */
    // Main function that sorts arr[l..r] using
    // until the array size is down to 2
    private void split(int arr[], int start, int end)
    {
        if (start >= end) {
            return;
        } else {
            // Find the middle point
            int mid = (start + end) / 2;

            // Sort left
            split(arr, start, mid);
            // Sort right
            split(arr, mid + 1, end);

            // Merge the sorted halves
            merge(arr, start, mid, end);
        }
    }

    private void merge(int arr[], int start, int mid, int end)
    {
        // Merges two subarrays of arr[].
        // First subarray is arr[l..m]
        // Second subarray is arr[m+1..r]

        // Find sizes of two subarrays to be merged
        int leftSize = mid - start + 1;
        int rightSize = end - mid;

        /* Create temp arrays */
        int L[] = new int[leftSize];
        int R[] = new int[rightSize];

        /* Copy data to temp arrays */
        for (int i = 0; i < leftSize; ++i)
            L[i] = arr[start + i];
        for (int j = 0; j < rightSize; ++j)
            R[j] = arr[mid + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int currentWalker = start;
        while (i < leftSize && j < rightSize)
        {
            if (L[i] <= R[j])
            {
                arr[currentWalker] = L[i];
                i++;
            }
            else
            {
                arr[currentWalker] = R[j];
                j++;
            }
            currentWalker++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < leftSize)
        {
            arr[currentWalker] = L[i];
            i++;
            currentWalker++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < rightSize)
        {
            arr[currentWalker] = R[j];
            j++;
            currentWalker++;
        }
    }



    /* A utility function to print array of size n */
    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method
    public static void main(String args[])
    {
        int arr[] =
        {12, 11, 13, 5, 6, 7 };

        System.out.println("Given ArrayTag");
        printArray(arr);

        MergeSort ob = new MergeSort();
        ob.mergeSort(arr);

        System.out.println("\nSorted array");
        printArray(arr);
    }
}
