package concurrency.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

public class List
{
    public static void main(String[] args)
    {

    }

    public static void VectorTest()
    {
        Vector<Integer> test = new Vector<Integer>();
        
    }

    public static void CopyOnWriteArrayListTest()
    {
        CopyOnWriteArrayList<Integer> test = new CopyOnWriteArrayList<Integer>();
    }

    public static void CollectionBasedSyncArrayList()
    {
        ArrayList list = (ArrayList) Collections.synchronizedList(new ArrayList());

        synchronized (list)
        {
            Iterator i = list.iterator();// Must be in synchronized block
            while (i.hasNext())
            {
                
            }
        }
    }
}
