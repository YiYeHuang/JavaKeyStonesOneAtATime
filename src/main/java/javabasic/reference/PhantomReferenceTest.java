package javabasic.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.TimeUnit;

public class PhantomReferenceTest {
    /**
     * 虚引用 永远无法用get() 获得，用来跟踪对象被垃圾回收的状态
     */
    private static ReferenceQueue<MyObject> phanQueue = new ReferenceQueue<>();

    public static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject's finalize called");
        }

        @Override
        public String toString() {
            return "I am MyObject";
        }
    }

    public static class CheckRefQueue implements Runnable {
        Reference<MyObject> obj = null;

        @Override
        public void run() {
            try {
                obj = (Reference<MyObject>) phanQueue.remove();
                System.out.println("删除的虚引用为：" + obj + "  but获取虚引用的对象obj.get()=" + obj.get());
                System.exit(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyObject object = new MyObject();
        Reference<MyObject> phanRef = new PhantomReference<>(object, phanQueue);
        System.out.println("创建的虚引用为：" + phanRef);
        new Thread(new CheckRefQueue()).start();

        object = null;
        TimeUnit.SECONDS.sleep(1);
        int i = 1;
        while (true) {
            System.out.println("第" + i++ + "次gc");
            System.gc();
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
