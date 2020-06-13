package leetcode.sort.bucketSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import leetcode.trie.basicObj.TrieNodeWithWord;
import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Heap;
import leetcode.tag.type.Trie;


/**
 * 692. Top K Frequent Words
 *
 * Favorite
 *
 * Share
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 *     Note that "i" comes before "love" due to a lower alphabetical order.
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 *     with the number of occurrence being 4, 3, 2 and 1 respectively.
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 */

@Medium
@HashTableTag
@Heap
@Trie
public class TopKFrequentWords {

    /**
     * Tree map solution
     *
     * Key is the linked list
     * This solution is applied from top K frequent elements, however, the tree map does not solve the problem that
     * when count is the same, it does not sort the word properly
     */
    public List<String> topKFrequentTreeMap(String[] words, int k) {
        if(words.length == 0) return new ArrayList<>();
        // Mapping word to frequency
        Map<String, Integer> map = new HashMap<>();
        for(String n: words){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        TreeMap<Integer, List<String>> freqMap = new TreeMap<>();
        for(String word : map.keySet()){
            int freq = map.get(word);
            if (!freqMap.containsKey(freq)) {
                freqMap.put(freq, new LinkedList<>());
            }
            freqMap.get(freq).add(word);
        }


        List<String> result = new ArrayList<>();
        while(result.size()<k){
            Map.Entry<Integer, List<String>> entry = freqMap.pollLastEntry();
            result.addAll(entry.getValue());
        }
        Collections.sort(result);
        return result;
    }

    /**
     *  define a trie within each bucket to store all the words with the same frequency.
     *  With trie, it ensures that the lower alphabetical word will be met first,
     *  saving the trouble to sort the words within the bucket.
     */
    public List<String> topKFrequentBucketAndTrie(String[] words, int k) {
        if(words.length == 0) return new ArrayList<>();
        // Mapping word to frequency
        Map<String, Integer> map = new HashMap<>();
        for(String n: words){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        // build the buckets and trie
        TrieNodeWithWord[] bucketRoot = new TrieNodeWithWord[words.length + 1];
        for(String word : map.keySet()) {
            int freq = map.get(word);
            if(bucketRoot[freq] == null) {
                bucketRoot[freq] = new TrieNodeWithWord();
            }
            addWord(bucketRoot[freq], word);
        }

        // get k frequent work
        List<String> result = new LinkedList<>();
        // get from the end of the list
        for(int f = bucketRoot.length - 1; f >= 1 && result.size() < k; f--) {
            if(bucketRoot[f] == null) {
                continue;
            }
            getwords(bucketRoot[f], result, k);
        }

        return result;
    }

    private void getwords(TrieNodeWithWord node, List<String> list, int k) {
        if(node == null) return;
        if(node.word != null) {
            list.add(node.word);
        }
        if(list.size() == k) return;
        for(int i = 0; i < 26; i++) {
            if(node.children[i] != null) {
                getwords(node.children[i], list, k);
            }
        }
    }

    private boolean addWord(TrieNodeWithWord root, String word) {
        TrieNodeWithWord curr = root;
        for(char c : word.toCharArray()) {
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNodeWithWord();
            }
            curr = curr.children[c - 'a'];
        }
        curr.word = word;
        return true;
    }
}
