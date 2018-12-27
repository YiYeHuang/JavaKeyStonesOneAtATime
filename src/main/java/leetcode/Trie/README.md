## Trie Notes
Best for prefix suffix search and char by char string match

TrieNode
```java 
public class TrieNode {
	public char val;
	public boolean wordEndHere;
	public String word;
	public TrieNode[] children = new TrieNode[26];

	public TrieNode(char c){
		val = c;
	}
}
```

Build Trie: depends on the use case, either just flag true or assign the word.
```java
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
    ws.word = word;
}
```

### 211. Add and Search Word
Trie + recursive search

- add
    - build the trie tree
- search
    - if letter, search and continue
    - if . skip this layer and continue continue with all 26 child if not null.

### 648. Replace Words
Trie
- Build the Trie Tree with input dictionary words
- Split the sentence with space and pass each word to trie tree
- rebuild the sentence with the replaced word found in trie tree

### 692. Top K Frequent Words
Trie + Bucket Sort
- Load the words into a hash map. Word/Frequency
- Build the bucket list of TrieNode same as the input stream length;
- build trie on each entry
- Go through the bucket trie until gets K word

Key: since the trie check is sort by alphabetical order, no sort is needed.

### 720. Longest Word in Dictionary
Trie + BFS
- Load the words into a trie tree
- Search is the key part
    - push the root to the a queue
    - for each level, if child not null, and there is a word ending, push to queue again
        - since the longest word is built by other word, the pushed node could also have child
    - search until the queue is empty

 