package leetcode.design_systemdesign;

import leetcode.tag.company.Facebook;
import leetcode.tag.company.LinkedIn;
import leetcode.tag.level.Easy;
import leetcode.tag.type.StackTag;

import java.util.Stack;

@LinkedIn
@Facebook
@Easy
@StackTag
/**
 * Question differ from Min stack is popMax, O(1) is just not possible for this.
 * Use double stack to solve
 */
class MaxStack {

    int max = Integer.MIN_VALUE;
    Stack<Integer> stack = new Stack<Integer>();

    Stack<Integer> buffer = new Stack<Integer>();

    /** initialize your data structure here. */
    public MaxStack() {

    }

    public void push(int x) {
        if(x >= max){
            stack.push(max);
            max=x;
        }
        stack.push(x);
    }

    public int pop() {
        int res = stack.pop();
        if (res == max) {
            max = stack.pop();
        }
        return res;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return max;
    }

    public int popMax() {
        while (stack.peek() != max) {
            buffer.push(stack.pop());
        }

        int res = stack.pop();
        max = stack.pop();
        while (!buffer.isEmpty()) {
            int val = buffer.pop();
            push(val);
        }
        return res;
    }

    public static void main(String[] args) {
        MaxStack ms = new MaxStack();

        ms.push(5);
        ms.push(1);
        ms.popMax();
        ms.peekMax();
    }
}
