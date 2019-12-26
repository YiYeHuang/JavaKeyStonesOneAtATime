package leetcode.string;

import leetcode.tag.level.Medium;
import leetcode.tag.type.StringTag;

/**
 * Given an input string, reverse the string word by word.
 * 
 * For example, Given s = "the sky is blue", return "blue is sky the".
 */

@Medium
@StringTag
public class ReverseWordsString
{
    public static String reverseWords(String s)
    {
        if (null == s)
        {
            return s;
        }
        String[] slist = s.trim().split("\\s+");
        if (slist.length == 1 || slist.length == 0)
        {
            return s.trim();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slist.length; i++)
        {
            sb.append(slist[slist.length - 1 - i]).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * the sky is blue" ----> eht yks si eulb"
     * reverse the whole -----> "blue is the sky"
     */
    public static void reverseWords(char[] s)
    {
        if (s.length <=1)
        {
            return;
        }

        int reverseStartHere = 0;
        for (int i = 0; i < s.length; i++)
        {
            if (s[i] == ' ')
            {
                reverse(s, reverseStartHere, i-1);
                reverseStartHere = i+1;
            }
        }
        reverse(s, reverseStartHere, s.length-1);
        for (int i = 0; i < s.length/2; i++)
        {
            char tmp = s[i];
            s[i] = s[s.length -1 -i];
            s[s.length -1-i] = tmp;
        }
    }

    private static void reverse(char[] s, int i, int j)
    {
        char tmp = 0;
        while (i < j)
        {
            tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args)
    {
        System.out.println(reverseWords("the sky is blue"));

        char[] test = {'t', 'h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        reverseWords(test);
    }
}
