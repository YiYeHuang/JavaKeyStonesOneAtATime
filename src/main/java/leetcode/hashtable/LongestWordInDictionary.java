package leetcode.hashtable;

import baseObj.TrieNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Trie;

import java.util.*;

/**
 * 720. Longest Word in Dictionary
 *
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 * Example 1:
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 */

@Google
@Amazon

@Easy
@Trie
@HashTableTag
public class LongestWordInDictionary {

    /**
     * Non trie easy solution
     * - Sort the words alphabetically, therefore shorter words always comes before longer words;
     * - Along the sorted list, populate the words that can be built;
     * - Any prefix of a word must comes before that word.
     */
    public String longestWordHashTable(String[] words) {
        Arrays.sort(words);
        Set<String> search = new HashSet<String>();
        String result = "";

        for (String word : words) {
            if (word.length() == 1 || search.contains(word.substring(0, word.length() - 1))) {
                result = word.length() > result.length()? word:result;
                search.add(word);
            }
        }

        return result;
    }

    /**
     * trie solution
     * Build trie, BFS to find the longest path
     */
    private TrieNode root;

    public String longestWordTrie(String[] words) {
        TrieNode trie = new TrieNode();
        for (String word : words) {
            insert(word);
        }

        return findLongestWord();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new TrieNode();
            }
            node = node.children[idx];
        }
        node.wordEndHere = true;
        node.word = word;
    }

    /**
     * BFS keep push same layer of nodes into queue, and scan them one by one
     *
     */
    public String findLongestWord() {
        String result = null;
        Queue<TrieNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TrieNode node = queue.poll();
                for (int j = 25; j >= 0; j--) {
                    if (node.children[j] != null && node.children[j].wordEndHere) {
                        result = node.children[j].word;
                        queue.offer(node.children[j]);
                    }
                }
            }
        }
        return result;
    }
}
