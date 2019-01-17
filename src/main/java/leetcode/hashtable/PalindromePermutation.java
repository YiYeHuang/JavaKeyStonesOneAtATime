package leetcode.hashtable;

import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;
import leetcode.tag.type.HashTableTag;

/**
 * 266. Palindrome Permutation
 *
 * Given a string, determine if a permutation of the string could form a palindrome.
 *
 * Example 1:
 *
 * Input: "code"
 * Output: false
 * Example 2:
 *
 * Input: "aab"
 * Output: true
 * Example 3:
 *
 * Input: "carerac"
 * Output: true
 */
@Google
@Facebook
@Microsoft

@Easy
@HashTableTag
@Hash
public class PalindromePermutation {

    public static boolean canPermutePalindrome(String s) {

        int[] low = new int[26];
        int[] high = new int[26];

        for (char c : s.toCharArray()) {
            if (c >= 'a' && c<='z') {
                low[c - 'a'] += 1;
            }

            if (c >= 'A' && c<='Z') {
                high[c - 'A'] += 1;
            }
        }

        int numberOfOdd = 0;

        for (int i = 0; i < 26; i++) {
            if (low[i] %2 == 1) {
                numberOfOdd++;
            }
            if (high[i] %2 == 1) {
                numberOfOdd++;
            }
        }

        if (numberOfOdd > 1) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aBb//a"));
    }
}
