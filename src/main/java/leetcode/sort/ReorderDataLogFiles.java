package leetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Sorting;
import leetcode.tag.type.StringTag;

/*
937. Reorder Data in Log Files

You have an array of logs.  Each log is a space delimited string of words.

For each log, the first word in each log is an alphanumeric identifier.  Then, either:

Each word after the identifier will consist only of lowercase letters, or;
Each word after the identifier will consist only of digits.
We will call these two varieties of logs letter-logs and digit-logs.
It is guaranteed that each log has at least one word after its identifier.

Reorder the logs so that all of the letter-logs come before any digit-log.
The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.
The digit-logs should be put in their original order.

Return the final order of the logs.



Example 1:

Input: logs = [
                "dig1 8 1 5 1",
                "let1 art can",
                "dig2 3 6",
                "let2 own kit dig",
                "let3 art zero"]

Output: ["let1 art can",
         "let3 art zero",
         "let2 own kit dig",
         "dig1 8 1 5 1",
         "dig2 3 6"]


Constraints:

0 <= logs.length <= 100
3 <= logs[i].length <= 100
logs[i] is guaranteed to have an identifier, and a word after the identifier.
 */

@Easy
@StringTag
@Sorting
public class ReorderDataLogFiles {

    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (s1, s2) -> {
            String[] split1 = s1.split(" ", 2);
            String[] split2 = s2.split(" ", 2);

            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));

            if (!isDigit1 && !isDigit2) {
                int comp = split1[1].compareTo(split2[1]);
                if (comp == 0) {
                    // same letter, then compare the entire string
                    return split1[0].compareTo(split2[0]);
                } else {
                    return comp;
                }
            }if (isDigit1 && isDigit2) {
                return 0;
            } else if (isDigit1 && !isDigit2) {
                // letter goes first,
                return 1;
            } else {
                return -1;
            }
        });

        return logs;
    }


    public List<String> reorderLines(int logFileSize, List<String> logLines) {

        if (logFileSize == 0) {
            return new ArrayList<String>();
        }

        // sort list by rules
        logLines.sort((l1, l2) -> {
            String[] splitLog1 = l1.split(" ", 2);
            String[] splitLog2 = l2.split(" ", 2);

            // find out if digit
            boolean isDigitLog1 = Character.isDigit(splitLog1[1].charAt(0));
            boolean isDigitLog2 = Character.isDigit(splitLog2[1].charAt(0));

            // case 1 both not digit
            if (!isDigitLog1 && !isDigitLog2) {
                int comp = splitLog1[1].compareTo(splitLog2[1]);
                if (comp == 0) {
                    // same log message, compare identifier
                    return splitLog1[0].compareTo(splitLog2[0]);
                } else {
                    return comp;
                }
            } else if (isDigitLog1 && isDigitLog2) {
                // both digit, original order
                return 0;
            } else if (isDigitLog1 && !isDigitLog2) {
                // letter goes first
                return 1;
            } else {
                return -1;
            }
        });

        return logLines;
    }
}
