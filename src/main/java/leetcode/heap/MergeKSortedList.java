package leetcode.heap;

import baseObj.ListNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/**
 23. Merge k Sorted Lists

 Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6
 */
@Amazon
@Facebook
@Google
@Microsoft
@Bloomberg

@Hard
@Heap

public class MergeKSortedList {

	/**
	 * Sort all node with min heap
	 *
	 * - min heap will always return next min,
	 * - while we keep polling node from queue, push next to queue as well for sorting
	 */
    public ListNode mergeKLists(ListNode[] lists) {
    	if (lists == null || lists.length == 0) return null;

    	// create min heap
		PriorityQueue<ListNode> queue = new PriorityQueue<>((x, y) -> x.val - y.val);

		// dummy head
		ListNode dummy = new ListNode(0);
		ListNode tail=dummy;

		// push all current node into queue, get sorted, from min -> max
		for (ListNode node:lists) {
			if (node!=null)
				queue.add(node);
		}

		// min heap will always return next min,
		// while we keep polling node from queue, push next to queue as well for sorting
		while(!queue.isEmpty()) {
			tail.next = queue.poll();
			tail=tail.next;

			if (tail.next != null) {
				queue.add(tail.next);
			}
		}

		return dummy.next;
    }

}
