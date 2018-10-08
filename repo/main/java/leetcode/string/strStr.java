package leetcode.string;

/**
 * Implement strStr().
 * 
 * Returns the index of the first occurrence of needle in haystack, or -1 if
 * needle is not part of haystack.
 * 
 */
public class strStr
{
    public static int strStrFun(String haystack, String needle)
    {
        if (needle.length() == 0)
        {
            return 0;
        }
        if (needle.length() > haystack.length())
        {
            return -1;
        }
        if (needle.equals(haystack))
        {
            return 0;
        }
 
        int index = 0;
        int counter = 0;
        while ((index + needle.length()) <=  haystack.length())
        {
            for (int i = 0; i < needle.length(); i++)
            {
                if (needle.charAt(i) != haystack.charAt(i+index))
                {
                    index ++;
                    break;
                }
                else
                {
                    counter++;
                }
            }
            if (counter == needle.length())
            {
                return index;
            }
            counter = 0;
        }

        return -1;
    }

    public static void main(String[] args)
    {
        System.out.println(strStrFun("ababababc", "abc"));
    }
}
