package leetcode.dp.maxSubArray;

import leetcode.tag.level.Easy;
import leetcode.tag.type.DP;

/**
 53. Maximum Subarray

 Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

 Example:

 Input: [-2,1,-3,4,-1,2,1,-5,4],
 Output: 6
 Explanation: [4,-1,2,1] has the largest sum = 6.
 Follow up:
 */

@Easy
@DP
public class MaxSubArray
{

    /**
     *
     * subquestion Max(A[], i, j) where A[i, j] sum is max
     * return sum no 2d array
     *
     * Input: [-2,1,-3,4,-1,2,1,-5,4],
     *
     * [0,0] max -2
     * [0,1] max 1    so we want to ditch the -2, and ditch the index 0, cuz it will always make the sum smaller
     * [1,2] max -2
     * [1,3] max 4    ditch -2 and ditch index 2
     *
     * so..............
     *
     *  Max(A[]) = Result[i - 1] > 0      Max( A [i])  or Max(A[i]) + Result[i - 1]
     *
     */
    public static int maxSubArray_DP(int[] nums) {
        // DP sub problem
        // either accumulate with previous result or use the current value alone
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxResult = nums[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);

            if (dp[i] > maxResult) maxResult = dp[i];
        }
        return maxResult;
    }


    public static int maxSubArray_DQ(int[] nums) {
        return Subarray_DQ(nums, 0 ,nums.length -1 );
    }

    // DP: num[i] or dp[i-1] + num[i]
    // DQ: Max[left] or Max[right] or Max[left] + Max[Right]
    //     e.g  -2, 3, 4, -1
    public static int Subarray_DQ(int[] A,int low, int high){
        if(low == high){
            return A[low];
        }

        int mid = low + (high - low) / 2;
        int leftSum = Subarray_DQ(A,low,mid);// left part
        int rightSum = Subarray_DQ(A,mid+1,high);//right part
        int crossSum = mergeCrossSubarray(A,low,high);// cross part

        String printResult = "Split on range " + low + " " + high;
        System.out.println(printResult);
        if(leftSum >= rightSum && leftSum >= crossSum){// left part is max
            System.out.println(printResult + " Result is left " + leftSum);
            return leftSum;
        } else if(rightSum >= leftSum && rightSum >= crossSum){// right part is max
            System.out.println(printResult + " Result is right " + rightSum);
            return rightSum;
        } else {
            System.out.println(printResult + " Result is cross " + crossSum);
            return crossSum;
        }
    }

    public static int mergeCrossSubarray(int[] A,int left,int right){
        int leftSum = Integer.MIN_VALUE;
        int rightSum = Integer.MIN_VALUE;
        int sum = 0;
        int mid = left + (right - left) / 2;
        for(int i = mid; i >= left ; i--){
            sum = sum + A[i];
            if(leftSum < sum){
                leftSum = sum;
            }
        }
        sum = 0;
        for(int j = mid + 1; j <= right; j++){
            sum = sum + A[j];
            if(rightSum < sum){
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    public static void main(String[] args)
    {
        //int[] nums = {-2,-3,4,-1,-2,1,5,-3,1,3};
        int[] nums = {-2,3,4,-1};
        System.out.println(maxSubArray_DQ(nums));
    }
}
