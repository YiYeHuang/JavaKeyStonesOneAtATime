package leetcode.hashtable;

/*
1002. Find Common Characters

Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.

You may return the answer in any order.



Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: ["cool","lock","cook"]
Output: ["c","o"]


Note:

1 <= A.length <= 100
1 <= A[i].length <= 100
A[i][j] is a lowercase letter
 */

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.ArrayList;
import java.util.List;

@HashTableTag
@Easy
public class FindCommonCharacters {

    public static List<String> commonChars(String[] A) {

        List<String> result = new ArrayList<>();
        int[] dict = new int[26];

        for (int i = 0; i < A[0].length(); i++) {
            dict[A[0].charAt(i) - 'a']++;
        }

        for (int i =1; i < A.length; i ++) {
            int[] current = new int[26];

            for (int j = 0; j < A[i].length(); j++) {
                current[A[i].charAt(j) - 'a']++;
            }

            // remove the not exist one
            for (int j = 0; j < 26; j++) {
                if (current[j] < dict[j]) dict[j] = current[j];
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < dict[i]; j++) {
                result.add(Character.toString((char) ('a' + i)));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] test = {"bella","label","roller"};
        commonChars(test);
    }
}
