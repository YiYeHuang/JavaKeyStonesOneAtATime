package leetcode.dp;

import leetcode.tag.company.Amazon;
import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/**
 70. Climbing Stairs

 You are climbing a stair case. It takes n steps to reach to the top.

 Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

 Note: Given n will be a positive integer.

 Example 1:

 Input: 2
 Output: 2
 Explanation: There are two ways to climb to the top.
 1. 1 step + 1 step
 2. 2 steps
 Example 2:

 Input: 3
 Output: 3
 Explanation: There are three ways to climb to the top.
 1. 1 step + 1 step + 1 step
 2. 1 step + 2 steps
 3. 2 steps + 1 step

 */

@Amazon

@Easy
@DP
public class ClimbingStairs
{
    /**
     * 0 : 0 
     * 1 : 1
     * 2 : 1,       1 2
     * 3 : 1,1,1    2,1     1,2
     * 4 : 1,1,1,1  1,2,1   1,1,2    2,1,1   2,2
     * 
     * For
     * Step(n) = Take one step from Step[n - 1] or Take two step from Step[n - 2]
     *
     * Step(n) = Step[n - 1] + Step[n - 2]
     *
     * So actually it is Fibonacci Sequence
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
