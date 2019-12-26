package leetcode.binaryIndexTree;

import algorithm.search.BinaryIndexTree;
import leetcode.tag.level.Hard;
import leetcode.tag.type.BinaryIndexTreeTag;

import java.util.*;

/*
315. Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array.
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.
 */

@Hard
@BinaryIndexTreeTag
public class CountSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {

        int[] sortedList = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sortedList.length; i++) {
            if (i == 0 || sortedList[i] != sortedList[i - 1]) {
                ranks.put(sortedList[i], ++rank);
            }
        }

        BinaryIndexTree tree = new BinaryIndexTree(ranks.size());
        List<Integer> result = new ArrayList<Integer>();

        // build BIT while go through the list in reverse order
        for (int i = nums.length - 1; i >= 0; i++) {
            int sum = tree.sum(ranks.get(nums[i]) - 1);
            result.add(tree.sum(ranks.get(nums[i]) - 1));
            tree.update(ranks.get(nums[i]), 1);
        }

        Collections.reverse(result);
        return result;
    }
}
