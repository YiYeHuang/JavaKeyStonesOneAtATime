package leetcode.math;

/**
 * Determine whether an integer is a palindrome. Do this without extra space.
 */
public class palindromeNumber
{
    /**
     * Key here is in place keep recalculating a another half of the input. and compare the math
     */
    public boolean isPalindrome(int x)
    {
        if (x < 10 && x >= 0)
        {
            return true;
        }
        if (x < 0 || (x != 0 && x % 10 == 0))
        {
            return false;
        }

        int half = 0;
        while (x > half)
        {
            half = half * 10 + x % 10;
            x = x / 10;
        }

        return (x == half) || (x == half / 10);
    }

    public static void main(String[] args)
    {

    }
}
