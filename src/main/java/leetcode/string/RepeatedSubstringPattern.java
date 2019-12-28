package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.KMP;
import leetcode.tag.type.StringTag;

/*
459. Repeated Substring Pattern

Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.



Example 1:

Input: "abab"
Output: True
Explanation: It's the substring "ab" twice.
Example 2:

Input: "aba"
Output: False
Example 3:

Input: "abcabcabcabc"
Output: True
Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
 */

@StringTag
@Easy
@KMP
public class RepeatedSubstringPattern {

    // think from small group too large is very hard
    // instead, shrink from the max subset: from half, and slowly reduce to the correct size
    public static boolean repeatedSubstringPattern(String s) {
        for(int i = s.length()/2; i>=1; i--) {
            if (s.length()/2 % i == 0) {
                int parts= (s.length())/i;
                String part = s.substring(0, i);
                StringBuilder sb = new StringBuilder();

                // construct back the parts and compare with OG
                for (int j = 0; j<parts; j++) {
                    sb.append(part);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String part = "ababab";
        repeatedSubstringPattern(part);
    }
}
