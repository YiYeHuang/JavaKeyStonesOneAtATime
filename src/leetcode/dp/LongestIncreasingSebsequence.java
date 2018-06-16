package leetcode.dp;

public class LongestIncreasingSebsequence
{
    public static int lengthOfLIS(int[] nums)
    {
        int[] dp = new int[nums.length];

        int globalMaxResult = 0;

        for (int i =0; i< nums.length; i++)
        {
            dp[i] = 1;
            int localMax = nums[i];
            int localresult = 1;
            for (int j = i+1; j < nums.length; j++)
            {
                if (nums[j] == nums[i])
                    dp[j] = 1;
                else if (nums[j] > nums[i])
                {
                    
                    if (nums[i] == localMax)
                    {
                        localMax = nums[j];
                        dp[j] = ++localresult;
                        continue;
                    }

                    if (nums[j] <= localMax)
                    {
                        for (int k = j-1; k >=i ; k--)
                        {
                            if (nums[j] > nums[k] && nums[k]!=0)
                            {
                                dp[j] = dp[k]+1;
                                if (dp[k]+1 > localresult)
                                {
                                    localresult = dp[k]+1;
                                }
                                break;
                            }
                        }
                    }
                    else
                    {
                        localMax = nums[j];
                        dp[j] = ++localresult;
                    }
                    
  
                }
            }
            if (localresult > globalMaxResult)
            {
                globalMaxResult = localresult;
            }

        }
        
        return globalMaxResult;
    }

    public static void main(String[] args)
    {
        int[] test = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(test));
    }
}
