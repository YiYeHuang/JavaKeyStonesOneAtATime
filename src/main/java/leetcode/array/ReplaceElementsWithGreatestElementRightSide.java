package leetcode.array;

import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.Mathematics;

/*
1299. Replace Elements with Greatest Element on Right Side

Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array.



Example 1:

Input: arr = [17,18,5,4,6,1]
Output: [18,6,6,6,1,-1]


Constraints:

1 <= arr.length <= 10^4
1 <= arr[i] <= 10^5
 */


@Easy
@ArrayTag
public class ReplaceElementsWithGreatestElementRightSide {

    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int currentMax = arr[arr.length - 1];

        for (int i = arr.length - 1; i >= 0; i--) {
            if (i==arr.length - 1) {
                arr[arr.length - 1] = -1;
            } else {
                int temp = arr[i];
                arr[i] = currentMax;
                currentMax = Math.max(currentMax, temp);
            }
        }

        return arr;
    }

}
