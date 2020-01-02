package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
434. Number of Segments in a String

Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5
 */

@Easy
@StringTag
public class NumberSegmentsinString {

    public static int countSegments(String s) {
        int res=0;
        for(int i=0; i<s.length(); i++) {
            if (s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ')) {
                res++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        countSegments(", , , ,        a, eaefa");
    }
}
