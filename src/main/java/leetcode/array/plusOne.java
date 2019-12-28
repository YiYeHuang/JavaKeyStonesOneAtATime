package leetcode.array;

import leetcode.tag.level.Easy;
import leetcode.tag.type.ArrayTag;
import leetcode.tag.type.Mathematics;

/**
 Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

 The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.

 You may assume the integer does not contain any leading zero, except the number 0 itself.

 Example 1:

 Input: [1,2,3]
 Output: [1,2,4]
 Explanation: The array represents the integer 123.
 Example 2:

 Input: [4,3,2,1]
 Output: [4,3,2,2]
 Explanation: The array represents the integer 4321.
 */

@Easy
public class plusOne
{
    /**
     * only trick is to care about the carriers
     * return new array if overflow
     */
    @ArrayTag
    @Mathematics
    public int[] plusOne(int[] digits)
    {
        for (int i =digits.length -1; i> 0; i--)
        {
            if (digits[i] < 9)
            {
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }

        int[] newNumber = new int [digits.length+1];
        newNumber[0] = 1;

        return newNumber;
    }
}
