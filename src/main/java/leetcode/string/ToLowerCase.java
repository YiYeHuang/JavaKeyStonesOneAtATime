package leetcode.string;

import leetcode.tag.type.StringTag;

/**
 * 709. To Lower Case
 *
 * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
 *
 *
 *
 * Example 1:
 *
 * Input: "Hello"
 * Output: "hello"
 * Example 2:
 *
 * Input: "here"
 * Output: "here"
 * Example 3:
 *
 * Input: "LOVELY"
 * Output: "lovely"
 */

@StringTag
public class ToLowerCase {
    public String toLowerCase(String str) {
        StringBuilder sb = new StringBuilder();
        short dif = 'a' - 'A';
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 'Z' && str.charAt(i) > 'A') {
                char cc = str.charAt(i);
                cc += dif;
                sb.append(cc);
            }
            else {
                sb.append(str.charAt(i));
            }
        }

        return sb.toString();
    }
}
