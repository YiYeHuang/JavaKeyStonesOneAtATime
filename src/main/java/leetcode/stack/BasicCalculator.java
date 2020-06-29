package leetcode.stack;


import leetcode.tag.level.Hard;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * Share
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * Example 1:
 *
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 *
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 *
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */

@StackTag
@Hard
public class BasicCalculator {

    public static int calculate(String s) {

        int currentNumber = 0;
        int n = 0;
        Stack<Object> stack = new Stack<>();

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                // Forming the operand - in reverse order.
                currentNumber = (int) Math.pow(10, n) * (int) (ch - '0') + currentNumber;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {
                    // Save the operand on the stack
                    // As we encounter some non-digit.
                    stack.push(currentNumber);
                    n = 0;
                    currentNumber = 0;
                }
                if (ch == '(') {

                    int res = calculateStackPre(stack);
                    // pop out the ')'
                    stack.pop();
                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res);

                } else {
                    // when reach here, it has to be operator just push onto the stack.
                    stack.push(ch);
                }
            } else if (ch == ' ') {
                stack.push(currentNumber);
                n = 0;
                currentNumber = 0;
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(currentNumber);
        }

        // Evaluate any left overs in the stack.
        return calculateStackPre(stack);
    }

    private static int calculateStackPre(Stack<Object> stack) {

        int res1 = 0;
        int res2 = 0;

        if (stack.size() == 1) {
            return (int) stack.pop();
        }
        if (!stack.empty()) {

            char sign;
            sign = (char) stack.pop();
            res1 = (int) stack.pop();
            res2 = (int) stack.pop();

            if (sign == '+') {
                res1 += res2;
            } else {
                res1 -= res2;
            }
        }

        return res1;
    }


    public static int calculate_front(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // if digit
            if(Character.isDigit(c)){
                number = 10 * number + (int)(c - '0');
            } else if(c == '+'){
                result += sign * number;
                // reset significant number
                number = 0;
                sign = 1;
            } else if(c == '-'){
                result += sign * number;
                number = 0;
                sign = -1;
            }else if(c == '('){
                //we push the result first, then sign;
                stack.push(result);
                stack.push(sign);
                //reset the sign and result for the value in the parenthesis
                sign = 1;
                result = 0;
            }else if(c == ')'){
                result += sign * number;
                //stack.pop() is the sign before the parenthesis
                result *= stack.pop();
                //stack.pop() now is the result calculated before the parenthesis
                result += stack.pop();

                number = 0;
            }
        }

        if(number != 0) {
            result += sign * number;
        }
        return result;
    }


    public static void main(String[] args) {
        String test = "(-10(+2 11))";

        System.out.println(calculate(test));
    }

}
