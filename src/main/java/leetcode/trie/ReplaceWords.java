package leetcode.trie;

import baseObj.TrieNode;
import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * 648. Replace Words
 *
 * In English, we have a concept called root, which can be followed by some other words to form another longer word -
 * let's call this word successor. For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence.
 * You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 * Note:
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 */

@Medium
@Trie
@HashTableTag
public class ReplaceWords {

    /**
     * pretty much the question tells you that you need to build a trie
     */
    public static String replaceWords(List<String> dict, String sentence) {

        TrieNode root = new TrieNode(' ');
        String[] list = sentence.split(" ");
        for (String word : dict) {
            insert(word, root);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String token : list) {
            stringBuilder.append(replace(token, root));
            stringBuilder.append(" ");
        }
        return stringBuilder.substring(0, stringBuilder.length()-1);
    }

    public static String replace(String token, TrieNode root) {
        TrieNode walker = root;
        StringBuilder result = new StringBuilder();

        for (char c:token.toCharArray()) {
            result.append(c);
            if (walker.children[c - 'a'] != null) {
                if (walker.children[c - 'a'].wordEndHere) {
                    return result.toString();
                }
                walker = walker.children[c - 'a'];
            } else {
                return token;
            }
        }
        return token;
    }

    public static void insert(String word, TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.wordEndHere = true;
    }

    public static void main(String[] args) {
        String[] words  = {"cat", "bat", "rat"};
        String sentence =  "the cattle was rattled by the battery";

        ArrayList<String> test = new ArrayList<>();
        test.add("cat");
        test.add("bat");
        test.add("rat");

        replaceWords(test, sentence);
    }
}
