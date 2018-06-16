package leetcode.array;

public class MaxStock_Amazon
{
    public static int maxProfit(int[] prices)
    {
        if (prices.length == 0)
        {
            return 0;
        }
        
        int min = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++)
        {
            if (prices[i] < min)
            {
                
                min = prices[i];
            }
            else
            {
                if (prices[i] - min > profit)
                {
                    profit = prices[i] - min;
                }
                
            }
        }
        return profit;
    }

    public static void main(String[] args)
    {
        int[] in = {7, 6, 4, 3, 1};
        System.out.println(maxProfit(in));
    }
}
