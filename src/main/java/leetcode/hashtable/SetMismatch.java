package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

/*
645. Set Mismatch

The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.
 */

@Easy
@HashTableTag
public class SetMismatch {

  // bit mask, freq table
  public int[] findErrorNums(int[] nums) {

    int[] ans = new int[2];
    int n = nums.length;
    int[] freq = new int[n+1];

    for(int i: nums) {
      freq[i]++;
    }

    for(int i = 1; i <= freq.length - 1; i++) {
      if(freq[i] == 2) ans[0] = i;
      if(freq[i] == 0) ans[1] = i;
    }

    return ans;
  }

}
