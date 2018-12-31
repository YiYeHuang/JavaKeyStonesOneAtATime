package leetcode.greedy;

import java.util.*;

/**
 * 358. Rearrange String k Distance Apart

 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 *
 * Input: s = "aabbcc", k = 3
 * Output: "abcabc"
 * Explanation: The same letters are at least distance 3 from each other.
 * Example 2:
 *
 * Input: s = "aaabc", k = 3
 * Output: ""
 * Explanation: It is not possible to rearrange the string.
 * Example 3:
 *
 * Input: s = "aaadbbcc", k = 2
 * Output: "abacabcd"
 * Explanation: The same letters are at least distance 2 from each other.
 */
public class RearrangeStringKDistanceApart {

    /**
     * Put the each character in different frequency and sort the frequency
     */
    public static String rearrangeString(String s, int k) {
        // build the frequency map
        Map<Character, Integer> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // Sort the frequency and put in queue
        // Larger frequency first, if same alphabetical order
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey()
        );

        // add the first one
        heap.addAll(map.entrySet());

        StringBuilder result = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();

        while (!heap.isEmpty()) {

            Map.Entry<Character, Integer> current = heap.poll();
            result.append(current.getKey());
            current.setValue(current.getValue() - 1);

            // wait queue is here to store the possible next iteration order
            // for the break case, is that one specific case frequency is down to 0 and will not be back to heap
            // so the remaining item exist in the wait queue, and heap will be empty.
            waitQueue.offer(current);

            if (waitQueue.size() < k) {
                continue;
            }

            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                heap.offer(front);
            }
        }

        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        rearrangeString("aaabx", 3);
    }
}
