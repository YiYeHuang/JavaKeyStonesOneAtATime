package leetcode.array;

import Tag.company.Amazon;
import Tag.company.Apple;
import Tag.company.Facebook;
import Tag.company.LinkedIn;
import Tag.company.Microsoft;
import Tag.type.Array;

/**
 Given an array nums of n integers where n > 1,
 return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

 Example:

 Input:  [1,2,3,4]
 Output: [24,12,8,6]
 Note: Please solve it without division and in O(n).

 Follow up:
 Could you solve it with constant space complexity?
 (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

@Facebook
@Microsoft
@Amazon
@LinkedIn
@Apple
public class ProductArrayExceptSelf
{
    /**
     *
     * with out itself == use value before and use value after
     * which == forward go through and backword go through
     *
     * T: O(2n)
     * S: O(n)
     */
    @Array
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
