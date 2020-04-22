package algorithm.dynamicProgramming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
Question 1
In this programming problem and the next you'll code up the knapsack algorithm from lecture.
Let's start with a warm-up. Download the text file below.

knapsack1.txt
This file describes a knapsack instance, and it has the following format:

[knapsack_size][number_of_items]

[value_1] [weight_1]

[value_2] [weight_2]

...

For example, the third line of the file is "50074 659", indicating that the second item has value 50074 and size 659, respectively.
You can assume that all numbers are positive. You should assume that item weights and the knapsack capacity are integers.
In the box below, type in the value of the optimal solution.
ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases.
And then post them to the discussion forum!

Enter answer here
1 point
2.Question 2
This problem also asks you to solve a knapsack instance, but a much bigger one.

Download the text file below.

knapsack_big.txt
This file describes a knapsack instance, and it has the following format:

[knapsack_size][number_of_items]

[value_1] [weight_1]

[value_2] [weight_2]

...

For example, the third line of the file is "50074 834558", indicating that the second item has value 50074
and size 834558, respectively. As before, you should assume that item weights and the knapsack capacity are integers.

This instance is so big that the straightforward iterative implemetation uses an infeasible amount of time and space.
So you will have to be creative to compute an optimal solution. One idea is to go back to a recursive implementation,
solving subproblems --- and, of course, caching the results to avoid redundant work --- only on an "as needed" basis.
Also, be sure to think about appropriate data structures for storing and looking up solutions to subproblems.

In the box below, type in the value of the optimal solution.

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases.
And then post them to the discussion forum!
 */
public class Knapsack {

    public Knapsack(String inputFileName) throws FileNotFoundException {

        Scanner in = new Scanner(new File(inputFileName));
        int totalWeight = in.nextInt();
        int totalSize = in.nextInt();

        long val[] = new long[totalSize+1];
        int w[] = new int[totalSize+1];

        for(int i=1; i<=totalSize; i++) {
            val[i] = in.nextInt();
            w[i] = in.nextInt();
        }

        long [] dp1 = new long[totalWeight + 1];
        long [] dp2 = new long[totalWeight + 1];

        for(int i=0; i<=totalWeight; i++) {
            dp1[i] = 0;
            dp2[i] = 0;
        }

        for(int i=1; i<=totalSize; i++) {
            for(int j=1; j<=totalWeight; j++)
            {
                if(j < w[i]) {
                    // the current weight cannot fit the target weight, check if previously contains any
                    dp2[j] = dp1[j];
                } else {
                    // capacity >= target weight, check if previously contains any weight value
                    dp2[j] = Math.max(dp1[j], val[i] + dp1[j - w[i]]);
                }
            }

            deepCopy(dp1, dp2);
            dp2 = new long[totalWeight + 1];

//            printMatrix(dp);
//            System.out.println("------------------------------------------------------");
        }
        System.out.println(dp1[totalWeight]);
    }

    public void printMatrix(long [][] dp) {
        for (int i =0 ; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] +  " ");
            }
            System.out.println();
        }
    }

    public void deepCopy(long [] dp1, long [] dp2) {
        for (int i =0 ; i < dp1.length; i++) {
            dp1[i] = dp2[i];
        }
    }

    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        Knapsack he = new Knapsack("resource/knapsack_big.txt");
    }
}
