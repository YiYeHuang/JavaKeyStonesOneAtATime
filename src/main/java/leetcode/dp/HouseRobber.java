package leetcode.dp;

/**
 198. House Robber

 You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

 Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

 Example 1:

 Input: [1,2,3,1]
 Output: 4
 Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 Total amount you can rob = 1 + 3 = 4.
 Example 2:

 Input: [2,7,9,3,1]
 Output: 12
 Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 Total amount you can rob = 2 + 9 + 1 = 12.

 */
public class HouseRobber
{
    /**
     * [1,2,3,1]
     *
     * n = 0,      get 1
     * n = 1,      get 2, since 2 > 1
     *
     * n = 2       get 4, since 1 + 3 > 2           compare i - 1 and
     * n = 3       get 4, since 1 + 2 < 1 + 3       compare previous result and
     *
     *
     *
     *
     * f(k) = max(f(k – 2) + Ak, f(k – 1)) 
     *      
     */
    public static int rob(int[] num)
    {
        if (num.length == 0) return 0;

        int[] result = new int[num.length + 1];

        result[0] = 0;
        result[1] = num[0];
        for (int i = 1; i < num.length; i++) {
            int val = num[i];
            result[i+1] = Math.max(result[i], result[i-1] + val);
        }
        return result[num.length];
    }

    public static void main(String[] args)
    {
        int[] test = {1, 2, 3, 1};
        System.out.println(rob(test));
    }
}
