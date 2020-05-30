package leetcode.lineSweep;

import leetcode.tag.level.Medium;
import leetcode.tag.type.LineSweep;

import java.util.ArrayList;
import java.util.List;

/*
LeetCode 1272. Remove Interval

Given a sorted list of disjoint intervals,
each interval intervals[i] = [a, b] represents the set of real numbers x such that a <= x < b.

We remove the intersections between any interval in intervals and the interval toBeRemoved.

Return a sorted list of intervals after all such removals.

Example 1:

Input: intervals = [[0,2],[3,4],[5,7]], toBeRemoved = [1,6]
Output: [[0,1],[6,7]]
Example 2:

Input: intervals = [[0,5]], toBeRemoved = [2,3]
Output: [[0,2],[3,5]]
Constraints:

1 <= intervals.length <= 10^4
-10^9 <= intervals[i][0] < intervals[i][1] <= 10^9
Solution: Geometry
Time complexity: O(n)
Space complexity: O(n)
 */

@LineSweep
@Medium
public class RemoveInterval {
    public List<int[]> removeInterval(List<int[]> intervals, int[] remove) {

        int start = 0;
        int end = 1;

        List<int[]> result = new ArrayList<>();

        for (int[] interval : intervals) {
            // does not interact with remove interval
            if (interval[end] <= remove[start] || interval[start] >= remove[end]) {
                result.add(interval);
            } else {
                // left side of interval is outside remove interval
                if (interval[start] < remove[start]) {
                    interval[end] = remove[start];
                    result.add(interval);
                }

                // right side of interval is outside remove interval
                if (interval[end] > remove[end]) {
                    interval[start] = remove[end];
                    result.add(interval);
                }
                // otherwise the entire interval is inside
                // not add
            }
        }
        return result;
    }
}
