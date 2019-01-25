package leetcode.twopointer;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;
import leetcode.tag.type.TwoPointer;

/**
 917. Reverse Only Letters

 Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.



 Example 1:

 Input: "ab-cd"
 Output: "dc-ba"
 Example 2:

 Input: "a-bC-dEf-ghIj"
 Output: "j-Ih-gfE-dCba"
 Example 3:

 Input: "Test1ng-Leet=code-Q!"
 Output: "Qedo1ct-eeLg=ntse-T!"


 Note:

 S.length <= 100
 33 <= S[i].ASCIIcode <= 122
 S doesn't contain \ or "
 */

@Easy
@StringTag
@TwoPointer
public class ReverseOnlyLetters {

	/**
	 * TwoPointer escape non character
	 */
	public String reverseOnlyLetters(String S) {
		char[] chars = S.toCharArray();
		int lo = 0, hi = S.length() - 1;

		while (lo < hi) {
			while (lo < hi && !Character.isLetter(chars[lo])) {
				lo++;
			}
			while (lo < hi && !Character.isLetter(chars[hi])) {
				hi--;
			}
			swap(chars, lo, hi);
			lo++;
			hi--;
		}

		return String.valueOf(chars);
	}

	private void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}
}
