package leetcode.string;

import java.util.Stack;

public class ValidParentheses_Amazon
{
    private static final char b = '(';
    private static final char sb = '[';
    private static final char bb = '{';

    public static boolean isValid(String s)
    {
        if (null == s)
        {
            return false;
        }
        if (s.length() == 0)
        {
            return false;
        }

        int b_counter = 0;
        int sb_counter = 0;
        int bb_counter = 0;

        for (char currentC : s.toCharArray())
        {
            switch (currentC)
            {
            case 40:
            {
                b_counter++;
                break;
            }
            case 41:
            {
                b_counter--;
                break;
            }
            case 91:
            {
                sb_counter++;
                break;
            }
            case 93:
            {
                sb_counter--;
                break;
            }
            case 123:
            {
                bb_counter++;
                break;
            }
            case 125:
            {
                bb_counter--;
            }
            }

            if (b_counter < 0 || sb_counter < 0 || bb_counter < 0)
            {
                return false;
            }
        }

        if (b_counter > 0 || sb_counter > 0 || bb_counter > 0)
        {
            return false;
        }

        return true;
    }

    public static boolean isValid2(String s)
    {
        if (null == s)
        {
            return false;
        }
        if (s.length() == 0)
        {
            return false;
        }

        int b_counter = 0;
        int sb_counter = 0;
        int bb_counter = 0;

        for (char currentC : s.toCharArray())
        {
            switch (currentC)
            {
            case 40:
            {
                b_counter++;
                break;
            }
            case 41:
            {
                b_counter--;
                break;
            }
            case 91:
            {
                sb_counter++;
                break;
            }
            case 93:
            {
                sb_counter--;
                break;
            }
            case 123:
            {
                bb_counter++;
                break;
            }
            case 125:
            {
                bb_counter--;
            }
            }

            if (b_counter < 0 || sb_counter < 0 || bb_counter < 0)
            {
                return false;
            }
        }

        if (b_counter > 0 || sb_counter > 0 || bb_counter > 0)
        {
            return false;
        }

        return true;
    }

    public static boolean isValid3(String s)
    {
        Stack<Integer> p = new Stack<>();
        for (int i = 0; i < s.length(); i++)
        {
            int q = "(){}[]".indexOf(s.substring(i, i + 1));
            if (q % 2 == 1)
            {
                if (p.isEmpty() || p.pop() != q - 1)
                    return false;
            } 
            else
            {
                p.push(q);
            }
        }
        return p.isEmpty();
    }

    public static void main(String[] args)
    {
        System.out.println(isValid("[]{()}["));
    }
}
