package algorithm.sorting;

/**
 *
 */
public class InsertionSort {

    private static int[] testTarget = new int[10];
    private static int size = 0;
    private static int next = 0;

    /**
     * Best: O(n) insert in order
     * Worst: O(n^2) all reverse sorted
     * Average: O(n^2)
     * Space: O(1) in place
     * Stable: Yes
     *
     * Single action: insert an item to a sorted list.
     *
     * Take each next item to the previous existing sorted list.
     */
    public static void insertionSort(int[] a) {
        int length = a.length;

        for (int i = 1; i < length; ++i) {
            int insertValue = a[i];
            int j = i - 1;

            for (; j >= 0; --j) {
                if (a[j] > insertValue) {
                    a[j+1] = a[j];
                } else {
                    break; // since all previous list are sorted
                }
            }
            a[j+1] = insertValue;
        }
    }

    public static boolean insert(int value) {
        if (size == testTarget.length) {
            return false;
        }

        testTarget[next] = value;
        for (int i = next; i > 0; i--) {
            if (testTarget[i] < testTarget[i - 1]) {
                int temp = testTarget[i - 1];
                testTarget[i - 1] = testTarget[i];
                testTarget[i] = temp;
            } else {
                break; // since all previous list are sorted
            }
        }

        next++;
        size++;
        return true;
    }

    public static void main(String[] args) {
        insert(10);
        insert(1);
        insert(5);
        insert(3);
        insert(4);

        int[] test = {4,5,6,3,2,1};

        insertionSort(test);
    }

}
