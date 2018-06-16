package leetcode.string;

/**
 * Write a function that takes a string as input and returns the string
 * reversed.
 * 
 * Example: Given s = "hello", return "olleh".
 */
public class reverseString
{
    public String reverseString(String s)
    {
        if (null == s || s.length() == 1)
        {
            return s;
        }

        char[] list = s.toCharArray();
        for (int i = 0; i < list.length/2; i++)
        {
            char temp = list[i];
            list[i] = list[list.length -1 -i];
            list[list.length -1 -i] = temp;
        }

        return new String(list);
    }
}
