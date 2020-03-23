package leetcode.trie.basicObj;

public class TrieNodeWithWord {
	public char val;
	public boolean wordEndHere;
	public String word;
	public TrieNodeWithWord[] children = new TrieNodeWithWord[26];

	public TrieNodeWithWord() { }

	public TrieNodeWithWord(char c){
		TrieNodeWithWord node = new TrieNodeWithWord();
		node.val = c;
	}
}
