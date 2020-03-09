package algorithm.divideCon;

import java.util.Arrays;

public class QuickSortPivot {
  // Function to count the number of inversions
  // during the merge process
  private static long mergeAndCount(int[] arr, int l, int m, int r)
  {

    // Left subarray
    int[] left = Arrays.copyOfRange(arr, l, m + 1);

    // Right subarray
    int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

    int i = 0, j = 0, k = l;
    long swaps = 0;

    while (i < left.length && j < right.length) {
      if (left[i] <= right[j])
        arr[k++] = left[i++];
      else {
        arr[k++] = right[j++];
        swaps += (m + 1) - (l + i);
      }
    }

    // Fill from the rest of the left subarray
    while (i < left.length)
      arr[k++] = left[i++];

    // Fill from the rest of the right subarray
    while (j < right.length)
      arr[k++] = right[j++];

    return swaps;
  }

  // Merge sort function
  private static long mergeSortAndCount(int[] arr, int l, int r)
  {

    // Keeps track of the inversion count at a
    // particular node of the recursion tree
    long count = 0;

    if (l < r) {
      int m = (l + r) / 2;

      // Total inversion count = left subarray count
      // + right subarray count + merge count

      // Left subarray count
      count += mergeSortAndCount(arr, l, m);

      // Right subarray count
      count += mergeSortAndCount(arr, m + 1, r);

      // Merge count
      count += mergeAndCount(arr, l, m, r);
    }

    return count;
  }

  private static long compare = 0;
  public static void quickSort(int[] a, int n) {
    quickSortInternally(a, 0, n-1);
  }

  private static void quickSortInternally(int[] arr, int start, int end) {
    if (start >= end) {
      return;
    }

    int pivotPointIndex = partition(arr, start, end, medianOfThree(arr, start, end));

    quickSortInternally(arr, start, pivotPointIndex -1);
    quickSortInternally(arr, pivotPointIndex + 1, end);
  }

  private static int medianOfThree(int[] array, int left, int right) {
    int length = right - left + 1;
    int mid = (length % 2) == 0 ? ((length / 2) - 1) + left : (length / 2) + left;
    int first = array[left], middle = array[mid], last = array[right];
    if ((middle < first && first < last) || (middle > first && first > last))
      return left;
    if ((first < middle && middle < last) || (first > middle && middle > last))
      return mid;
    return right;
  }

  /**
   * Choose the pivot at the end:
   *
   * item smaller than pivot, swap with the marking point
   * marking point move up
   *
   * item larger than pivot, marking point stay, move to next item
   *
   * Swap the final marking point with pivot value
   *
   */
  private static int partition(int[] arr, int start , int endPivot, int piv) {
    int pivot = arr[endPivot];
    System.out.println("pivot value =" + pivot);
    int i = start;
    compare += endPivot - start;
    for(int j = start; j < endPivot; ++j) {
      if (arr[j] < pivot) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        ++i;
      }
    }

    int tmp = arr[i];
    arr[i] = arr[endPivot];
    arr[endPivot] = tmp;

    System.out.println("pivot point =" + i);
    return i;
  }
}
