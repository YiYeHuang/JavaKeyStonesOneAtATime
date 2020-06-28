package leetcode.lineSweep;

import leetcode.tag.level.Medium;
import leetcode.tag.type.LineSweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1229 Meeting Scheduler
Given available slots array slot1 slot2 of two people and meeting duration, returen the earliest time slot that
works for both

Example 1:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]

Example 2:

Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []
 */

@LineSweep
@Medium
public class MeetingSchedule {

    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        if (slots1.length == 0 || slots2.length == 0) return new ArrayList<>();

        Arrays.sort(slots1, (a,b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a,b) -> a[0] - b[0]);

        List<Integer> result = new ArrayList<>();

        int slot1Index = 0;
        int slot2Index = 0;

        int start = 0;
        int end  = 1;

        while (slot1Index < slots1.length && slot2Index < slots2.length) {

            // the meeting interval is between the later start and earlier end
            // then compare the diff with the duration
            int meetingCanStart = Math.max(slots1[slot1Index][start], slots2[slot2Index][start]);
            int meetingCanEnd = Math.min(slots1[slot1Index][end], slots2[slot2Index][end]);

            // case success, add to result
            if (meetingCanEnd - meetingCanStart >= duration) {
                result.add(meetingCanStart);
                result.add(meetingCanStart + duration);
                return result;
            } else {
                // case failed, the user slot end earlier will move to next slot
                // earlier end user search for the next slot
                if (slots1[slot1Index][end] < slots2[slot2Index][end]) {
                    slot1Index++;
                } else {
                    slot2Index++;
                }
            }
        }
        return new ArrayList<>();
    }
}
