package leetcode.sort;

import baseObj.Interval;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 56. Merge Intervals

 Given a collection of intervals, merge all overlapping intervals.

 Example 1:

 Input: [[1,3],[2,6],[8,10],[15,18]]
 Output: [[1,6],[8,10],[15,18]]
 Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 Example 2:

 Input: [[1,4],[4,5]]
 Output: [[1,5]]
 Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

@Facebook
@Google
@Bloomberg
@Microsoft
@LinkedIn
@Amazon

@Medium
@Sorting
public class MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
		if (null == intervals || intervals.size() == 1) return new ArrayList<>();

		// sort by start time
		Collections.sort(intervals, (a, b)-> a.start - b.start);

		/**
		 * forward compare, compare the previous end to next start
		 */
		List<Interval> result = new LinkedList<Interval>();
		Interval currentStart = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval current = intervals.get(i);
			if (current.start <= currentStart.end) {
				currentStart.end = Math.max(current.end, currentStart.end);
			} else {
				result.add(currentStart);
				currentStart = current;
			}
		}
		// make up
		if (!result.contains(currentStart))
			result.add(currentStart);

		return result;
	}
}
