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

    public List<Integer> minAvailableDuration(int[][] slot1, int[][] slot2, int duration) {
        if (slot1.length == 0 || slot2.length == 0) return new ArrayList<>();

        Arrays.sort(slot1, (a,b) -> a[0] - b[0]);
        Arrays.sort(slot2, (a,b) -> a[0] - b[0]);

        List<Integer> result = new ArrayList<>();

        int slot1Index = 0;
        int slot2Index = 0;

        int start = 0;
        int end  = 1;

        while (slot1Index < slot1.length && slot2Index < slot2.length) {

            // the meeting interval is between the later start and earlier end
            int meetingCanStart = Math.max(slot1[slot1Index][start], slot2[slot2Index][start]);
            int meetingCanEnd = Math.min(slot1[slot1Index][end], slot2[slot2Index][end]);

            if (meetingCanEnd - meetingCanStart >= duration) {
                result.add(meetingCanStart);
                result.add(meetingCanStart + duration);
                return result;
            } else {
                // earlier end user search for the next slot
                if (slot1[slot1Index][end] == meetingCanEnd) {
                    slot1Index++;
                } else {
                    slot2Index++;
                }
            }
        }
        return new ArrayList<>();
    }
}
