package leetcode.bitwise;

public class operation
{
    // Iterative
    public static int getSum(int a, int b)
    {
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        while (b != 0)
        {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }

    // Iterative
    public static int getSubtract(int a, int b)
    {
        while (b != 0)
        {
            int borrow = (~a) & b;
            a = a ^ b;
            b = borrow << 1;
        }

        return a;
    }

    // Recursive
    public static int getSumRe(int a, int b)
    {
        return (b == 0) ? a : getSumRe(a ^ b, (a & b) << 1);
    }

    // Recursive
    public static int getSubtractRe(int a, int b)
    {
        return (b == 0) ? a : getSubtractRe(a ^ b, (~a & b) << 1);
    }

    // Get negative number
    public static int negate(int x)
    {
        return ~x + 1;
    }

    public static void main(String[] args)
    {
        System.out.println(getSum(2, 3));
        int a = 2;
        int b = 3;
        // In Java, all integer types are signed, so the "<<" and ">>" operators
        // perform arithmetic shifts. Java adds the operator ">>>" to perform
        // logical right shifts, but since the logical and arithmetic left-shift
        // operations are identical for signed integer, 
        // there is no "<<<" operator in Java.
        System.out.println(a >> 1);
    }
}
