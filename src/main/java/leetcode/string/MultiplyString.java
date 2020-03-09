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

}
