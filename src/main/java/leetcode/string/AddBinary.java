package leetcode.string;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

/*
67. Add Binary

Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */

@Easy
@StringTag
public class AddBinary {

    // like add two linkedlist
    public String addBinary(String a, String b) {
        int ptrA = a.length() - 1;
        int prtB = b.length() - 1;
        StringBuilder result = new StringBuilder();
        int carry = 0;

        while (ptrA >= 0 || prtB >= 0) {
            int sum = carry;
            if (prtB >= 0) {
                sum += b.charAt(prtB) - '0';
                prtB--;
            }
            if (ptrA >= 0) {
                sum += a.charAt(ptrA) - '0';
                ptrA--;
            }
            result.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) result.append(carry);
        return result.reverse().toString();
    }

}
