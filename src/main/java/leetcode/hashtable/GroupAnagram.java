package leetcode.hashtable;

import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams

 Given an array of strings, group anagrams together.

 Example:

 Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Output:
 [
 ["ate","eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 */

@Medium
@HashTableTag
public class GroupAnagram {

	// make the general case as the key,
	// for each compare, sort the word
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> group = new HashMap<>();
		for (int i = 0; i<strs.length; i++) {
			char[] ca = strs[i].toCharArray();
			Arrays.sort(ca);
			String keyStr = String.valueOf(ca);
			if(!group.containsKey(keyStr)) {
				group.put(keyStr, new ArrayList<>());
			}
			group.get(keyStr).add(strs[i]);
		}

		return new ArrayList<List<String>>(group.values());
	}
}
