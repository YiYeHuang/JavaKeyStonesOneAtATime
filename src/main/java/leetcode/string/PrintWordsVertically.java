package leetcode.string;

import java.util.LinkedList;
import java.util.List;

/*
1324. Print Words Vertically

Given a string s. Return all the words vertically in the same order in which they appear in s.
Words are returned as a list of strings, complete with spaces when is necessary. (Trailing spaces are not allowed).
Each word would be put on only one column and that in one column there will be only one word.

Example 1:

Input: s = "HOW ARE YOU"
Output: ["HAY","ORO","WEU"]
Explanation: Each word is printed vertically.
 "HAY"
 "ORO"
 "WEU"
Example 2:

Input: s = "TO BE OR NOT TO BE"
Output: ["TBONTB","OEROOE","   T"]
Explanation: Trailing spaces is not allowed.
"TBONTB"
"OEROOE"
"   T"
Example 3:

Input: s = "CONTEST IS COMING"
Output: ["CIC","OSO","N M","T I","E N","S G","T"]


Constraints:

1 <= s.length <= 200
s contains only upper case English letters.
It's guaranteed that there is only one space between 2 words.
 */
public class PrintWordsVertically {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        List<String> result = new LinkedList<>();
        int maxWordLen = 0;

        for (String i : words) {
            maxWordLen = Math.max(maxWordLen, i.length()); // Find max length of word
        }

        // the longest length of words will determine the length of the words
        StringBuilder[] verticalWord = new StringBuilder[maxWordLen];

        for (int i = 0; i < maxWordLen; i++) {
            // go through each word
            for (int j = 0; j < words.length; j++) {
                if (verticalWord[i] == null) {
                    verticalWord[i] = new StringBuilder();
                }

                if (i < words[j].length()) {
                    verticalWord[i].append(words[j].charAt(i));
                } else {
                    // append white space if no words
                    verticalWord[i].append(" ");
                }
            }
        }

        for (StringBuilder sb : verticalWord) {
            int i = sb.length() - 1;
            while (sb.charAt(i) == ' ') {// trim right white space
                sb.deleteCharAt(i);
                i--;
            }
            result.add(sb.toString());
        }
        return result;
    }
}
