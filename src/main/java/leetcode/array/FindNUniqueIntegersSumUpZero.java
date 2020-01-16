package leetcode.array;


/*
1304. Find N Unique Integers Sum up to Zero

Given an integer n, return any array containing n unique integers such that they add up to 0.



Example 1:

Input: n = 5
Output: [-7,-1,1,3,4]
Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
Example 2:

Input: n = 3
Output: [-1,0,1]
Example 3:

Input: n = 1
Output: [0]
 */

import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;

@Easy
@ArrayTag
public class FindNUniqueIntegersSumUpZero {

  public int[] sumZero(int n) {
    int[] arr = new int[n];

    int num = n/2;
    int index = 0;

    while ((num) > 0) {
      arr[index] = num;
      index++;
      arr[index] = num * -1;
      num--;
    }

    if(n%2 == 1) {
      arr[index] = 0;
    }

    return arr;
  }

}
