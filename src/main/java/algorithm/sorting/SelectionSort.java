package algorithm.sorting;

public class SelectionSort {

    /**
     * Best: O(n^2) always need to find the smallest
     * Worst: O(n^2) always need to find the smallest
     * Average: O(n^2)
     * Space: O(1) in place
     * Stable: No
     *
     * Always to find the smallest in the remaining list, and swap with the current top.
     */
    public static void selectionSort(int[] a) {

        int length = a.length;

        for (int i = 0; i < length - 1; ++i) {

            int minIndex = i;
            for (int j = i + 1; j < length; ++j) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            // swap to the top
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] test = {4,5,6,3,2,1};

        selectionSort(test);
    }
}
