package javabasic.concurrency.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class Set
{
    public static void main(String[] args)
    {

    }

    public static void testHashSet()
    {
        Vector<Integer> test = new Vector<Integer>();

    }

    public static void CopyOnWriteArraySetTest()
    {
        CopyOnWriteArraySet<Integer> test = new CopyOnWriteArraySet<Integer>();
    }

    public static void ConcurrentSkipListSet()
    {
        ConcurrentSkipListSet<Integer> test = new ConcurrentSkipListSet<Integer>();
    }

    public static void CollectionBasedSyncArraySet()
    {
        HashSet s = (HashSet) Collections.synchronizedSet(new HashSet());

        synchronized (s)
        {
            Iterator i = s.iterator(); // Must be in the synchronized block
            while (i.hasNext())
            {
                
            }
        }
    }
}
