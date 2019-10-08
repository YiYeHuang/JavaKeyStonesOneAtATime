package leetcode.stack;

import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/*
394. Decode String

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

@StackTag
@Medium
public class DecodeString {

    public String decodeString(String s) {
        String result = "";
        Stack<Integer> counterStack = new Stack<>();
        Stack<String> segmentStack = new Stack<>();

        int currentIdx = 0;

        while (currentIdx < s.length()) {
            // handle number
            if (Character.isDigit(s.charAt(currentIdx))) {
                int operand = 0;
                while (Character.isDigit(s.charAt(currentIdx))) {
                    operand = 10 * operand + (s.charAt(currentIdx) - '0');
                    currentIdx++;
                }
                counterStack.push(operand);
            } else if (s.charAt(currentIdx) == '[') {
                // handle the start of a pattern, push the previous result, to accumulate
                segmentStack.push(result);
                // once push result clear the current result
                result = "";
                currentIdx++;
            } else if (s.charAt(currentIdx) == ']') {
                // calculate the current result
                StringBuilder temp = new StringBuilder();
                temp.append(segmentStack.pop());
                int repeatCount = counterStack.pop();
                for (int i = 0; i< repeatCount; i++) {
                    temp.append(result);
                }
                result = temp.toString();
                currentIdx++;
            } else {
                result += s.charAt(currentIdx);
                currentIdx++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();

        ds.decodeString("3[a2[c]]");
    }
}
