package leetcode.hashtable;


import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashSet;
import java.util.Set;

/**
 * 575. Distribute Candies

 Given an integer array with even length, where different numbers in this array represent different kinds of candies.
 Each number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and sister.
 Return the maximum number of kinds of candies the sister could gain.

 Example 1:
 Input: candies = [1,1,2,2,3,3]
 Output: 3
 Explanation:
 There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
 Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too.
 The sister has three different kinds of candies.
 Example 2:
 Input: candies = [1,1,2,3]
 Output: 2
 Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1].
 The sister has two different kinds of candies, the brother has only one kind of candies.
 */

@Easy
@HashTableTag
public class DistributeCandies {

	/**
	 * the good part of the question is that the candies are always even
	 * [1,1,2,2,3,3] if evenly distributed, to get the max is to get the most distinct,
	 * so it comes down to either get the most distinct number or, if not enough to split, get the half of the
	 * candies size.
	 */
	public int distributeCandies(int[] candies) {
		Set<Integer> candidate = new HashSet<>();

		for(int num : candies) {
			candidate.add(num);
		}

		return Math.min(candidate.size(), candies.length/2);
	}
}
