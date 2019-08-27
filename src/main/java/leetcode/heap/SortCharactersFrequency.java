package leetcode.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Heap;

/*
451. Sort Characters By Frequency

Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.
 */

@Medium
@Heap
public class SortCharactersFrequency {

  public static String frequencySort(String s) {

    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
    }

    List<Character> [] bucket = new List[s.length() + 1];
    for (char key : map.keySet()) {
      int frequency = map.get(key);
      if (bucket[frequency] == null) bucket[frequency] = new ArrayList<>();
      bucket[frequency].add(key);
    }

    StringBuilder sb = new StringBuilder();
    for (int pos = bucket.length - 1; pos >= 0; pos--) {
      if (bucket[pos] != null) {
        for (char c : bucket[pos]) {
          for (int i = 0; i < map.get(c); i++)
            sb.append(c);
        }
      }
    }

    return sb.toString();
  }

  public String frequencySortPQ(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray())
      map.put(c, map.getOrDefault(c, 0) + 1);

    PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
    pq.addAll(map.entrySet());

    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      Map.Entry e = pq.poll();
      for (int i = 0; i < (int)e.getValue(); i++)
        sb.append(e.getKey());
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    frequencySort("avbabbcaaA");
  }
}
