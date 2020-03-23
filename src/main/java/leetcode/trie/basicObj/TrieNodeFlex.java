package leetcode.trie.basicObj;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeFlex {
    public boolean wordEndHere;
    public Map<Character, TrieNodeFlex> children;

    public TrieNodeFlex() {
        children = new HashMap<>();
    }
}
