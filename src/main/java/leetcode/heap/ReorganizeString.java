package leetcode.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Heap;

/*

767 ReorganizeString

Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""

 */


@Medium
@Heap
public class ReorganizeString {

  // this question is like the k apart question,
  // in k apart, we use a k and a additional queue to manage the k part
  // since this one is 2 apart, we dont need a queue, just use a pre value.
  public static String reorganizeString(String S) {

    Map<Character, Integer> map = new HashMap<>();
    for(char c: S.toCharArray()) {
      //Character Frequency count
      map.put(c, map.getOrDefault(c, 0) + 1);
    }

    PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((a,b) -> (b.getValue() - a.getValue()));
    maxHeap.addAll(map.entrySet());

    StringBuilder result = new StringBuilder();

    //The reason we need "prev" Entry is because we don't want 2 consecutive same characters!
    //Since this is the start of the resultant string we have to keep prev = null. We don't want to add anything before the start of the resultant string.
    Map.Entry<Character, Integer> prev = null;

    while(!maxHeap.isEmpty())
    {
      //Get the element with the highest freq from the PQ
      Map.Entry<Character, Integer> curr = maxHeap.poll();
      result.append(curr.getKey());//Append the current character

      //Since we have used one character from the current Entry, we update the frequency count -1
      curr.setValue(curr.getValue()-1);
      if(prev != null) {
        //Means that our previous entry still has characters available to use, so add it to the Priority Queue for further processing.
        maxHeap.offer(prev);
      }

      // After updating the current node's frequency count,
      // if we are still having some characters available, so update the prev with the current value.
      if(curr.getValue() > 0)
        prev = curr;
      else
        prev = null;
    }

    return result.length() == S.length() ? result.toString() : "";
  }

  public static void main(String[] args) {
    reorganizeString("aaaaab");
  }
}
