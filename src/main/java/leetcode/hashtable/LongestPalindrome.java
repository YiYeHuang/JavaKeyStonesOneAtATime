package leetcode.hashtable;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;
import leetcode.tag.type.HashTableTag;

/**
 * 409. Longest Palindrome
 *
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.
 *
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 *
 * Note:
 * Assume the length of given string will not exceed 1,010.
 */

@Amazon

@Easy
@Hash
@HashTableTag
public class LongestPalindrome {
    public static int longestPalindrome(String s) {
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

        int maxLength = 0;

        for (int i = 0; i < 26; i++) {
            maxLength += (low[i]/2)*2;
            maxLength += (high[i]/2)*2;
        }

        // since /2 round up the odd one, if all string is even number, than return s length,
        // otherwise we can at least use one odd char
        return maxLength == s.length() ? maxLength : maxLength+1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }
}
