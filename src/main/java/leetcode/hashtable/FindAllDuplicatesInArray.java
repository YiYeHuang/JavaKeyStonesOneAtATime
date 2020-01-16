package leetcode.hashtable;

import java.util.ArrayList;
import java.util.List;
import leetcode.tag.level.Easy;
import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;

/*
442. Find All Duplicates in an Array

Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements that appear twice in this array.

Could you do it without extra space and in O(n) runtime?

Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]
 */

@HashTableTag
@Medium
public class FindAllDuplicatesInArray {

  public static List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();


    for (int i = 0; i < nums.length; ++i) {
      int index = Math.abs(nums[i])-1;
      if (nums[index] < 0) {

        // a perfect nun missing array, would always mark all array to neg
        result.add(Math.abs(index+1));
      } else {

        nums[index] = -nums[index];
      }
    }

//    for (int i = 0; i < nums.length; i++) {
//
//      if (nums[i] > 0) {
//        result.add(nums.length - i - 1);
//      }
//    }

    return result;
  }

  public static void main(String[] args) {
    int[] test = {4,3,2,7,8,2,3,1};
    findDuplicates(test);
  }

}
