package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashSet;

/**
 Happy Number

 Write an algorithm to determine if a number is "happy".

 A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

 Example:

 Input: 19
 Output: true
 Explanation:
 12 + 92 = 82
 82 + 22 = 68
 62 + 82 = 100
 12 + 02 + 02 = 1

 */

@Easy
@HashTableTag
public class HappyNumber {

	/**
	 * pretty mush use the hashset to store the previous answer
	 *
	 * key is, if contains, means there is a cycle contains, operation will never finish
	 */
	public static boolean isHappy(int n) {
		HashSet<Integer> set = new HashSet<Integer>();
		set.add(n);
		while (n != 1) {
			int result = 0;
			while (n != 0) {
				result += Math.pow(n % 10, 2);
				n /= 10;
			}
			if (set.contains(result)) {
				return false;
			}
			set.add(result);
			n = result;
		}
		return true;
	}


	/**
	 * O(1) space ================================
	 */

	/**   _____________________________________________
	 *   |                                             |
	 *   V       									   |
	 *  16 - > 37 - > 58 -> 89 -> 145 - > 42 -> 20 - > 4
	 *
	 *  use calculation to do faster/slower "pointer", detect cycle
	 */
	public static boolean isHappyNOEXTRA(int n) {
		int x = n;
		int y = n;
		while(x>1){
			x = cal(x) ;
			if(x==1) return true ;
			y = cal(cal(y));
			if(y==1) return true ;

			if(x==y) return false;
		}
		return true ;
	}
	public static int cal(int n){
		int x = n;
		int s = 0;
		while(x>0){
			s = s+(x%10)*(x%10);
			x = x/10;
		}
		return s ;
	}


	public static void main(String[] args) {
		isHappyNOEXTRA(16);
	}
}
