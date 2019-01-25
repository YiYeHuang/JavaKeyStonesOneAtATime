package leetcode.design;

import leetcode.tag.type.ArrayTag;

import java.util.Arrays;

/**
 705. Design HashSet
 Easy

 85

 28

 Favorite

 Share
 Design a HashSet without using any built-in hash table libraries.

 To be specific, your design should include these functions:

 add(value): Insert a value into the HashSet.
 contains(value) : Return whether the value exists in the HashSet or not.
 remove(value): Remove a value in the HashSet. If the value does not exist in the HashSet, do nothing.

 Example:

 MyHashSet hashSet = new MyHashSet();
 hashSet.add(1);
 hashSet.add(2);
 hashSet.contains(1);    // returns true
 hashSet.contains(3);    // returns false (not found)
 hashSet.add(2);
 hashSet.contains(2);    // returns true
 hashSet.remove(2);
 hashSet.contains(2);    // returns false (already removed)

 Note:

 All values will be in the range of [0, 1000000].
 The number of operations will be in the range of [1, 10000].
 Please do not use the built-in HashSet library.

 */
public class DesignHashSet {
	int[] hashset;
	/** Initialize your data structure here. */
	public DesignHashSet() {
		hashset = new int[1000001];
		Arrays.fill(hashset, -1);
	}

	public void add(int key) {
		hashset[key] = 1;
	}

	public void remove(int key) {
		hashset[key] = -1;
	}

	/** Returns true if this set contains the specified element */
	public boolean contains(int key) {
		return hashset[key] == 1;
	}
}
