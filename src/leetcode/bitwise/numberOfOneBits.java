package leetcode.bitwise;

/**
 * Write a function that takes an unsigned integer and returns the number of ’1'
 * bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation
 * 00000000000000000000000000001011, so the function should return 3.
 */
public class numberOfOneBits
{
    // you need to treat n as an unsigned value
    // 000001010101010101010100001
    //                           &
    // 000000000000000000000000001
    //______________________________ = 1
    // then shift to left 1 bit
    public static int hammingWeight(int n)
    {
        int result = 0;
        while (n != 0)
        {
            if ((n & 1) == 1)
            {
                result++;
            }
            n = n >>>1;
        }
        return result;
    }

    public static void main(String[] args)
    {
        System.out.println(hammingWeight(11));
    }
}
