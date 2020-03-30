package algorithm.greedy.jobSchedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
/*
1.Question 1
In this programming problem and the next you'll code up the greedy algorithms
from lecture for minimizing the weighted sum of completion times..

This file describes a set of jobs with positive and integral weights and lengths. It has the format

[number_of_jobs]

[job_1_weight] [job_1_length]
[job_2_weight] [job_2_length]
...

For example, the third line of the file is "74 59", indicating that the second job has weight 74 and length 59.

You should NOT assume that edge weights or lengths are distinct.

Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing order of the difference (weight - length).
Recall from lecture that this algorithm is not always optimal. IMPORTANT: if two jobs have equal difference (weight - length),
you should schedule the job with higher weight first. Beware: if you break ties in a different way, you are likely to get the wrong answer.
You should report the sum of weighted completion times of the resulting schedule --- a positive integer --- in the box below.

ADVICE: If you get the wrong answer, try out some small test cases to debug your algorithm (and post your test cases to the discussion forum).

2.Question 2
For this problem, use the same data set as in the previous problem.

Your task now is to run the greedy algorithm that schedules jobs (optimally) in decreasing order of the ratio (weight/length).
In this algorithm, it does not matter how you break ties.
You should report the sum of weighted completion times of the resulting schedule --- a positive integer --- in the box below.

 */
public class JobSchedule {

    class Job {
        public int weight;
        public int length;

        public Job(int weight, int length) {
            this.weight = weight;
            this.length = length;
        }

        public double getDiff1() {
            return weight - length;
        }

        public double getDiff2() {
            return (double) weight / (double) length;
        }
    }

    public static class JobComparator2 implements Comparator<Job>
    {
        @Override
        public int compare(Job x, Job y)
        {
            double xRatio = (double) x.weight / (double) x.length;
            double yRatio = (double) y.weight / (double) y.length;
            if (xRatio == yRatio) {
                return y.weight - x.weight;
            } else if (yRatio > xRatio){
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static class JobComparator1 implements Comparator<Job>
    {
        @Override
        public int compare(Job x, Job y)
        {
            if (x.weight - x.length == y.weight - y.length) {
                return y.weight - x.weight;
            } else {
                return (y.weight - y.length) - (x.weight - x.length);
            }
        }
    }

    private PriorityQueue<Job> pq1 = new PriorityQueue<Job>(new JobComparator1());
    private PriorityQueue<Job> pq2 = new PriorityQueue<Job>(new JobComparator2());

    public JobSchedule(String inputFileName) throws FileNotFoundException {
        Scanner in = new Scanner(new File(inputFileName));
        int jobNumber = in.nextInt();
        //add all vertices
        while (in.hasNextInt()){
            int weight = in.nextInt();
            int length = in.nextInt();
            pq1.add(new Job(weight, length));
            pq2.add(new Job(weight, length));
        }
    }

    public long getAllSum1() {
        long totalSum = 0;
        long currentTime = 0;

        while(!pq1.isEmpty()) {
            Job current = pq1.poll();

            currentTime += current.length;
            totalSum += current.weight * currentTime;
        }

        return totalSum;
    }

    public long getAllSum2() {
        long totalSum = 0;
        long currentTime = 0;

        while(!pq2.isEmpty()) {
            Job current = pq2.poll();

            currentTime += current.length;
            totalSum += current.weight * currentTime;
        }

        return totalSum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        JobSchedule js = new JobSchedule("resource/jobs.txt");
        System.out.println(js.getAllSum1());
    }

}
