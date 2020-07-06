package leetcode.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import leetcode.tag.level.Hard;
import leetcode.tag.type.DP;

/*
140. Word Break II

Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */

@Hard
@DP
public class WordBreakII {

  public static List<String> wordBreak(String s, Set<String> wordDict) {

    List<String> rst = new ArrayList<>();
    if (s == null || s.length() == 0 || wordDict == null) {
      return rst;
    }

    boolean[] canBreak = new boolean[s.length()];
    Arrays.fill(canBreak, true);
    StringBuilder sb = new StringBuilder();
    dfs(rst, sb, s, wordDict, canBreak, 0);
    return rst;
  }

  private static void dfs(
      List<String> rst, StringBuilder sb, String s, Set<String> dict, boolean[] canBreak, int start) {


    if (start == s.length()) {
      rst.add(sb.substring(1));
      return;
    }

    if (!canBreak[start]) {
      return;
    }

    for (int i = start + 1; i <= s.length(); i++) {
      String word = s.substring(start, i);
      if (!dict.contains(word)) continue;

      int sbBeforeAdd = sb.length();
      sb.append(" " + word);

      int rstBeforeDFS = rst.size();
      dfs(rst, sb, s, dict, canBreak, i);
      if (rst.size() == rstBeforeDFS) {
        canBreak[i] = false;
      }
      sb.delete(sbBeforeAdd, sb.length());
    }
  }


  /**
   * from word break
   * ============================================================================================
   */

  public static List<String> wordBreak_2(String s, List<String> wordDict) {
    List<String> rst = new ArrayList<>();
    if (null == s || null == wordDict || wordDict.size() == 0)
      return rst;

    Set<String> dict = new HashSet<>();
    for (String word : wordDict)
    {
      dict.add(word);
    }

    boolean[] f = new boolean[s.length() + 1];

    f[0] = true;

    /**
     * Typical DP question, need an array to store the value
     * The idea is to store flag to check word_contans end here,
     */
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 0; j < i; j++) {
        String target = s.substring(j, i);
        if (f[j] && dict.contains(target)) {
          System.out.print("Contains " + target + "\n");
          f[i] = true;
          break;
        }
        System.out.print(target + "\n");
      }
    }

    if ( f[s.length()]) {
      dfs_build(rst, new StringBuilder(), s, dict, f, 0);
    }
    return rst;
  }

  private static void dfs_build(
      List<String> rst, StringBuilder sb, String s, Set<String> dict, boolean[] canBreakResult, int start) {


    if (start == s.length()) {
      rst.add(sb.substring(1));
      return;
    }

    if (!canBreakResult[start]) {
      return;
    }

    for (int i = start + 1; i <= s.length(); i++) {
      if (!canBreakResult[i]) continue;

      int sbBeforeAdd = sb.length();
      String attach = s.substring(start, i);
      if (!dict.contains(attach)) continue;

      sb.append(" " +  s.substring(start, i));

      dfs_build(rst, sb, s, dict, canBreakResult, i);
      sb.delete(sbBeforeAdd, sb.length());
    }
  }


  public static void main(String[] args)
  {
    List<String> test = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");

    HashSet<String> dict = new HashSet<>(test);

    wordBreak_2("pineapplepenapple", test);
  }

}
