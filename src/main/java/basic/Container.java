package basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Container
{
    public static void main(String[] args)
    {
        Stack test = new Stack<Integer>();
        ArrayList<Integer> test2 = new ArrayList<Integer>();

        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
     
        SynchronousQueue<Integer> test3 = new SynchronousQueue<Integer>();

        BlockingQueue queue = new ArrayBlockingQueue(1024);
    }
}
