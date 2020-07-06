package leetcode.slidingWindow;


import java.util.HashMap;
import java.util.Map;
import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.SlidingWindow;

import java.util.HashSet;
import java.util.Set;
import leetcode.tag.type.TwoPointer;

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

@Medium
@SlidingWindow
@HashTableTag
@TwoPointer
public class LongestSubstringWithoutRepeatingCharacters
{
    /**
     * The brute force:
     *
     * for each s(i, j) check if each i to j is unique, and update the max distance of i, j
     * during check, scan i to j every time.
     *
     * use set as sliding window to check duplicate in O(1) time
     *
     * [a b c a b c b b]
     *
     *  i   j
     * [a b c a b c b b]  set [a, b, c]
     *
     *  i     j
     * [a b c a b c b b]  set [a, b, c]  -->  set [b, c]
     *
     *    i   j
     * [a b c a b c b b]  set [b, c]  -->  set [b, c, a]
     *
     *    i     j
     * [a b c a b c b b]  set [b, c, a]  -->  set [c, a]
     *
     *      i   j
     * [a b c a b c b b]  set [b, c, a]  -->  set [c, a]
     *
     */
    public static int lengthOfLongestSubstringSlideWindow(String s)
    {
        int n = s.length();
        // the set is the sliding window
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                // add to sliding window
                set.add(s.charAt(j));
                // move right pointer to next
                j++;
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i));
                // move the the left pointer to next
                i++;
            }
        }
        return ans;
    }


    /**
     * when calculate the max we do j - i, but i is always next possible duplicate character index
     * so index of each position can be saved,
     * use map, when hit duplicate, character index i can be update to j when duplicate
     */
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]

        for (int j = 0, i = 0; j < n; j++) {

            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);

            // update index of char of duplicate
            map.put(s.charAt(j), j + 1);
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
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
