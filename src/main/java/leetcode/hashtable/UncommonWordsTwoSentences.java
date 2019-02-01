package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 884. Uncommon Words from Two Sentences
 *
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 *
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Return a list of all uncommon words.
 *
 * You may return the list in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: A = "this apple is sweet", B = "this apple is sour"
 * Output: ["sweet","sour"]
 * Example 2:
 *
 * Input: A = "apple apple", B = "banana"
 * Output: ["banana"]
 *
 *
 * Note:
 *
 * 0 <= A.length <= 200
 * 0 <= B.length <= 200
 * A and B both contain only spaces and lowercase letters.
 */

@Easy
@HashTableTag
public class UncommonWordsTwoSentences {

    /**
     * Use 2 HashSets to store distinct and common Strings.
     *
     * If encounter a String that is not in either Set, add it to distinct;
     * Otherwise, remove it from the distinct and add it to the common one.
     * In other words:
     * a) first check if the com Set contains the String s,
     * if yes, go to b);
     * if no, check if distinct Set can add s, if yes, no further operation; if no, go to b).
     *
     * b) remove s from distinct and add it the com Set
     */
    public String[] uncommonFromSentences(String A, String B) {
        Set<String> distinct = new HashSet<>(), com = new HashSet<>();
        for (String s : (A + " " + B).split("\\s")) {
            if (com.contains(s) || !distinct.add(s)) { distinct.remove(s); com.add(s); }
        }
        return distinct.toArray(new String[0]);
    }

    /**
     * filter out all map key that is more than one
     */
    public String[] uncommonFromSentencesSteam(String A, String B) {
        Map<String, Integer> count = new HashMap<>();
        for (String s : (A + " " + B).split("\\s")) {
            count.put(s, count.getOrDefault(s, 0) + 1);
        }
        return count.entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> e.getKey())
                .toArray(String[]::new);
    }
}
