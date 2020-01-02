package leetcode.greedy;

import baseObj.Interval;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Greedy;
import leetcode.tag.type.Heap;
import leetcode.tag.type.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253. Meeting Rooms II

 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1

 */

@Medium
@Heap
@Greedy
@Sorting
public class MeetingRoomII {
	public int minMeetingRooms(Interval[] intervals) {
		if (null == intervals || intervals.length == 0) return 0;

		// sort by start time
		Arrays.sort(intervals, (a, b)-> a.start - b.start);

		// min queue: the meeting that ends earliest will be at the top
		PriorityQueue<Integer> room = new PriorityQueue<>((a, b) -> a - b);

		room.add(intervals[0].end);
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start >= room.peek()) {
				room.poll();
			}

			// whenever there is a new interval, it needs to take a room, either the old room or new one, always add
			room.add(intervals[i].end);
		}

		return room.size();
	}
}
