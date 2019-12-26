package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
686. Repeated String Match

Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
 */

@Easy
@StringTag
public class RepeatedStringMatch {
    public static int repeatedStringMatch(String A, String B) {

        //does not make sense compare A and B while A is still shorter than B
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if(sb.toString().contains(B)) return count;
        if(sb.append(A).toString().contains(B)) return ++count;
        return -1;
    }

    public static void main(String[] args) {
        repeatedStringMatch("abc", "cabcabca");
    }
}
