package leetcode.dp;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate
 * the number of 1's in their binary representation and return them as an array.
 * 
 * Example: For num = 5 you should return [0,1,1,2,1,2].
 * 
 * Follow up:
 * 
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it
 * in linear time O(n) /possibly in a single pass? Space complexity should be O(n). Can you do it
 * like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any
 * other lang
 */
public class CountingBits
{
    public static void main(String[] args)
    {
        
        countBits(6);
    }

    /**
     * 0             0
     * 1            01
     * 2            10
     * 3            11
     * 4           100
     * 5           101
     * 6           110
     * 7           111
     * 8          1000 
     * 9          1001
     * 10         1010
     * 
     * This can be typical dp solutions, in this case, cuz number is keep increase, it is not hard to find the relationship
     * 
     * 2, 4, 8 only got 1 1's, 3 has one more 1 than 1, 5 have one more 1 than 3
     * 
     * so 
     * f(i) = f(i/2) + i%2
     */
    private static int[] countBits(int num)
    {
        int[] result = new int[num + 1];
        result[0] = 0;
        for (int i= 1; i<= num; i++)
        {
            result[i] = result[i/2] + i%2;
        }
        return result;
    }
}
