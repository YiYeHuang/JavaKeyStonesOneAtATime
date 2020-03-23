package leetcode.trie;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Trie;
import leetcode.trie.basicObj.TrieNode;
/*
676. Implement Magic Dictionary

Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
Note:
You may assume that all the inputs are consist of lowercase letters a-z.
For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
@Medium
@Trie
public class MagicDictionary {

    TrieNode root;

    public MagicDictionary() {
        root = new TrieNode();
    }

    public void buildDict(String[] dict) {
        for(String s : dict){
            TrieNode curr = root;
            for(char c : s.toCharArray()){
                if(curr.children[c - 'a'] == null){
                    curr.children[c - 'a'] = new TrieNode(c);
                }
                curr = curr.children[c - 'a'];
            }
            curr.wordEndHere = true;
        }
    }

    public boolean search(String word) {
        return dfs(word, 0, 0, root);
    }

    private boolean dfs(String word, int cur, int dff, TrieNode node) {
        if (cur == word.length()) {
            return dff == 1 && node.wordEndHere;
        }

        if (dff > 1) {
            return false;
        }

        char c = word.charAt(cur);

        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null &&
                    dfs(
                            word, cur+1,
                            word.charAt(cur) - 'a' == i ?
                                    dff : dff+1,
                            node.children[i])){
                return true;
            }
        }
        return false;
    }
}
