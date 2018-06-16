package leetcode.dp;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * Note: Given n will be a positive integer.
 */
public class ClimbingStairs
{
    /**
     * 0 : 0 
     * 1 : 1
     * 2 : 1,1   2
     * 3 : 1,1,1  2,1  1,2
     * 4 : 1,1,1,1    1,2,1   1,1,2    2,1,1   2,2
     * 
     * It is Fibonacci
     */
    public static int climbStairs(int n)
    {
        if (n == 0)
        {
            return 0;
        }
        else if (n == 1)
        {
            return 1;
        }
        else if (n == 2)
        {
            return 2;
        }
        else
        {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    
    public static int climbStairsNon_Recurr(int n)
    {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    public static void main(String[] args)
    {
        System.out.println(climbStairs(44));
    }
}
