package leetcode.heap;
/*
int[][] A = new int[5][];

A[0] = new int[] { 1, 5, 8, 9 };
A[1] = new int[] { 2, 3, 7, 10 };
A[2] = new int[] { 4, 6, 11, 15 };
A[3] = new int[] { 9, 14, 16, 19 };
A[4] = new int[] { 2, 4, 6, 9 };

Output:
[1, 2, 2, 3, 4, 4, 5, 6, 6, 7, 8, 9, 9, 9, 10, 11, 14, 15, 16, 19]
 */
public class MergeKSortedArray {

    static class MinHeapNode {
        int value; // The element to be stored

        // index of the array from
        // which the element is taken
        int index;

        // index of the next element
        // to be picked from array
        int next;

        public MinHeapNode(int element, int index, int next) {
            this.value = element;
            this.index = index;
            this.next = next;
        }
    }

    static class MinHeap {
        MinHeapNode[] harr; // Array of elements in heap
        int heap_size; // Current number of elements in min heap

        // Constructor: Builds a heap from
        // a given array a[] of given size
        public MinHeap(MinHeapNode a[], int size) {
            heap_size = size;
            harr = a;
            int i = (heap_size - 1) / 2;
            while (i >= 0) {
                MinHeapify(i);
                i--;
            }
        }

        // A recursive method to heapify a subtree
        // with the root at given index This method
        // assumes that the subtrees are already heapified
        void MinHeapify(int i) {
            int l = left(i);
            int r = right(i);
            int smallest = i;
            if (l < heap_size && harr[l].value < harr[i].value)
                smallest = l;
            if (r < heap_size && harr[r].value < harr[smallest].value)
                smallest = r;
            if (smallest != i) {
                swap(harr, i, smallest);
                MinHeapify(smallest);
            }
        }

        // to get index of left child of node at index i
        int left(int i) {
            return (2 * i + 1);
        }

        // to get index of right child of node at index i
        int right(int i) {
            return (2 * i + 2);
        }

        // to get the root
        MinHeapNode getMin() {
            if (heap_size <= 0) {
                System.out.println("Heap underflow");
                return null;
            }
            return harr[0];
        }

        // to replace root with new node
        // "root" and heapify() new root
        void replaceMin(MinHeapNode root) {
            harr[0] = root;
            MinHeapify(0);
        }

        // A utility function to swap two min heap nodes
        void swap(MinHeapNode[] arr, int i, int j) {
            MinHeapNode temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    // A utility function to print array elements
    static void printArray(int[] arr) {
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }

    // This function takes an array of
    // arrays as an argument and All
    // arrays are assumed to be sorted.
    // It merges them together and
    // prints the final sorted output.
    public static void mergeKSortedArrays(int[][] arr, int k) {
        MinHeapNode[] hArr = new MinHeapNode[k];
        int totalArrayLength = 0;
        for (int i = 0; i < arr.length; i++) {
            MinHeapNode node = new MinHeapNode(arr[i][0], i, 1);
            hArr[i] = node;
            totalArrayLength += arr[i].length;
        }

        // Create a min heap with k heap nodes. Every heap node
        // has first element of an array
        MinHeap mh = new MinHeap(hArr, k);

        int[] result = new int[totalArrayLength];     // To store output array

        // Now one by one get the minimum element from min
        // heap and replace it with next element of its array
        for (int i = 0; i < totalArrayLength; i++) {

            // Get the minimum element and store it in result
            MinHeapNode root = mh.getMin();
            result[i] = root.value;

            // if next index is less than the current array length
            if (root.next < arr[root.index].length) {
                root.value = arr[root.index][root.next++];
            } else {
                root.value = Integer.MAX_VALUE;
            }

            // Replace root with next element of array
            mh.replaceMin(root);
        }

        printArray(result);
    }

}
