package leetcode.string;

import leetcode.tag.company.Facebook;
import leetcode.tag.level.Easy;

/**
 * 680. Valid Palindrome II
 * Easy
 * 534
 * 33
 *
 *
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 * Input: "aba"
 * Output: True
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */
@Facebook
@Easy
public class ValidPalinDromeII {



    public static boolean validPalindrome(String s) {
        int quota = 1;
        int head = 0;
        int tail = s.length() - 1;
        if (null == s || s.length() <= 1) return true;

        while (head <= tail) {
            if (s.charAt(head) == s.charAt(tail))
            {
                head++;
                tail--;
            } else {
                if (quota == 1) {
                    if (head <= tail -1 && isSubPalindrome(s, head, tail - 1)) {
                        tail--;
                        quota--;
                    } else if (head + 1 <= tail && isSubPalindrome(s, head+1, tail)) {
                        head++;
                        quota--;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isSubPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) == s.charAt(end))
            {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.print(validPalindrome("ebcbbececabbacecbbcbe"));
    }

}
