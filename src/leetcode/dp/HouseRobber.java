package leetcode.dp;

/**
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping you
 * from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight without
 * alerting the police.
 */
public class HouseRobber
{
    /**
     * Let us denote that:
     * 
     * f(k) = Largest amount that you can rob from the first k houses. 
     * Ai = Amount of money at the ith house. 

     * n = 1,
     * f(1) = A1.
     * 
     * n = 2,
     *  f(2) = max(A1, A2).
     * 
     * For n = 3,
     * 
     * Rob the third house, and add its amount to the first house's amount.
     * 
     * Do not rob the third house, and stick with the maximum amount of the
     * first two houses.
     * 
     * f(k) = max(f(k – 2) + Ak, f(k – 1)) 
     * 
     * We choose the base case as f(–1) =
     * f(0) = 0, which will greatly simplify our code as you can see.
     * 
     * The answer will be calculated as f(n). We could use an array to store and
     * calculate the result, but since at each step you only need the previous
     * two maximum values, two variables are suffice.
     * 
     * 
     * 
     * A1 -> x
     * A2 -> max(A1, x)
     * 
     * 3 -> A1 + x
     *      Item2
     * 
     * 4 -> A1 + A3
     *      A2 + x
     *      A1 + x
     * 
     * f(k) = max(f(k – 2) + Ak, f(k – 1)) 
     *      
     */
    public static int rob(int[] num)
    {
        int prevMax = 0;
        int currMax = 0;
        for (int x : num) {
            int temp = currMax;
            currMax = Math.max(prevMax + x, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public static void main(String[] args)
    {
        int[] test = {1, 2, 3, 1000};
        System.out.println(rob(test));
    }
}
