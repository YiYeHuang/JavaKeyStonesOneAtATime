package leetcode.string;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * Longest common prefix for a pair of strings S1 and S2 is the longest string S which is the prefix
 * of both S1 and S2.
 * 
 * As an example, longest common prefix of "abcdefgh" and "abcefgh" is "abc".
 * 
 * Given the array of strings, you need to find the longest S which is the prefix of ALL the strings
 * in the array.
 */
public class LongestCommonPrefixString
{
    /**
     * good part of the algorithm is that, find the shortest part of a bowl. 
     * 
     * keep reducing the length
     */
    public String longestCommonPrefix(String[] strs)
    {
        if (null == strs || strs.length == 0) return "";
        
        String key = strs[0];

        int count = 1;
        while (count < strs.length)
        {
            while (strs[count].indexOf(key) != 0)
            {
                key = key.substring(0, key.length() - 1);
            }
            count++;
        }

        return key;
    }

}
