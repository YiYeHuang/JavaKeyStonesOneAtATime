package leetcode.hashtable;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;
import leetcode.tag.type.HashTableTag;

import java.util.HashSet;
import java.util.Set;

/**
 771. Jewels and Stones
 Easy
 1086
 200


 You're given strings J representing the types of stones that are jewels, and S representing the stones you have.  Each character in S is a type of stone you have.  You want to know how many of the stones you have are also jewels.

 The letters in J are guaranteed distinct, and all characters in J and S are letters. Letters are case sensitive, so "a" is considered a different type of stone from "A".

 Example 1:

 Input: J = "aA", S = "aAAbbbb"
 Output: 3
 Example 2:

 Input: J = "z", S = "ZZ"
 Output: 0

 */

@Amazon

@Easy
@Hash
@HashTableTag
public class JewelsAndStone {
	/**
	 * basic hashset way figure out the duplicate, good basic solution, but search cost O(logn)
	 */
	public int numJewelsInStones(String J, String S) {
		Set<Character> jewel = new HashSet<>();

		for (int i= 0; i < J.length(); i ++) {
			jewel.add(J.charAt(i));
		}

		int result = 0;
		for (int i= 0; i < S.length(); i ++) {
			if (jewel.contains(S.charAt(i))){
				result++;
			}
		}

		return result;
	}

	/**
	 * USE ASCII hash mapping,
	 * since a and A are different, cannot use int[26]
	 */
	public int numJewelsInStonesHASH(String J, String S) {
		int cntJewels = 0;
		if (J == null || J.length() == 0 || S == null || S.length() == 0) {
			return cntJewels;
		}
		// ASCII hash mapping
		int[] hash = new int[256];
		for (char ch : S.toCharArray()) {
			hash[ch]++;
		}
		for (char ch : J.toCharArray()) {
			if (hash[ch] != 0) {
				cntJewels += hash[ch];
				hash[ch] = 0;
			}
		}
		return cntJewels;
	}
}
