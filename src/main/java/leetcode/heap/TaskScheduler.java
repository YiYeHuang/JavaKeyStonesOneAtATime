package leetcode.heap;

import leetcode.tag.company.Facebook;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Greedy;
import leetcode.tag.type.Heap;

import java.util.*;

/**
 * 621. Task Scheduler
 *
 * Share
 * Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 *
 *
 * Example:
 *
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 *
 *
 * Note:
 *
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 */

@Facebook

@Medium
@Greedy
@Heap
public class TaskScheduler {

    /**
     * Question is to not make the same task char as close as possible
     * Since the input is from A to Z
     * problem is close to rearrange string K distance apart
     *
     * we need to know about the frequency and do sorting,
     * therefore, bucket sort + heap
     */
    public int leastInterval(char[] tasks, int n) {
        // build the frequency map
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1);
        }

        // Sort the frequency and put in queue
        // Larger frequency first, if same alphabetical order
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey()
        );

        // add the first one
        q.addAll(map.entrySet());

        int count = 0;
        while (!q.isEmpty()) {
            // this is the cpu task interval
            int k = n + 1;

            List<Map.Entry> templist = new ArrayList<>();
            while (k > 0 && !q.isEmpty()) {
                // get the current most frequent task
                Map.Entry<Character, Integer> top = q.poll();
                // simulate that the task is executed
                top.setValue(top.getValue() - 1);
                templist.add(top);
                k--;
                count++;
            }

            for (Map.Entry<Character, Integer> e : templist) {
                // collect task to add back to queue with new frequency
                if (e.getValue() > 0) q.add(e);
            }

            if (q.isEmpty()) break;
            count = count + k;
        }

        return count;
    }
}
