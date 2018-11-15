package algorithm.sorting;

public class BubbleSort {

    /**
     * Best: O(n) all sorted
     * Worst: O(n^2) all reverse sorted
     * Average: O(n^2)
     * Space: O(1) in place
     * Stable: Yes
     *
     * For each iteration, make sure the current largest number is at it's correct location.
     * After each iteration, the length to check get shortened for one unit
     *
     * If no swap in a iteration, mean all numbers are sorted, break the loop
     */
    public static void bubbleSort(int[] a) {
        int length = a.length;

        for (int i = 0; i < length; ++i) {

            boolean flag = false;
            for (int j = 0; j < length - i - 1; ++j) {
                if (a[j] > a[j+1]) {
                    // swap
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;

                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] test = {4,5,6,3,2,1};

        bubbleSort(test);
    }

}
