package leetcode.stack;

import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * Medium
 *
 * Share
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 */

@StackTag
@Medium
public class BasicCalculatorII {

    public static int calculate(String s) {

        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        s += '+';
        char operator = '+';
        for (int i = 0, n = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9') {
                n = n * 10 + ch - '0';
                continue;
            }
            if (ch == ' ') continue;
            if (operator == '+') {
                stack.push(n);
            } else if (operator == '-') {
                stack.push(-n);
            } else if (operator == '*') {
                stack.push(stack.pop()*n);
            } else if (operator == '/') {
                stack.push(stack.pop()/n);
            }
            operator = ch;
            n = 0;
        }

        int total = 0;
        while (!stack.isEmpty()) {
            total += stack.pop();
        }
        return total;
    }

    public static void main(String[] args) {
        String test = "3 * 5 + 6 + 8/4";

        System.out.println(calculate(test));
    }
}
