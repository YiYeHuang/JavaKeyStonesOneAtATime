package algorithm.dataStructure.hashTable;

import algorithm.dataStructure.floatingMedian.FloatingMedian;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * The goal of this problem is to implement a variant of the 2-SUM algorithm covered in this week's lectures.
 *
 * The file contains 1 million integers, both positive and negative (there might be some repetitions!).
 * This is your array of integers, with the i^{th}ith
 *   row of the file specifying the i^{th}ith
 *   entry of the array.
 *
 * Your task is to compute the number of target values tt in the interval [-10000,10000]
 * (inclusive) such that there are distinct numbers x,yx,y in the input file that satisfy x+y=tx+y=t.
 * (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 *
 * Write your numeric answer (an integer between 0 and 20001) in the space provided.
 *
 * OPTIONAL CHALLENGE: If this problem is too easy for you, try implementing your own hash table for it.
 * For example, you could compare performance under the chaining and open addressing approaches to resolving collisions.
 */
public class twoSum {
    Map<Long, ArrayList<Long>> nums = new HashMap<>();
    long[] numAll = new long[1000000];

    public twoSum(String inputFile) throws FileNotFoundException {
        int result = 0;
        Scanner in = new Scanner(new File(inputFile));

        // The range of the input is roughly from -1*10^11 to 1*10^11,
        // within file there are 1000000 file
        // so number of buckets should be 50
        int index = 0;
        while (in.hasNextLong()){
            long value = in.nextLong();
            long key = value / 20000;
            if (nums.containsKey(key)) {
                nums.get(key).add(key);
            } else {
                ArrayList<Long> newList = new ArrayList<>();
                newList.add(value);
                nums.put(key, newList);
            }
            numAll[index] = value;
            index++;
        }

        HashSet<Long> set = new HashSet<>();
        for (long target : numAll) {
            List<Long> rangeAll = new ArrayList<>();
            long key = target/20000;
            // to find the oppsite value, the most likely is find in
            // -key, -key + 1 and -key -1
            if (nums.containsKey(-key)) {
                rangeAll.addAll(nums.get(-key));
            }
            if (nums.containsKey(-key - 1)) {
                rangeAll.addAll(nums.get(-key - 1));
            }
            if (nums.containsKey(-key + 1)) {
                rangeAll.addAll(nums.get(-key + 1));
            }

            for (long inRange : rangeAll) {
                long sum = target + inRange;
                if (sum > -10000 && sum < 10000) {
                    if (!set.contains(sum)) {
                        set.add(sum);
                        result++;
                    }
                }
            }
        }

        System.out.println(result);
    }


    public static void main(String[] args) throws FileNotFoundException{
        //Graph g = new Graph("SimpleInput.txt");
        String inputFile = "resource/2sum.txt";
        twoSum s2 = new twoSum(inputFile);
    }
}
