package leetcode.multiThread;

import leetcode.tag.level.Medium;
import leetcode.tag.type.MultiThread;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
1115. Print FooBar Alternately

Suppose you are given the following code:

class FooBar {
  public void foo() {
    for (int i = 0; i < n; i++) {
      print("foo");
    }
  }

  public void bar() {
    for (int i = 0; i < n; i++) {
      print("bar");
    }
  }
}
The same instance of FooBar will be passed to two different threads. Thread A will call foo() while thread B will call bar(). Modify the given program to output "foobar" n times.



Example 1:

Input: n = 1
Output: "foobar"
Explanation: There are two threads being fired asynchronously.
One of them calls foo(), while the other calls bar(). "foobar" is being output 1 time.
Example 2:

Input: n = 2
Output: "foobarfoobar"
Explanation: "foobar" is being output 2 times.
 */

@Medium
@MultiThread
public class PrintFooBarAlternately {
    private int n;
    private volatile int signal = 0;
    ////flag 0->foo to be print  1->foo has been printed
    private AtomicInteger flag=new AtomicInteger(0);

    ReentrantLock reentrantLock= new ReentrantLock();
    Condition fooPrintedCondition=reentrantLock.newCondition();
    Condition barPrintedCondition=reentrantLock.newCondition();

    public PrintFooBarAlternately(int n) {
        this.n = n;
    }

    public void foo_lock(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            try {
                reentrantLock.lock();
                while (signal ==1){
                    barPrintedCondition.await();
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                signal=1;
                fooPrintedCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public void bar_lock(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            reentrantLock.lock();
            while (signal==0){
                fooPrintedCondition.await();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            signal=0;
            barPrintedCondition.signalAll();
            reentrantLock.unlock();
        }
    }

    public void foo_volatile(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            while (true){
                if(signal==0){
                    printFoo.run();
                    signal=1;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }

    public void bar_volatile(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (true){
                if(signal==1){
                    printBar.run();
                    signal=0;
                    break;
                }
                Thread.sleep(1);
            }
        }
    }

    public void foo_CAS(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (!flag.compareAndSet(0,1)){
                Thread.sleep(1);
            }
            printFoo.run();
        }
    }

    public void bar_CAS(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while (!flag.compareAndSet(1,0)){
                Thread.sleep(1);
            }
            printBar.run();
        }
    }

    Semaphore s = new Semaphore(0);
    Semaphore s2 = new Semaphore(1);

    public void foo_Semaphore(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printFoo.run() outputs "foo". Do not change or remove this line.
            s2.acquire();

            printFoo.run();
            s.release();

        }
    }

    public void bar_Semaphore(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            s.acquire();

            printBar.run();
            s2.release();

        }
    }
}
