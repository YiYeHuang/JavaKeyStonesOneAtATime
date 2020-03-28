package leetcode.lineSweep;

import leetcode.tag.level.Medium;
import leetcode.tag.type.LineSweep;

import java.util.Arrays;

/*
1288. Remove Covered Intervals

Given a list of intervals, remove all intervals that are covered by another interval in the list.
Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.

After doing so, return the number of remaining intervals.

Example 1:

Input: intervals = [[1,4],[3,6],[2,8]]
Output: 2
Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.

Constraints:

1 <= intervals.length <= 1000
0 <= intervals[i][0] < intervals[i][1] <= 10^5
intervals[i] != intervals[j] for all i != j
 */

@Medium
@LineSweep
public class RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int result = 0;
        int[] currentRange = {-1, -1};
        for (int[] interval : intervals) {
            // if there is gap between interval
            if (interval[0] > currentRange[0] && interval[1] > currentRange[1]) {
                currentRange[0] = interval[0];
                result++;
            }

            if (interval[1] > currentRange[1]) {
                currentRange[1] = interval[1];
            }
        }

        return result;
    }
}
