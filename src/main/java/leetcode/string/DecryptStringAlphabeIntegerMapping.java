package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
1309. Decrypt String from Alphabet to Integer Mapping

Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:

Characters ('a' to 'i') are represented by ('1' to '9') respectively.
Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
Return the string formed after mapping.

It's guaranteed that a unique mapping will always exist.



Example 1:

Input: s = "10#11#12"
Output: "jkab"
Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
Example 2:

Input: s = "1326#"
Output: "acz"
Example 3:

Input: s = "25#"
Output: "y"
Example 4:

Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
Output: "abcdefghijklmnopqrstuvwxyz"
 */

@Easy
@StringTag
public class DecryptStringAlphabeIntegerMapping {

    public static String freqAlphabets(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '#') {
                int index = (int) (s.charAt(i - 2) - '0') * 10 + (int) (s.charAt(i - 1) - '0');
                char res = 'a';
                res += index - 1;
                sb.append(res);
                i -= 2;
            } else {
                int index = (int) (s.charAt(i) - '0');
                char res = 'a';
                res += index - 1;
                sb.append(res);
            }
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        freqAlphabets("12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#");
    }
}
