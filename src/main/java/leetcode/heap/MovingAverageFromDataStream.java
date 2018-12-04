package leetcode.heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of
 * all integers in the sliding window.
 * 
 * For example, MovingAverage 
 * m = new MovingAverage(3); 
 * m.next(1) = 1 
 * m.next(10)= (1 + 10) / 2 
 * m.next(3) = (1 + 10 + 3) / 3 
 * m.next(5) = (10 + 3 + 5) / 3
 */
public class MovingAverageFromDataStream
{
    Queue<Integer> cache = new LinkedList<Integer>();

    int maxSize = 0;
    int currentSum = 0;
    int currentCount = 0;

    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size)
    {
        maxSize = size;
    }

    public double next(int val)
    {
        if (currentCount < maxSize)
        {
            cache.add(val);
            currentCount++;
            currentSum += val;
            return (double)(currentSum) / (double)(currentCount);
        }
        else
        {
            currentSum -= cache.poll();
            currentSum += val;
            cache.add(val);
            return (double)(currentSum) / (double)(currentCount);
        }
    }

    public static void main(String[] args)
    {
        MovingAverageFromDataStream test = new MovingAverageFromDataStream(3);
        System.out.println(test.next(1));
        System.out.println(test.next(10));
        System.out.println(test.next(3));
        System.out.println(test.next(5));
        
    }
}
