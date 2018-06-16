package leetcode.string;

import Tag.company.Amazon;
import Tag.company.Bloomberg;
import Tag.company.Google;
import Tag.company.Microsoft;
import Tag.type.StringModify;

/**
 Given a string, find the first non-repeating character in it and return it's
 index. If it doesn't exist, return -1.
 Examples:

 s = "leetcode"
 return 0.

 s = "loveleetcode",
 return 2.
 */

@Amazon
@Google
@Microsoft
@Bloomberg
public class FisrtUniqueCharacterInString
{
    @StringModify
    public static int firstUniqChar(String s)
    {
        // use index as to access the frequency
        // initial with alphabetic array
        int[] freq = new int[26];

        for (int i = 0; i< s.length(); ++i)
        {
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i< s.length(); ++i)
        {
            if (freq[s.charAt(i) - 'a']==1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args)
    {

    }
}
