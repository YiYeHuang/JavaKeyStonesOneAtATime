package leetcode.string;

/**
 * Given a string, your task is to count how many palindromic substrings in this
 * string.
 * 
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 * 
 * Example 1: 
 * Input: "abc" Output: 3 
 * Explanation: Three palindromic strings:
 * "a", "b", "c". 
 * 
 * Example 2: 
 * Input: "aaa" 
 * Output: 6 
 * Explanation: Six palindromic
 * strings: "a", "a", "a", "aa", "aa", "aaa".
 */
public class PalindromicSubstrings
{
    public static int countSubstrings(String s)
    {
        if (null == s || s.length() == 0)
            return 0;
        if (s.length() == 1)
            return 1;

        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];
        
        int counter = 0;

        for (int i = n - 1; i >= 0; i--)
        {
            for (int j = i; j < n; j++)
            {
                // always check the left down corner.
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]))
                {
                    dp[i][j] = true;
                    counter++;
                }
            }
        }

        return counter;
    }

    public static void main(String[] args)
    {
        System.out.println(countSubstrings("aaa"));
    }
}
