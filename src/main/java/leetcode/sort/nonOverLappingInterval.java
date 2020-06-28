package leetcode.sort;

import leetcode.basicDto.Interval;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Greedy;
import leetcode.tag.type.Sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
435. Non-overlapping Intervals

Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.



Example 1:

Input: [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:

Input: [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:

Input: [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Note:

You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */

@Sorting
@Medium
@Greedy
public class nonOverLappingInterval {

    // sort by end, get the most intervals that are not overlapping
    public static int eraseOverlapIntervals(int[][] intervals) {

        if (intervals == null || intervals.length == 0) return 0;
        List<Interval> intervalList = new ArrayList<>();
        for (int[] interval : intervals) {
            intervalList.add(new Interval(interval[0], interval[1]));
        }

        Collections.sort(intervalList, (a, b)-> a.start == b.start? a.end - b.end : a.start - b.start);

        Interval currentStart = intervalList.get(0);
        int result = 0;
        for (int i = 1; i < intervalList.size(); i++) {
            Interval current = intervalList.get(i);
            if (current.start < currentStart.end) {
                // compare tow end time and update
                result++;
            } else {
                currentStart = current;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test = {{1,2}, {2, 3}, {1,3}, {3,4}};
        eraseOverlapIntervals(test);
    }

}
