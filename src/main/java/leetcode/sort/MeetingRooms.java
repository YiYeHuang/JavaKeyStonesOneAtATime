package leetcode.sort;

import leetcode.tag.company.Amazon;
import leetcode.tag.company.Facebook;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Sorting;

import java.util.Arrays;

import com.sun.tracing.dtrace.FunctionAttributes;

/**
 252. Meeting Rooms

 Favorite

 Share
 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

 Example 1:

 Input: [[0,30],[5,10],[15,20]]
 Output: false
 Example 2:

 Input: [[7,10],[2,4]]
 Output: true
 */

@Amazon
@Facebook

@Easy
@Sorting
public class MeetingRooms {

	public class Interval {
		int start;
		int end;
		Interval() { start = 0; end = 0; }
		Interval(int s, int e) { start = s; end = e; }
	}

	public boolean canAttendMeetings(Interval[] intervals) {
		if (null == intervals) return false;

		// sort by start time
		Arrays.sort(intervals, (a, b) -> a.start - b.start);

		/**
		 * [0, 30], [5, 10], next start time needs to be later then end time
		 */
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < intervals[i -1].end) {
				return false;
			}
		}

		return true;
	}
}
