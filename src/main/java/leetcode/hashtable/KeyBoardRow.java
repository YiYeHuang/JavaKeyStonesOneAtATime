package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Hash;

import java.util.ArrayList;

/**

 500. Keyboard Row

 Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.


 Example:

 Input: ["Hello", "Alaska", "Dad", "Peace"]
 Output: ["Alaska", "Dad"]


 Note:

 You may use one character in the keyboard more than once.
 You may assume the input string will only contain letters of alphabet.
 */

@Easy
@Hash
public class KeyBoardRow {

	/**
	 * Typical int[26] hash problem
	 */
	public String[] findWords(String[] words) {
		int[] row = new int[26];
		String row1 = "QWERTYUIOP";
		String row2 = "ASDFGHJKL";
		String row3 = "ZXCVBNM";
		for (int i  = 0; i < row1.length(); i++) {
			row[row1.charAt(i) - 'A'] = 1;
		}

		for (int i  = 0; i < row2.length(); i++) {
			row[row2.charAt(i) - 'A'] = 2;
		}

		for (int i  = 0; i < row3.length(); i++) {
			row[row3.charAt(i) - 'A'] = 3;
		}

		ArrayList<String> result = new ArrayList<>();
		for (String each : words) {
			String temp = each.toUpperCase();
			int check = row[temp.charAt(0) - 'A'];
			boolean add = true;
			for (int i  = 0; i < temp.length(); i++) {
				if (row[temp.charAt(i) - 'A'] != check) {
					add = false;
					break;
				}
			}
			if (add) {
				result.add(each);
			}
		}

		String[] valid = new String[result.size()];
		int i = 0;
		for (String validItem:result) {
			valid[i++] = validItem;
		}

		return valid;
	}

}
