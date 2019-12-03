package leetcode.multiThread;

/*
1114. Print in Order

Suppose we have a class:

public class Foo {
  public void first() { print("first"); }
  public void second() { print("second"); }
  public void third() { print("third"); }
}
The same instance of Foo will be passed to three different threads.
Thread A will call first(), thread B will call second(), and thread C will call third().
Design a mechanism and modify the program to ensure that second() is executed after first(), and third() is executed after second().



Example 1:

Input: [1,2,3]
Output: "firstsecondthird"
Explanation: There are three threads being fired asynchronously.
The input [1,2,3] means thread A calls first(), thread B calls second(),
and thread C calls third(). "firstsecondthird" is the correct output.
Example 2:

Input: [1,3,2]
Output: "firstsecondthird"
Explanation: The input [1,3,2] means thread A calls first(), thread B calls third(), and thread C calls second().
"firstsecondthird" is the correct output.


Note:

We do not know how the threads will be scheduled in the operating system,
even though the numbers in the input seems to imply the ordering.
The input format you see is mainly to ensure our tests' comprehensiveness.
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintInOrder {

    private final CountDownLatch l2;
    private final CountDownLatch l3;

    private AtomicInteger flag=new AtomicInteger(2);

    public PrintInOrder() {
        l2 = new CountDownLatch(1);
        l3 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        l2.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        l2.await();
        printSecond.run();
        l3.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        l3.await();
        printThird.run();
    }

    public void first_atomic(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        flag.decrementAndGet();
    }

    public void second_atomic(Runnable printSecond) throws InterruptedException {
        while(flag.get() != 1) {
            Thread.sleep(1);
        }
        printSecond.run();
        flag.decrementAndGet();
    }

    public void third_atomic(Runnable printThird) throws InterruptedException {
        while(flag.get() != 0) {
            Thread.sleep(1);
        }
        printThird.run();
    }

    public static void main(String[] args) {

        PrintInOrder foo = new PrintInOrder();
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        Thread t3 = new Thread();
    }
}
