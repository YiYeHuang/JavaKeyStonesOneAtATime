package leetcode.hashtable;

import leetcode.tag.company.Bloomberg;
import leetcode.tag.company.Google;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;

/**
 * 205. Isomorphic Strings
 *
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 */

@Google
@LinkedIn
@Bloomberg

@Easy
@Hash
public class IsomorphicStrings {

    /**
     * A transform of int[256] problem, each word hosting a ASCII table hash,
     * A n a
     * | | |
     * B o b
     *
     * if all words are unique then the result is valid, the key check is the
     */
    public static boolean isIsomorphic(String s1, String s2) {
        int[] m = new int[512];
        for (int i = 0; i < s1.length(); i++) {
            if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
            m[s1.charAt(i)]  = i+1;
            m[s2.charAt(i)+256]  = i+1;
        }
        return true;
    }

    public static void main(String[] args) {
        isIsomorphic("Ana", "Bob");
    }
}
