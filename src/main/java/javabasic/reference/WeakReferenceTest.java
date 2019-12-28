package javabasic.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class WeakReferenceTest {

    /**
     * like softreference after delet strong reference
     */
    private static ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<>();

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
                obj = (Reference<MyObject>) weakQueue.remove();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                System.out.println("delete " + obj + "  but get soft ref obj.get()=" + obj.get());
            }
        }
    }

    public static void main(String[] args) {
        MyObject object = new MyObject();
        Reference<MyObject> weakRef = new WeakReference<>(object, weakQueue);
        System.out.println("create " + weakRef);
        new Thread(new CheckRefQueue()).start();

        object = null; // delete strong reference

        System.out.println("Before GC: Weak Get= " + weakRef.get());
        System.gc();
        System.out.println("After GC: Weak Get= " + weakRef.get());
    }
}
