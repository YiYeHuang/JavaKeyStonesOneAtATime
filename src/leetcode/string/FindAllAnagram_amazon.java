package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagram_amazon
{

    public static List<Integer> findAnagrams(String s, String p)
    {
        int[] chars = new int[26];
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length())
            return result;
        for (char c : p.toCharArray())
            chars[c - 'a']++;

        int start = 0, end = 0, count = p.length();
        // Go over the string
        while (end < s.length())
        {
            // If the char at start appeared in p, we increase count
            if (end - start == p.length() && chars[s.charAt(start++) - 'a']++ >= 0)
            {
                count++;
            }
            // If the char at end appeared in p (since it's not -1 after
            // decreasing), we decrease count
            if (--chars[s.charAt(end++) - 'a'] >= 0)
            {
                count--;
            }
            if (count == 0)
            {
                result.add(start);
            }
        }

        return result;
    }

    public static List<Integer> findAnagrams2(String s, String p)
    {
        int[] chars = new int[26];
        List<Integer> result = new ArrayList<>();

        if (s == null || p == null || s.length() < p.length())
            return result;
        for (char c : p.toCharArray())
            chars[c - 'a']++;

        int start = 0, end = 0, count = p.length();
        // Go over the string
        for (int i = 0; i + p.length() <= s.length(); i++)
        {
            
        }

        return result;
    }

    public static void main(String[] args)
    {
        findAnagrams("abdbab", "ab");
    }
}
