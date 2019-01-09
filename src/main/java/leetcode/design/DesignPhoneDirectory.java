package leetcode.design;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Hash;
import leetcode.tag.type.HashTableTag;

import java.util.HashSet;
import java.util.Set;

/**
 379. Design Phone Directory

 Design a Phone Directory which supports the following operations:

 get: Provide a number which is not assigned to anyone.
 check: Check if a number is available or not.
 release: Recycle or release a number.
 Example:

 // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
 PhoneDirectory directory = new PhoneDirectory(3);

 // It can return any available phone number. Here we assume it returns 0.
 directory.get();

 // Assume it returns 1.
 directory.get();

 // The number 2 is available, so return true.
 directory.check(2);

 // It returns 2, the only number that is left.
 directory.get();

 // The number 2 is no longer available, so return false.
 directory.check(2);

 // Release number 2 back to the pool.
 directory.release(2);

 // Number 2 is available again, return true.
 directory.check(2);
 */

@Medium
@HashTableTag
@Hash
public class DesignPhoneDirectory {

	Set<Integer> numbers;

	/** Initialize your data structure here
	 @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
	public DesignPhoneDirectory(int maxNumbers) {

		numbers = new HashSet<>();
		for (int i = 0; i < maxNumbers; i++) {
			numbers.add(i);
		}
	}

	/** Provide a number which is not assigned to anyone.
	 @return - Return an available number. Return -1 if none is available. */
	public int get() {
		if (numbers.isEmpty()) {
			return -1;
		}

		int result = numbers.iterator().next();
		// avoid to get it again
		numbers.remove(result);
		return result;
	}

	/** Check if a number is available or not. */
	public boolean check(int number) {
		return numbers.contains(number);
	}

	/** Recycle or release a number. */
	public void release(int number) {
		numbers.add(number);
	}
}
