package javabasic.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceTest {

    /**
     * out of memory then do second GC
     */
    private static ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>();

    /**
     * A static inner class is a nested class which is a static member of the outer class
     */
    public static class MyObject {
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject's finalize called");
        }

        @Override
        public String toString() {
            return "MyObject";
        }
    }

    public static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) softQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("Object for SoftReference is " + obj.get());
            }
        }
    }

    // run with -Xmx5M -XX:+PrintGCDetails
    public static void main(String[] args) {
        MyObject mobj = new MyObject();
        SoftReference<MyObject> softRef = new SoftReference<MyObject>(mobj, softQueue);
        new Thread(new CheckRefQueue()).start();

        mobj = null; // delete strong reference
        System.gc(); // force GC
        System.out.println("After gc: soft get = " + softRef.get());
        System.out.println("Allocate bit chunk of memory");
        byte[] b = new byte[5*2014*928];
        // soft reference will be GC when memory is not enough
        System.out.println("After new byte[]:Soft Get= " + softRef.get());
        System.gc();
    }
}
