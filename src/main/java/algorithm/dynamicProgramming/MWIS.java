package algorithm.dynamicProgramming;

import algorithm.greedy.huffmanEcode.HuffmanEncode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
In this programming problem you'll code up the dynamic programming algorithm for
computing a maximum-weight independent set of a path graph.

This file describes the weights of the vertices in a path graph
(with the weights listed in the order in which vertices appear in the path).
It has the following format:

[number_of_vertices]

[weight of first vertex]

[weight of second vertex]

...

For example, the third line of the file is "6395702,"
indicating that the weight of the second vertex of the graph is 6395702.

Your task in this problem is to run the dynamic programming algorithm (and the reconstruction procedure)
from lecture on this data set. The question is: of the vertices 1, 2, 3, 4, 17, 117, 517, and 997,
which ones belong to the maximum-weight independent set?
(By "vertex 1" we mean the first vertex of the graph---there is no vertex 0.)
In the box below, enter a 8-bit string, where the ith bit should be 1 if the ith of
these 8 vertices is in the maximum-weight independent set, and 0 otherwise.
For example, if you think that the vertices 1, 4, 17,
and 517 are in the maximum-weight independent set and the other four vertices are not,
then you should enter the string 10011010 in the box below.
 */
public class MWIS {
    public MWIS(String inputFileName) throws FileNotFoundException {

        Scanner in = new Scanner(new File(inputFileName));

        int n = in.nextInt();

        long a[] = new long[n+1];
        long dp[] = new long[n+1];

        for(int i=1; i<=n; i++) {
            a[i] = in.nextInt();
        }

        dp[1] = a[1];
        for(int i=2; i<=n; i++)
            dp[i] = Math.max(dp[i-1], a[i] + dp[i-2]);

        int wis[] = new int[n+1];

        int i = n;
        while(i >= 1)
        {
            if(i == 1) {wis[i] = 1; break;}

            if(dp[i-1] > dp[i-2]+ a[i])
                i--;
            else
            {
                wis[i] = 1;
                i = i-2;
            }
        }

        int x[] = new int[]{1, 2, 3, 4, 17, 117, 517, 997};

        for(int k:x)
            System.out.print(wis[k]);
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        MWIS he = new MWIS("resource/mwis.txt");
    }
}
