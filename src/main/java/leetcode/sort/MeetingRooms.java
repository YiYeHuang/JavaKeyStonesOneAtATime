package leetcode.sort;

import baseObj.Interval;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Sorting;

import java.util.Arrays;

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
@Easy
@Sorting
public class MeetingRooms {

	public boolean canAttendMeetings(Interval[] intervals) {
		if (null == intervals) return false;

		// sort by start time
		Arrays.sort(intervals, (a, b) -> a.start - b.start);

		/**
		 * [0, 30], [5, 10], next start time needs to be later then end time
		 * backward compare, compare next start to previous end
		 */
		for (int i = 1; i < intervals.length; i++) {
			if (intervals[i].start < intervals[i -1].end) {
				return false;
			}
		}

		return true;
	}
}
