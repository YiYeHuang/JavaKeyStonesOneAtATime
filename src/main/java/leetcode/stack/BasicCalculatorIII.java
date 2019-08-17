package leetcode.stack;

import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/**
 * 772. Basic Calculator III 基本计算器之三
 *
 *
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators ,
 * open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid.
 * All intermediate results will be in the range of [-2147483648, 2147483647].
 *
 * Some examples:
 *
 * "1 + 1" = 2
 * " 6-4 / 2 " = 4
 * "2*(5+5*2)/3+(6/2+8)" = 21
 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
 *
 *
 * Note: Do not use the eval built-in library function.
 */

@StackTag
@Medium
public class BasicCalculatorIII {

    public static int calculate(String s) {

        int operand = 0;
        int n = 0;
        Stack<Object> stack = new Stack<Object>();

        char lastOperator = '+';

        for (int i = s.length() - 1; i >= 0; i--) {

            char ch = s.charAt(i);

            if (Character.isDigit(ch)) {

                // Forming the operand - in reverse order.
                operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
                n += 1;

            } else if (ch != ' ') {
                if (n != 0) {

                    // Save the operand on the stack
                    // As we encounter some non-digit.
                    if (lastOperator=='*') {
                        stack.push(operand * (int)stack.pop() );
                        n = 0;
                        operand = 0;
                    }
                    else if (lastOperator=='/') {
                        stack.push(operand / (int)stack.pop());
                        n = 0;
                        operand = 0;
                    } else {
                        stack.push(operand);
                        n = 0;
                        operand = 0;
                    }
                }
                if (ch == '(') {

                    int res = calculateStack(stack);
                    stack.pop();
                    // Append the evaluated result to the stack.
                    // This result could be of a sub-expression within the parenthesis.
                    stack.push(res);

                } else {
                    // For other non-digits just push onto the stack.
                    if (ch != '*' && ch != '/') {
                        stack.push(ch);
                    }
                    lastOperator = ch;
                }
            }
        }

        //Push the last operand to stack, if any.
        if (n != 0) {
            stack.push(operand);
        }

        // Evaluate any left overs in the stack.
        return calculateStack(stack);
    }

    public static int calculateStack(Stack<Object> stack) {

        int res = 0;

        if (!stack.empty()) {
            res = (int) stack.pop();
        }

        // Evaluate the expression till we get corresponding ')'
        while (!stack.empty() && !((char) stack.peek() == ')')) {

            char sign = (char) stack.pop();

            if (sign == '+') {
                res += (int) stack.pop();
            } else {
                res -= (int) stack.pop();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String test = "(10-(2*2*2+2))";

        System.out.println(calculate(test));
    }
}
