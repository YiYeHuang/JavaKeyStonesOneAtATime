package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
541. Reverse String II

Given a string and an integer k,
you need to reverse the first k characters for every 2k characters counting from the start of the string.
If there are less than k characters left, reverse all of them.
If there are less than 2k but greater than or equal to k characters,
then reverse the first k characters and left the other as original.

Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]
 */

@Easy
@StringTag
public class ReverseWordInStringIII {

    public String reverseWords(String s) {
        char[] arr = s.toCharArray();

        int reverseStartHere = 0;
        for (int i = 0; i < s.length(); i++)
        {
            if (arr[i] == ' ')
            {
                swap(arr, reverseStartHere, i-1);
                reverseStartHere = i+1;
            } else if (i == s.length() - 1) {
                swap(arr, reverseStartHere, i-1);
            }
        }

        return String.valueOf(arr);
    }

    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
