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

### 642. Design Search Autocomplete System (hard)
Trie + Kth most frequent (heap) + Hash Map
Based one trie tree, As the input is dynamic, bucket sort cannot be used

- in the case when a new input is tried multiple times, the bucket will experience out of bounce
  Special character might also used, an array of ascII table each node is very expensive
- need to use dynamic data structure

Special TrieNode is used here
```java
	class TrieNode {
		Map<Character, AutoCompTrieNode> children;
		Map<String, Integer> counts;
		boolean isWord;
		public AutoCompTrieNode() {
			children = new HashMap<Character, AutoCompTrieNode>();
			counts = new HashMap<String, Integer>();
			isWord = false;
		}
	}
```

- Add (trie tree node will contain map instead of array, and a count nmap)
	- build the trie tree, while build the trie tree, build the count map
- Search
 	- *** for each input, unless it is #, rebuild prefix and search the current root
    - trick is to keep a global prefix for continuous search
    - *** Based on each search (with each char from prefix) build find the root in current trie tree
    - *** push all the nodes in count map from the root to the max heap
    - *** build the result of top 3

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
Trie + BFS Queue
- Load the words into a trie tree
- Search is the key part
    - push the root to the a queue
    - for each level, if child not null, and there is a word ending, push to queue again
        - since the longest word is built by other word, the pushed node could also have child
    - search until the queue is empty
```java
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
```

 