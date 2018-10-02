package leetcode.datastructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Implement the following operations of a stack using queues.
 *  push(x) -- Push element x onto stack. 
 *  pop() -- Removes the element on top of the stack. 
 *  top() -- Get the top element. 
 *  empty() -- Return whether the stack is empty.
 *
 * 
 */
public class ImplementStackWithQueue
{
    Queue<Integer> queue;

    public ImplementStackWithQueue()
    {
        this.queue = new LinkedList<Integer>();
    }

    // Push element x onto stack.
    public void push(int x)
    {
        queue.add(x);
        for (int i = 0; i < queue.size() - 1; i++)
        {
            queue.add(queue.poll());
        }
    }

    // Removes the element on top of the stack.
    public int pop()
    {
        return queue.poll();
    }

    // Get the top element.
    public int top()
    {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty()
    {
        return queue.isEmpty();
    }
}
