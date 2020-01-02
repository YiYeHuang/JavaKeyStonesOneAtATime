package leetcode.design_systemdesign;


import leetcode.tag.level.Easy;
import leetcode.tag.type.Design;
import leetcode.tag.type.StackTag;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 * 
 * push(x) -- Push element x onto stack. 
 * pop() -- Removes the element on top of the stack. 
 * top() -- Get the top element. 
 * getMin() -- Retrieve the minimum
 * element in the stack.
 */

@Easy
@Design
@StackTag
/**
 * only push the old minimum value when the current minimum value changes after pushing the new value x
 *
 * key move: push twice and pop twice
 */
public class MinStack
{
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();


    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        
        // Key is here, always keep the copy of the previous min
        if(stack.pop() == min) {
            min=stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args)
    {
        MinStack test = new MinStack();
        test.push(10);
        test.push(12);
        test.push(2);
        int min1 = test.getMin();
        test.pop();
        int min3 = test.getMin();
        test.pop();
        int min2 = test.getMin();
        test.pop();
        test.pop();
        test.pop();
        test.pop();
        test.pop();
        test.pop();
    }
}
