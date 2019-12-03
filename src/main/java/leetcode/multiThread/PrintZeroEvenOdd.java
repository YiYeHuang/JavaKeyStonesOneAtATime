package leetcode.multiThread;

/*
1116. Print Zero Even Odd

Suppose you are given the following code:

class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // constructor
  public void zero(printNumber) { ... }  // only output 0's
  public void even(printNumber) { ... }  // only output even numbers
  public void odd(printNumber) { ... }   // only output odd numbers
}
The same instance of ZeroEvenOdd will be passed to three different threads:

Thread A will call zero() which should only output 0's.
Thread B will call even() which should only output even numbers.
Thread C will call odd() which should only output odd numbers.
Each of the threads is given a printNumber method to output an integer.
Modify the given program to output the series 010203040506... where the length of the series must be 2n.


Example 1:

Input: n = 2
Output: "0102"
Explanation: There are three threads being fired asynchronously.
One of them calls zero(), the other calls even(), and the last one calls odd().
"0102" is the correct output.

Example 2:

Input: n = 5
Output: "0102030405"
 */

import leetcode.tag.level.Medium;
import leetcode.tag.type.MultiThread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

@Medium
@MultiThread
public class PrintZeroEvenOdd {

    private int n;
    private Semaphore zeroSem, oddSem, evenSem;

    public PrintZeroEvenOdd(int n) {
        this.n = n;
        zeroSem = new Semaphore(1);
        oddSem = new Semaphore(0);
        evenSem = new Semaphore(0);
        /**
         * For semZero all acquire() calls will block and tryAcquire() calls will return false, until you do a release()
         * For semOne the first acquire() calls will succeed and the rest will block until the first one releases.
         */
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i < n; ++i) {
            zeroSem.acquire();
            printNumber.accept(0);
            if(i % 2 == 0) {
                oddSem.release();
            } else {
                evenSem.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release();
        }
    }
}
