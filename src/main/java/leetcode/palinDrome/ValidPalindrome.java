package leetcode.palinDrome;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;
import leetcode.tag.type.TwoPointer;

/**
 Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

 Note: For the purpose of this problem, we define empty string as valid palindrome.

 Example 1:

 Input: "A man, a plan, a canal: Panama"
 Output: true
 Example 2:

 Input: "race a car"
 Output: false
 */

@Easy
@StringTag
@TwoPointer
public class ValidPalindrome
{
    public static boolean isPalindrome1(String s)
    {
        if (null == s || s.length() <= 1) return true;

        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }

    public static boolean isPalindrome2(String s)
    {
        if (null == s || s.length() <= 1) return true;

        s = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        for (int i =0; i<s.length()/2; i++ )
        {
            if (s.charAt(i) != s.charAt(s.length() - i -1))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        String test = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        long startTime = System.nanoTime();
        System.out.println(isPalindrome1(test));
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
