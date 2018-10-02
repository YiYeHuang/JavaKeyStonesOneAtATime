package leetcode.string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * For example, given s = "leetcode", dict = ["leet", "code"].
 * 
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * UPDATE (2017/1/4): The wordDict parameter had been changed to a list of strings (instead of a set
 * of strings). Please reload the code definition to get the latest changes.
 */
public class WordBreak
{
    public static boolean wordBreak(String s, List<String> wordDict)
    {

        if (null == s || null == wordDict || wordDict.size() == 0)
            return false;

        Set<String> dict = new HashSet<String>();
        for (String word : wordDict)
        {
            dict.add(word);
        }

        boolean[] f = new boolean[s.length() + 1];

        f[0] = true;

        /*
         * First DP for(int i = 1; i <= s.length(); i++){ for(String str: dict){ if(str.length() <=
         * i){ if(f[i - str.length()]){ if(s.substring(i-str.length(), i).equals(str)){ f[i] = true;
         * break; } } } } }
         */

        /**
         * Typical DP question, need an array to store the value
         * The idea is to store flag to check word_contans end here, 
         */
        for (int i = 1; i <= s.length(); i++)
        {
            for (int j = 0; j < i; j++)
            {
                String target = s.substring(j, i);
                System.out.println(target);
                if (f[j] && dict.contains(target))
                {
                    f[i] = true;
                    break;
                }
            }
        }

        return f[s.length()];
    }

    public static void main(String[] args)
    {
        List test = new ArrayList<String>();
        test.add("leet");
        test.add("code");
        test.add("test");

        wordBreak("testleetcode", test);
    }
}
