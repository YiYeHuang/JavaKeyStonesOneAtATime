package leetcode.design_systemdesign;

import baseObj.TrieNode;
import leetcode.tag.company.Amazon;
import leetcode.tag.company.Apple;
import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.company.Microsoft;
import leetcode.tag.level.Medium;
import leetcode.tag.type.BackTrack;
import leetcode.tag.type.Trie;


/**

 211. Add and Search Word - Data structure design_systemdesign

 Design a data structure that supports the following two operations:

 void addWord(word)
 bool search(word)
 search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

 Example:

 addWord("bad")
 addWord("dad")
 addWord("mad")
 search("pad") -> false
 search("bad") -> true
 search(".ad") -> true
 search("b..") -> true
 */

@Google
@Facebook
@Microsoft
@Amazon
@Apple

@Medium
@Trie
@BackTrack
public class WordDictionary {

	private TrieNode root;

	/** Initialize your data structure here. */
	public WordDictionary() {
		root = new TrieNode();
		root.val = ' ';
	}

	/** Adds a word into the data structure. */
	public void addWord(String word) {
		TrieNode walker = root;
		for (int i = 0; i < word.length(); i++) {
			char insert = word.charAt(i);
			if (walker.children[insert - 'a'] == null) {
				walker.children[insert - 'a'] = new TrieNode(insert);
			}
			walker = walker.children[insert - 'a'];
		}

		walker.wordEndHere = true;
	}

	/** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
	public boolean search(String word) {
		TrieNode walker = root;
		return findMatch(word, 0, walker);
	}

	public boolean findMatch(String word, int level, TrieNode currRoot) {
		if (word.length() == level) {
			if (currRoot.wordEndHere) {
				return true;
			} else {
				return false;
			}
		}

		char search = word.charAt(level);

		if ( search != '.' ) {
			if (currRoot.children[search-'a'] == null) {
				// mismatch
				return false;
			} else {
				// find
				return findMatch(word,level+1,currRoot.children[search-'a']);
			}
		} else {
			// KEY point: find . skip a level
			for (int i = 0; i < 26; i++) {
				if (currRoot.children[i] != null) {
					if (findMatch(word,level+1,currRoot.children[i])) {
						return true;
					}
				}
			}
		}

		// never get here
		return false;
	}
}
