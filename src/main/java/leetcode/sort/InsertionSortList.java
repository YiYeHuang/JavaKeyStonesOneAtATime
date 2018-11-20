package leetcode.sort;

import baseObj.ListNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.LinkedListTag;
import leetcode.tag.type.Sorting;

/**
 * Algorithm of Insertion Sort:
 *
 * Insertion sort iterates, consuming one input element each repetition, and growing a sorted output list.
 * At each iteration, insertion sort removes one element from the input data, finds the location it belongs within the sorted list, and inserts it there.
 * It repeats until no input elements remain.
 *
 * Example 1:
 *
 * Input: 4->2->1->3
 * Output: 1->2->3->4
 * Example 2:
 *
 * Input: -1->5->3->4->0
 * Output: -1->0->3->4->5
 */

@Medium
@Sorting
@LinkedListTag
public class InsertionSortList {

//    public ListNode insertionSortList(ListNode head) {
//
//    }

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

//    public static boolean insert(int value) {
//        if (size == testTarget.length) {
//            return false;
//        }
//
//        testTarget[next] = value;
//        for (int i = next; i > 0; i--) {
//            if (testTarget[i] < testTarget[i - 1]) {
//                int temp = testTarget[i - 1];
//                testTarget[i - 1] = testTarget[i];
//                testTarget[i] = temp;
//            } else {
//                break; // since all previous list are sorted
//            }
//        }
//
//        next++;
//        size++;
//        return true;
//    }
}
