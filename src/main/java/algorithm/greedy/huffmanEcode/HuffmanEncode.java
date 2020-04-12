package algorithm.greedy.huffmanEcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
In this programming problem and the next you'll code up the greedy algorithm from the lectures on Huffman coding.

Download the text file below.

huffman.txt
This file describes an instance of the problem. It has the following format:

[number_of_symbols]

[weight of symbol #1]

[weight of symbol #2]

...

For example, the third line of the file is "6852892,"
indicating that the weight of the second symbol of the alphabet is 6852892.
(We're using weights instead of frequencies, like in the "A More Complex Example" video.)

Your task in this problem is to run the Huffman coding algorithm from lecture on this data set.
What is the maximum length of a codeword in the resulting Huffman code?

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases.
And then post them to the discussion forum!

Continuing the previous problem, what is the minimum length of a codeword in your Huffman code?

 */
public class HuffmanEncode {

    static class Node{
        long w;
        int max_bits;
        int min_bits;

        Node(long w,int max_bits,int min_bits)
        {
            this.w = w;
            this.min_bits = min_bits;
            this.max_bits = max_bits;
        }
    }


    public HuffmanEncode(String inputFileName) throws FileNotFoundException {

        Scanner in = new Scanner(new File(inputFileName));

        int size = in.nextInt();
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> (int) (a.w - b.w));

        for(int i=0; i<size; i++) {
            int w = in.nextInt();
            pq.add(new Node(w,0,0));
        }


        while(pq.size() > 1) {
            // merge two smallest
            Node n1 = pq.remove();
            Node n2 = pq.remove();

            // add back the merged
            pq.add(new Node(n1.w + n2.w, Math.max(n1.max_bits , n2.max_bits) + 1,Math.min(n1.min_bits, n2.min_bits) + 1));
        }

        System.out.println(pq.peek().max_bits);
        System.out.println(pq.peek().min_bits);
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        HuffmanEncode he = new HuffmanEncode("resource/huffman.txt");
    }
}
