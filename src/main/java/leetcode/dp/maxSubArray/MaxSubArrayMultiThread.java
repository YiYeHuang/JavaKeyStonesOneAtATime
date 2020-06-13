package leetcode.dp.maxSubArray;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MaxSubArrayMultiThread {


  public static void main(String[] args) {
    int[] nums = {-2,-3,4,-1,-2,1,5,-3,1,3};

    ForkJoinPool pool = new ForkJoinPool();
    ForkJoinTask<Integer> task = new MaxSubTask(nums, 0, nums.length - 1);
    Integer result = pool.invoke(task);
    pool.shutdown();

    System.out.println(result);
  }

  public static class MaxSubTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private final int[] num;
    private final int high;
    private final int low;
    private final int length;

    public MaxSubTask(int[] num, int low, int high) {
      this.num = num;
      this.high = high;
      this.low = low;
      this.length = high - low + 1;
    }

    @Override
    protected Integer compute() {
      if(low == high){
        return num[low];
      }

      int mid = low + (high - low) / 2;
      MaxSubTask leftSumTask = new MaxSubTask(num,low,mid);// left part
      MaxSubTask rightSumTask = new MaxSubTask(num,mid+1,high);//right part
      int crossSum = mergeCrossSubarray(num,low,high);// cross part

      // String printResult = "Split on range " + low + " " + high;
      // System.out.println(printResult);
      invokeAll(leftSumTask, rightSumTask);
      int leftSum = leftSumTask.join();
      int rightSum = rightSumTask.join();

      if(leftSum >= rightSum && leftSum >= crossSum){// left part is max
        // System.out.println(printResult + " Result is left " + leftSum);
        return leftSum;
      } else if(rightSum >= leftSum && rightSum >= crossSum){// right part is max
        // System.out.println(printResult + " Result is right " + rightSum);
        return rightSum;
      } else {
        // System.out.println(printResult + " Result is cross " + crossSum);
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
  }
}
