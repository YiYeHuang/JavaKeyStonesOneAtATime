package leetcode.string;

import leetcode.tag.level.Easy;

/*
821. Shortest Distance to a Character

Given a string S and a character C, return an array of
integers representing the shortest distance from the character C in the string.

Example 1:

Input: S = "loveleetcode", C = 'e'
Output: [3, 2, 1, 0, 1, 0, 0, 1, 2, 2, 1, 0]


Note:

S string length is in [1, 10000].
C is a single character, and guaranteed to be in string S.
All letters in S and C are lowercase.
 */

@Easy
public class ShortestDistanceCharacter {

  public static int[] shortestToChar(String S, char C) {
    int[] result = new int[S.length()];

    int firstOccur = S.indexOf(C);

    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == C) {
        if (i != firstOccur) firstOccur = i;
      } else {
        result[i] = Math.abs(i - firstOccur);
      }
    }

    int lastOccur = S.lastIndexOf(C);

    for (int i = S.length() - 1; i >= 0; i--) {
      if (S.charAt(i) == C) {
        if (i != lastOccur) lastOccur = i;
      } else {
        result[i] = Math.min(Math.abs(i - lastOccur), result[i]);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    shortestToChar("loveleetcode", 'e');
  }
}
