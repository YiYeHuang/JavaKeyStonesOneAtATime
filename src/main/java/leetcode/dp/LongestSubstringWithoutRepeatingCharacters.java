package leetcode.dp;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 * 3. Longest Substring Without Repeating Characters
 *
 * Given a string, find the length of the longest substring without repeating
 * characters.
 * 
 * Examples:
 * 
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * 
 * Given "bbbbb", the answer is "b", with the length of 1.
 * 
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
 * answer must be a substring, "pwke" is a subsequence and not a substring.
 * 
 * 
 */

@Amazon

@Medium
@SlidingWindow
@HashTableTag

public class LongestSubstringWithoutRepeatingCharacters
{
    /**
     * one sue set to track the repeat
     *
     * once find the repeat, clean the list and redo
     */
    public static int lengthOfLongestSubstring(String s)
    {
        if (null == s || s.length() == 0)
        {
            return 0;
        }

        int[] seq = new int[s.length()];
        Set<String> result = new HashSet<String>();

        int resultMax = 0;
        for (int i = 0; i < s.length(); i++)
        {
            String tar = String.valueOf(s.charAt(i));
            result.add(tar);
            int currentMax = 1;
            for (int j = i+1 ; j < s.length(); j++)
            {
                String newtar = String.valueOf(s.charAt(j));
                if (result.contains(newtar))
                {
                    result.clear();
                    break;
                }
                else
                {
                    result.add(newtar);
                    currentMax++;
                }
            }
            seq[i] = currentMax;
            if (currentMax >= resultMax)
            {
                resultMax = currentMax;
            }
            result.clear();
        }

        return resultMax;
    }

    /**
     * The naive approach is very straightforward. But it is too slow. So how
     * can we optimize it?
     * 
     * In the naive approaches, we repeatedly check a substring to see if it has
     * duplicate character. But it is unnecessary. If a substring s_{ij}s ​ij ​​
     * from index ii to j - 1j−1 is already checked to have no duplicate
     * characters. We only need to check if s[j]s[j] is already in the substring
     * s_{ij}s ​ij ​​ .
     * 
     * To check if a character is already in the substring, we can scan the
     * substring, which leads to an O(n^2)O(n ​2 ​​ ) algorithm. But we can do
     * better.
     * 
     * By using HashSet as a sliding window, checking if a character in the
     * current can be done in O(1)O(1).
     * 
     * A sliding window is an abstract concept commonly used in array/string
     * problems. A window is a range of elements in the array/string which
     * usually defined by the start and end indices, i.e. [i, j)[i,j)
     * (left-closed, right-open). A sliding window is a window "slides" its two
     * boundaries to the certain direction. For example, if we slide [i, j)[i,j)
     * to the right by 11 element, then it becomes [i+1, j+1)[i+1,j+1)
     * (left-closed, right-open).
     * 
     * Back to our problem. We use HashSet to store the characters in current
     * window [i, j)[i,j) (j = ij=i initially). Then we slide the index jj to
     * the right. If it is not in the HashSet, we slide jj further. Doing so
     * until s[j] is already in the HashSet. At this point, we found the maximum
     * size of substrings without duplicate characters start with index ii. If
     * we do this for all ii, we get our answer.
     */
    public static int lengthOfLongestSubstringSlideWindow(String s)
    {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static void main(String[] args)
    {
        /**
         * a,               [a],        expending upper bound i = 0, j = 1
         * a, b             [a, b]      expending upper bound i = 0, j = 2
         * a, b, b          [b]         shrinking lower bound i = 1, j = 2
         * a, b, b          []          shrinking lower bound i = 2, j = 2
         * a, b ,b          [b]         expending upper bound i = 2, j = 3
         * a, b, b, a       [a, b]      expending upper bound i = 2, j = 4
         * a, b, b, a, b    [a]         shrinking lower bound i = 3, j = 4
         * a, b ,b, a, b    [a, b]      expending upper bound i = 3, j = 5
         * a, b ,b, a, b, c [a, b, c]   expending upper bound i = 3, j = 6
         * 
         * break
         */
        System.out.println(lengthOfLongestSubstringSlideWindow("abbabc"));
    }
}
