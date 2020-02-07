package leetcode.hashtable;

import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
1342. Reduce Array Size to The Half

Given an array arr.  You can choose a set of integers and remove all the occurrences of these integers in the array.
Return the minimum size of the set so that at least half of the integers of the array are removed.

Example 1:

Input: arr = [3,3,3,3,5,5,5,2,2,7]
Output: 2
Explanation: Choosing {3,7} will make the new array [5,5,5,2,2] which has size 5 (i.e equal to half of the size of the old array).
Possible sets of size 2 are {3,5},{3,2},{5,2}.
Choosing set {2,7} is not possible as it will make the new array [3,3,3,3,5,5,5] which has size greater than half of the size of the old array.
Example 2:

Input: arr = [7,7,7,7,7,7]
Output: 1
Explanation: The only possible set you can choose is {7}. This will make the new array empty.
Example 3:

Input: arr = [1,9]
Output: 1
Example 4:

Input: arr = [1000,1000,3,7]
Output: 1
Example 5:

Input: arr = [1,2,3,4,5,6,7,8,9,10]
Output: 5

Constraints:

1 <= arr.length <= 10^5
arr.length is even.
1 <= arr[i] <= 10^5
 */

@Medium
@HashTableTag
public class ReduceArraySizetoHalf {
    public static  int minSetSize(int[] arr) {
        if (arr.length == 1) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i],map.get(arr[i])+1);
            } else {
                map.put(arr[i],1);
            }
        }

        if (map.size() == 1) return 1;

        // value count
        int counter = 0;
        int[] pair = new int[map.size()];
        for (Map.Entry<Integer, Integer> a : map.entrySet()) {
            pair[counter] = a.getValue();
            counter++;
        }

        Arrays.sort(pair);
        int currentSize = arr.length;
        int result = 0;
        int index = pair.length - 1;

        while (index >= 0) {
            currentSize -= pair[index];
            result++;
            if (currentSize <= arr.length/2) {
                break;
            }
            index--;
        }

        return result;
    }
}
