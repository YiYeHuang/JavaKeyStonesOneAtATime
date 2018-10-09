package leetcode.string;


import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.StringModify;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.
 * It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

 Words in the list of banned words are given in lowercase, and free of punctuation.
 Words in the paragraph are not case sensitive.  The answer is in lowercase.

 Example:
 Input:
 paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
 banned = ["hit"]
 Output: "ball"
 Explanation:
 "hit" occurs 3 times, but it is a banned word.
 "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
 Note that words in the paragraph are not case sensitive,
 that punctuation is ignored (even if adjacent to words, such as "ball,"),
 and that "hit" isn't the answer even though it occurs more because it is banned.


 Note:

 1 <= paragraph.length <= 1000.
 1 <= banned.length <= 100.
 1 <= banned[i].length <= 10.
 The answer is unique, and written in lowercase
 (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
 paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
 Different words in paragraph are always separated by a space.
 There are no hyphens or hyphenated words.
 Words only consist of letters, never apostrophes or other punctuation symbols.

 */
@Amazon
@Easy
public class MostCommonWord {

    @StringModify
    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>();
        Map<String, Integer> dictionary = new HashMap<>();

        for (String bannedWords : banned) {
            bannedSet.add(bannedWords);
        }

        int currentStart = 0;
        String currentResult = "";
        int currentMax = 0;

        StringBuilder currentWords = new StringBuilder();

        while (currentStart < paragraph.length()) {
            if ((paragraph.charAt(currentStart) >= 'a' && paragraph.charAt(currentStart) <= 'z') ||
                    (paragraph.charAt(currentStart) >= 'A' && paragraph.charAt(currentStart) <= 'Z')) {
                currentWords.append(paragraph.charAt(currentStart));
                currentStart++;
            } else {
                if (currentWords.length() > 0) {
                    String words = currentWords.toString().toLowerCase();
                    currentWords = new StringBuilder();

                    if (!bannedSet.contains(words)) {
                        if (dictionary.containsKey(words)) {
                            int newValue = dictionary.get(words) + 1;
                            if (newValue > currentMax) {
                                currentMax = newValue;
                                currentResult = words;
                            }
                            dictionary.put(words, newValue);
                        } else {
                            dictionary.put(words, 1);
                            if (1 > currentMax) {
                                currentMax = 1;
                                currentResult = words;
                            }
                        }
                    }

                }
                currentStart++;
            }
        }

        // Flush the rest
        if (currentWords.length() > 0) {
            String words = currentWords.toString().toLowerCase();

            if (!bannedSet.contains(words)) {
                if (dictionary.containsKey(words)) {
                    int newValue = dictionary.get(words) + 1;
                    if (newValue > currentMax) {
                        currentResult = words;
                    }
                    dictionary.put(words, newValue);
                } else {
                    dictionary.put(words, 1);
                    currentResult = words;
                }
            }
        }

        return currentResult;
    }

    public static void main(String[] args) {
        System.out.println(
                mostCommonWord("a.",
                        new String[]{"hit"})
        );
    }
}
