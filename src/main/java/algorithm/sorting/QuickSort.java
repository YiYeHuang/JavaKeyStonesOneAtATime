package algorithm.sorting;

public class QuickSort {


    /**
     * Best: O(nlog(n))
     * Worst: O(n^2) when pivot point choice is really against the order
     * Average: O(nlog(n))
     * Space: O(1) in place
     * Stable: yes
     *
     * Choice of pivot point is the key
     */
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n-1);
    }

    private static void quickSortInternally(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotPointIndex = partition(arr, start, end);

        quickSortInternally(arr, start, pivotPointIndex -1);
        quickSortInternally(arr, pivotPointIndex + 1, end);
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
    private static int partition(int[] arr, int start , int endPivot) {
        int pivot = arr[endPivot];
        int i = start;
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

    public static void main(String[] args) {
        int[] test = {6, 11, 3, 9, 8};
        quickSort(test, test.length);
    }
}
