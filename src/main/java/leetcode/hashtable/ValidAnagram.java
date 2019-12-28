package leetcode.hashtable;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;

/**
 242. Valid Anagram
 Easy
 524
 87


 Given two strings s and t , write a function to determine if t is an anagram of s.

 Example 1:

 Input: s = "anagram", t = "nagaram"
 Output: true
 Example 2:

 Input: s = "rat", t = "car"
 Output: false
 Note:
 You may assume the string contains only lowercase alphabets.

 Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

@Amazon

@Easy
@Hash
public class ValidAnagram {

	/**
	 * Typical int[26] hash
	 */
	public boolean isAnagram(String s, String t) {
		int[] range = new int[26];

		for (int i =0; i < s.length(); i++) {
			range[s.charAt(i) - 'a']++;
		}

		for (int i =0; i < t.length(); i++) {
			range[t.charAt(i) - 'a']--;
		}

		for (int i =0; i < range.length; i++) {
			if (range[i] != 0) {
				return false;
			}
		}

		return true;
	}
}
