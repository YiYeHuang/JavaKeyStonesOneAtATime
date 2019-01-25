package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.DFS;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.StringTag;

import java.util.Stack;

/**
 * 20. Valid Parentheses

 Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true

 */

@Easy
@StringTag
@DFS
@StackTag
public class ValidParentheses {

    /**
     * even index push, odd pop.
     */
    public static boolean isValid3(String s)
    {
        Stack<Integer> p = new Stack<>();
        for (int i = 0; i < s.length(); i++)
        {
            int q = "(){}[]".indexOf(s.substring(i, i + 1));
            if (q % 2 == 1)
            {
                if (p.isEmpty() || p.pop() != q - 1)
                    return false;
            } 
            else
            {
                p.push(q);
            }
        }
        return p.isEmpty();
    }

    public static void main(String[] args)
    {

    }
}
