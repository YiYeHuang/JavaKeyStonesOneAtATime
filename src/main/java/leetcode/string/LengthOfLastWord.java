package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
58. Length of Last Word

Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
return the length of last word (last word means the last appearing word if we loop from left to right) in the string.

If the last word does not exist, return 0.

Note: A word is defined as a maximal substring consisting of non-space characters only.

Example:

Input: "Hello World"
Output: 5
 */

@Easy
@StringTag
public class LengthOfLastWord {

    public int lengthOfLastWord(String s) {
        if (s==null || s.length() == 0) return 0;
        if (s.replace(" ", "").length() == 0) return 0;
        String[] split = s.split(" ");
        if(split.length == 1) return split[0].length();

        return split[split.length - 1].length();
    }

    public int lengthOfLastWord_show(String s) {
        return s.trim().length()-s.trim().lastIndexOf(" ")-1;
    }
}
