package javabasic.reference;


import dataStructure.skiplist.impl.SLNode;

import java.util.HashMap;
import java.util.LinkedList;

public class StrongReferenceTest {
    public static void main(String[] args) {

        // collection frameworks are strong reference,objects here will not be GC
        LinkedList<SLNode> nodeList = new LinkedList<>();
        HashMap<Integer, SLNode> map = new HashMap<>();


        SLNode test = new SLNode(1,1);
        System.out.println(test);
        nodeList.add(test);
        map.put(1, test);

        System.out.println(map.get(1));
        System.out.println(nodeList.get(0));

        // Containers actually maps to the same memory address in the heap
        // so....shallow copy
        // as long as there still reference point to the object, no matter how long it wait, gc will not run
        test = null; // delete strong reference
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(map.get(1));
        System.out.println(nodeList.get(0));
    }
}
