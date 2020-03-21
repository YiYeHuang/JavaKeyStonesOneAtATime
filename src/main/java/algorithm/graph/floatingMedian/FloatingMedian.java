package algorithm.graph.floatingMedian;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Input Format:
 * The text file contains a list of the integers from 1 to 10000 in unsorted
 * order; you should treat this as a stream of numbers, arriving one by one.
 * Letting xi denote the ith number of the file, the kth median mk is defined
 * as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th
 * smallest number among x1,…,xk; if k is even, then mk is the (k/2)th smallest
 * number among x1,…,xk.)
 *
 * Output Format:
 * Return the sum of these 10000 medians, modulo 10000 (i.e., only the last 4
 * digits), that is (m1+m2+m3+?+m10000)mod10000.
 *
 * Time Complexity: O(log(k)) for each median
 */


public class FloatingMedian {
    public FloatingMedian(String inputFile) throws FileNotFoundException {
        minHeap = new PriorityQueue<Integer>((a, b)-> a - b);
        maxHeap = new PriorityQueue<Integer>((a, b)-> b - a);
        Scanner in = new Scanner(new File(inputFile));
        //add all vertices
        while (in.hasNextInt()){
            add(in.nextInt());
            //System.out.println(m.median());
            sum = sum + median();
        }
        System.out.println(sum % 10000);
    }

    // length always n/2
    private PriorityQueue<Integer> minHeap;
    // length always n/2 or n/2 + 1
    private PriorityQueue<Integer> maxHeap;
    private boolean even = true;
    public int sum;
    private int size;


    // EVEN: min heap get value
    // ODD: max heap get value
    public void add(int n){
        if (size == 0){
            maxHeap.add(n);
        } else if(size % 2 == 0){
            if (n > minHeap.peek()){
                maxHeap.add(minHeap.poll());
                minHeap.add(n);
            } else {
                maxHeap.add(n);
            }

        } else {
            if (n < maxHeap.peek()){
                minHeap.add(maxHeap.poll());
                maxHeap.add(n);
            } else {
                minHeap.add(n);
            }
        }
        size++;
    }
    public int median(){
        return maxHeap.peek();
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        String inputFile = "resource/Median.txt";
        FloatingMedian f = new FloatingMedian(inputFile);
    }
}
