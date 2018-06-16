package leetcode.dp;

/**
 * Given a string s, find the longest palindromic subsequence's length in s. You
 * may assume that the maximum length of s is 1000.
 * 
 * Example 1: Input:
 * "bbbab" 
 * Output: 4 One possible longest palindromic subsequence is "bbbb".
 * 
 * 
 * Example 2: Input:
 * "cbbd" 
 * Output: 2
 */
public class LongestPalindromicSubsequence_amazon
{

    /**
     * asbbsbbac
     * 
     * if char at i == char at j
     * d(i,j) = d(i+1, j-1) + 2
     * else
     * d(i, j) = Max( d[i+1][j], d[i][j-1]);
     */
    public static int longestPalindromeSubseq(String s)
    {
        int[][] dp = new int[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--)
        {
            dp[i][i] = 1;
            for (int j = i + 1; j < s.length(); j++)
            {
                if (s.charAt(i) == s.charAt(j))
                {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
                else
                {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][s.length() - 1];
    }


    public static int longestPalindromeSubseqForce(String s)
    {
        return longestPalindromeSubseqForceHelp(0,s.length()-1,s); 
    }
    
    static int longestPalindromeSubseqForceHelp(int l, int r, String s) {
        if(l==r) return 1;
        if(l>r) return 0;  //happens after "aa" 
        
        if (s.charAt(l)== s.charAt(r))
        {
            return 2 + longestPalindromeSubseqForceHelp(l+1,r-1, s);
        }
        else
        {
            return Math.max(
                    longestPalindromeSubseqForceHelp(l+1,r, s),
                    longestPalindromeSubseqForceHelp(l,r-1, s));
        } 
    }

    public static void main(String[] args)
    {
        longestPalindromeSubseq("acbbbzad");
    }
}
