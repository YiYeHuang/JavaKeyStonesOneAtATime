package algorithm.divideCon;

import java.math.BigInteger;

public class Karatsuba {

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
        if (N <= 10) {
            return x.multiply(y);                // optimize this parameter
        }

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
        System.out.println(karatsuba(new BigInteger("3141592653589793238462643383279502884197169399375105820974944592"),
                new BigInteger("2718281828459045235360287471352662497757247093699959574966967627")));
    }
}
