package leetcode.dp.forwardANDbackward;


import leetcode.tag.level.Medium;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.DP;
import leetcode.tag.type.ForwardBackward;

/**
 238.  Given an array nums of n integers where n > 1,
 return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:

 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity?
 (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

@ForwardBackward
@Medium
@DP
public class ProductArrayExceptSelf
{
    /**
     *
     * with out itself == use value before and use value after
     * which == forward go through and backword go through
     *
     * T: O(2n)
     * S: O(n)
     *
     * 1:   \2  3  4
     * 2: 1  \  3  4
     * 3: 1 2 \    4
     * 4: 1 2 3\
     *
     */
    @ArrayTag
    public static int[] productExceptSelf(int[] nums)
    {
        int leng = nums.length;
        int[] ret = new int[leng];
        if(leng == 0)
            return ret;

        int runningprefix = 1;

        for(int i = 0; i < leng; i++){
            ret[i] = runningprefix;
            runningprefix*= nums[i];
        }
        int runningsufix = 1;
        for(int i = leng -1; i >= 0; i--){
            ret[i] *= runningsufix;
            runningsufix *= nums[i];
        }
        return ret;
    }
 
    public static void main(String[] args)
    {
        int[] test = {1,2,3,4};
        System.out.println(productExceptSelf(test));
    }
}
