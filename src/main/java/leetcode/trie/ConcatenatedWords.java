package leetcode.trie;

import java.util.ArrayList;
import java.util.List;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Trie;
import leetcode.trie.basicObj.TrieNode;

/*
472. Concatenated Words

Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 */

@Hard
@Trie
public class ConcatenatedWords {

  public List<String> findAllConcatenatedWordsInADict(String[] words) {

    List<String> result = new ArrayList<>();
    if (words == null || words.length == 0) {
      return result;
    }
    TrieNode root = new TrieNode();
    for (String word : words) { // construct Trie tree
      if (word.length() == 0) {
        continue;
      }
      addWord(word, root);
    }

    for (String word : words) { // test word is a concatenated word or not
      if (word.length() == 0) {
        continue;
      }
      if (testWord(word.toCharArray(), 0, root, 0)) {
        result.add(word);
      }
    }
    return result;
  }

  public boolean testWord(char[] words, int index, TrieNode root, int count) {
    TrieNode cur = root;
    int totalLength = words.length;

    for (int i = index; i < totalLength; i++) {
      if (cur.children[words[i] - 'a']==null) return false;

      if(cur.children[words[i] - 'a'].wordEndHere) {
        if (i == totalLength - 1) {
          // last word
          return count >= 1;
        }

        // pass in root cuz we want to check each word from root again
        if (testWord(words, i + 1, root, count + 1)) {
          return true;
        }
      }

      cur = cur.children[words[i] - 'a'];
    }

    return false;
  }

  private void addWord(String str, TrieNode root) {
    char[] chars = str.toCharArray();
    TrieNode cur = root;
    for (char c : chars) {
      if (cur.children[c - 'a'] == null) {
        cur.children[c - 'a'] = new TrieNode();
      }
      cur = cur.children[c - 'a'];
    }
    cur.wordEndHere = true;
  }
}
