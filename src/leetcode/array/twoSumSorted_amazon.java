package leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2. Please note that
 * your returned answers (both index1 and index2) are not zero-based.
 * 
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 * 
 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
 *
 */
public class twoSumSorted_amazon
{
    /**
     * use of map, contain the previous result
     */
    public static int[] twoSum(int[] numbers, int target)
    {
        int[] result = new int[2];
        Map<Integer, Integer> map =new HashMap<Integer,Integer>();
        for (int i = 0; i < numbers.length; i++)
        {
            if (!map.containsKey(target - numbers[i]))
            {
                map.put(numbers[i],i);
            }
            else
            {
                result[0] = map.get(target - numbers[i]) + 1;
                result[1] = i + 1;
                break;
            }
        }
        return result;
    }
    
    public static void main(String[] args)
    {
        int[] test =
        { 2,3,4 };
        System.out.println(twoSum(test, 6));
    }
}
