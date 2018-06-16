package leetcode.dp;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume
 * that the maximum length of s is 1000.
 * 
 * Example:
 * 
 * Input: "babad" Output: "bab"
 * 
 * Note: "aba" is also a valid answer. 
 * Example: Input: "cbbd" Output: "bb"
 * 
 * 
 */
public class LongestPalindromicSubstring_amzon
{
    // Brute Force: Find all the possible combine
    // can opt with break the for loop, but not working with worst case
    /**
     * 
     * Time complexity : O(n^3)
     * Space complexity : O(1)
     */
    public static String longestPalindrome_BF(String s)
    {
        if (null == s || s.length() == 0) return "";
        if (s.length() == 1) return s;

        String res = null;
        int localMax = 0;

        for (int i = 0; i< s.length(); i++)
        {
            for (int j=i; j<s.length(); j++)
            {
                if (isPalindrome(s,i,j))
                {
                    if (j - i > localMax)
                    {
                        res = s.substring(i, j+1);
                        localMax = j-i;
                    }
                }
            }
        }
        return res;
    }

    private static boolean isPalindrome(String input, int left, int right)
    {
        while (left < right)
        {
            if (input.charAt(left) != input.charAt(right)) return false;
            ++left;
            --right;
        }
        return true;
    }


    /**
     * P(i, j) = P(i,j)=(P(i+1,j−1) and Si==Sj)
     * 
     * abcbd
     * 
     * [[true,  false, false, false, false]
     *  [false, true,  false, true,  false]
     *  [false, false, true,  false, false]
     *  [false, false, false, true,  false]
     *  [false, false, false, false, true]]
     * 
     * 
     */
    public static String longestPalindrome(String s)
    {
        if (null == s || s.length() == 0)
            return "";
        if (s.length() == 1)
            return s;

        int n = s.length();
        String res = null;

        boolean[][] dp = new boolean[n][n];

        // d[i, j] is true only if d[i+1, j] and d[i, j-1] is true
        for (int i = n - 1; i >= 0; i--)
        {
            for (int j = i; j < n; j++)
            {
//                if (j - i > 2)
//                {
//                    System.out.println("Check " +  (i+1) + " and " + (j-1));
//                }
                // always check the left down corner.
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]))
                {
                    dp[i][j] = true;
                }

                if (dp[i][j] && (res == null || j - i + 1 > res.length()))
                {
                    res = s.substring(i, j + 1);
                }
            }
        }

        return res;
    }

    public static void main(String[] args)
    {
        System.out.println("DP solution: " + longestPalindrome("abbzcbbd"));
        System.out.println("Brute Force: " + longestPalindrome_BF("bccabbacfgh"));
    }
}
