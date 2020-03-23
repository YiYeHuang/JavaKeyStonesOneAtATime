package leetcode.design_systemdesign;

import leetcode.trie.basicObj.TrieNode;
import leetcode.trie.basicObj.TrieNodeWithWord;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Trie;

/**

 208. Implement TrieDesign (Prefix Tree)

 Favorite

 Share
 Implement a trie with insert, search, and startsWith methods.

 Example:

 TrieDesign trie = new TrieDesign();

 trie.insert("apple");
 trie.search("apple");   // returns true
 trie.search("app");     // returns false
 trie.startsWith("app"); // returns true
 trie.insert("app");
 trie.search("app");     // returns true
 Note:

 You may assume that all inputs are consist of lowercase letters a-z.
 All inputs are guaranteed to be non-empty strings.
 */

@Medium
@Trie
public class TrieDesign {

	private TrieNode root;

	/** Initialize your data structure here. */
	public TrieDesign() {
		root = new TrieNode();
		root.val = ' ';
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		TrieNode ws = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(ws.children[c - 'a'] == null){
				ws.children[c - 'a'] = new TrieNode(c);
			}
			ws = ws.children[c - 'a'];
		}
		ws.wordEndHere = true;
	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		TrieNode ws = root;
		for(int i = 0; i < word.length(); i++){
			char c = word.charAt(i);
			if(ws.children[c - 'a'] == null) return false;
			ws = ws.children[c - 'a'];
		}
		return ws.wordEndHere;
	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		TrieNode ws = root;
		for(int i = 0; i < prefix.length(); i++){
			char c = prefix.charAt(i);
			if(ws.children[c - 'a'] == null) return false;
			ws = ws.children[c - 'a'];
		}
		return true;
	}
}
