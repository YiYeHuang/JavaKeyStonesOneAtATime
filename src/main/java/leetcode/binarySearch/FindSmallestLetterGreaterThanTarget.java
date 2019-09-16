package leetcode.binarySearch;


import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

import java.util.Arrays;

/*
744. Find Smallest Letter Greater Than Target

Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
 */

@Easy
@BinarySearch
public class FindSmallestLetterGreaterThanTarget {

    public char nextGreatestLetter(char[] letters, char target) {

        Arrays.sort(letters);

        if (target < letters[0] || target >= letters[letters.length - 1]) return letters[0];

        int low = 0; int high = letters.length - 1;

        while (low <= high) {
            int mid = low + (high - low)/2;

            // we want the target 1 index larger than low
            if (letters[mid] <= target) {
                low = mid + 1;
            } else if (letters[mid] > target) {
                high = mid - 1;
            }
        }

        // we want the target 1 index larger than low
        return letters[low];
    }
}
