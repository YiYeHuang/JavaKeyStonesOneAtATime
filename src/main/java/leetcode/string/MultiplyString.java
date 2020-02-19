package leetcode.string;

import java.math.BigInteger;

/**
 * 43. Multiply Strings
 *
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 */
public class MultiplyString
{
    public static String multiply(String num1, String num2)
    {
        int value1 = 0;
        int value2 = 0;

        int tenPower1 = num1.length() -1;
        for (char value : num1.toCharArray())
        {
            value1 += (value - '0') * ((int) Math.pow(10, tenPower1));
            tenPower1--;
        }
        int tenPower2 = num2.length() -1;
        for (char value : num2.toCharArray())
        {
            value2 += (value - '0') * ((int) Math.pow(10, tenPower2));
            tenPower2--;
        }

        Integer result = 0;

        while (value2 != 0)
        {
            result += value1;
            value2--;
        }

        return result.toString();
    }

    /**
     * the key is to simulate the real multiply process
     */
    public static String multiplyAnswer(String num1, String num2)
    {
        int n1 = num1.length(), n2 = num2.length();
        int[] products = new int[n1 + n2];
        for (int i = n1 - 1; i >= 0; i--)
        {
            // go through each value and calculate exact value
            for (int j = n2 - 1; j >= 0; j--)
            {
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                products[i + j + 1] += d1 * d2;
            }
        }
        int carry = 0;
        // handle the carry
        for (int i = products.length - 1; i >= 0; i--)
        {
            int tmp = (products[i] + carry) % 10;
            carry = (products[i] + carry) / 10;
            products[i] = tmp;
        }
        StringBuilder sb = new StringBuilder();
        for (int num : products)
        {
            sb.append(num);
        }

        while (sb.length() != 0 && sb.charAt(0) == '0')
        {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    /** Function to multiply two numbers
     * 1, a * c
     * 2, b * d
     * 3, (a + b) (c + d)
     * 4, 3 - 2 - 1
     *
     * */
    public static long multiply(long x, long y)
    {
        int size1 = getSize(x);
        int size2 = getSize(y);
        /** Maximum of lengths of number **/
        int N = Math.max(size1, size2);
        /** for small values directly multiply **/
        if (N < 10) {
            return x * y;
        }
        /** max length divided, rounded up **/
        N = (N / 2) + (N % 2);
        /** multiplier **/
        long m = (long)Math.pow(10, N);

        /** compute sub expressions **/
        long b = x / m;
        long a = x - (b * m);
        long d = y / m;
        long c = y - (d * m);
        /** compute sub expressions **/
        long z0 = multiply(a, c);
        long z1 = multiply(a + b, c + d);
        long z2 = multiply(b, d);

        return z0 + ((z1 - z0 - z2) * m) + (z2 * (long)(Math.pow(10, 2 * N)));
    }

    private final static BigInteger ZERO = new BigInteger("0");

    /*
     * Take two numbers, x and y.
     * Example: 12345 and 6789.
     * Find a base b and power m to separate it into.
     * We'll pick base = 10, and m to be half the length of the digits of the numbers in this implementation of the algorithm.
     * 	In this case, m will be 2, so 10^2 = 100. We will split the 2 numbers using this multiplier.
     * The form we want is:
     * x = x1*b^m + x0
     * y = y1*b^m + y0
     * ----------
     * Using the above example,
     * x1 = 123
     * x0 = 45
     * ----------
     * y1 = 67
     * y2 = 89
     * ----------
     * b = 10 and m = 2
     * ----------
     * Thus:
     * 12345 = 123 * 10^2  +  45
     * 6789 =   67 * 10^2  +  89
     *
     *
     * The recursive algorithm is as follows:
     *
     * If x<10 or y<10, return x*y. Single digit multiplication is the base case.
     * Otherwise:
     * Let z2 = karatsuba(x1, y1). x1 and y1 are the most significant digits, and are the local variables "high".
     * Let z0 = karatsuba(x0, y0). x0 and y0 are the least significant digits, and are the local variables "low".
     * Let z1 = karatsuba(x1+y0, x0+y1) - z0 - z2.
     * And the result is the following sum:
     * z2 * b^2m	+	z1 * b^m	+	z0
     *
     * @param x The multiplicand.
     * @param y The multiplier.
     * @return The product.
     */
    public static BigInteger karatsuba(BigInteger x, BigInteger y) {

        // cutoff to brute force
        int N = Math.max(x.bitLength(), y.bitLength());
        if (N <= 10) return x.multiply(y);                // optimize this parameter

        // number of bits divided by 2, rounded up
        N = (N / 2) + (N % 2);

        // x = a + 2^N b,   y = c + 2^N d
        BigInteger b = x.shiftRight(N);
        BigInteger a = x.subtract(b.shiftLeft(N));
        BigInteger d = y.shiftRight(N);
        BigInteger c = y.subtract(d.shiftLeft(N));

        // compute sub-expressions
        BigInteger ac    = karatsuba(a, c);
        BigInteger bd    = karatsuba(b, d);
        BigInteger abcd  = karatsuba(a.add(b), c.add(d));

        BigInteger result1 = abcd.subtract(ac).subtract(bd);
        BigInteger result1Shift = result1.shiftLeft(N);

        return ac.add(result1Shift).add(bd.shiftLeft(2*N));
    }

    /** Function to calculate length or number of digits in a number **/
    public static int getSize(long num)
    {
        int ctr = 0;
        while (num != 0)
        {
            ctr++;
            num /= 10;
        }
        return ctr;
    }

    public static void main(String[] args)
    {
        System.out.println(karatsuba(new BigInteger("3141592653589793238462643383279502884197169399375105820974944592"), new BigInteger("2718281828459045235360287471352662497757247093699959574966967627")));
    }
}
