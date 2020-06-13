package leetcode.heap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import leetcode.tag.type.Heap;

/*

Examples:

Input:

[ 3, -1, 2, 6, 4, 5 , 8 ]
k = 2

Each number is no more than 2 indices from its final sorted position.

Output:
[ -1, 2, 3, 4, 5, 6, 8 ]

It is often that data finds itself in an almost sorted state.

For example: A server needs to sort orders coming in internationally and due to differences
in server loads and network routes some earlier orders come in after later orders.

How do we efficiently sort this almost sorted data?

Whenever I hear or think of sorting or searching,
I think of my fast sorting algorithms, binary search, and min/max heaps.
 */


@Heap
public class SortAlmostSortedArrayKApart {

  public static void kSort_heap(int[] arr, int k) {

    // default is a min heap;
    PriorityQueue<Integer> heap = new PriorityQueue<>();

    //  0 ~ k -> k + 1 element
    for (int i = 0; i <= k; k++) {
      heap.add(arr[i]);
    }

    int index = 0;
    // pop and insert if arr.length > k
    for (int i = k+1; i < arr.length; i++) {
      arr[index] = heap.poll();
      heap.add(arr[i]);
      index++;
    }

    //
    Iterator<Integer> heapItr = heap.iterator();
    while(heapItr.hasNext()) {
      arr[index] = heap.poll();
      index++;
    }
  }

  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    list.add(1);
    list.add(2);
    list.add(3);
    list.add(5);
    list.add(4);
    list.add(6);

    for (int i = 0; i < list.size(); i++){
      list.remove(0);
    }
  }
}
