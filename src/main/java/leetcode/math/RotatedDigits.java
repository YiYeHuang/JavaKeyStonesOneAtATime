package leetcode.math;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Mathematics;
import leetcode.tag.type.StringTag;

/*
788. Rotated Digits

X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
Explanation:
There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
Note:
 */

@Easy
@StringTag
@Mathematics
public class RotatedDigits {

    public int rotatedDigits(int N) {
        int count = 0;
        for (int i = 1; i <= N; i ++) {
            if (isValid(i)) count ++;
            i += incrementIfNeeded(i);
        }
        return count;
    }

    // if 300, then we know 300 ... 399 are all invalid.
    private int incrementIfNeeded(int i) {
        int inc = 1;
        while (i >= 10) {
            inc *= 10;
            i /= 10;
        }
        if (i == 3 || i == 4 || i == 7) return inc - 1;
        else return 0;
    }

    private boolean isValid(int N) {
        /*
         Valid if N contains ATLEAST ONE 2, 5, 6, 9
         AND NO 3, 4 or 7s
         */
        boolean validFound = false;
        while (N > 0) {
            if (N % 10 == 2) validFound = true;
            if (N % 10 == 5) validFound = true;
            if (N % 10 == 6) validFound = true;
            if (N % 10 == 9) validFound = true;
            if (N % 10 == 3) return false;
            if (N % 10 == 4) return false;
            if (N % 10 == 7) return false;
            N = N / 10;
        }
        return validFound;
    }

    /********************************
     * DP
     * Using a int[] for dp.
     * dp[i] = 0, invalid number
     * dp[i] = 1, valid and same number
     * dp[i] = 2, valid and different number
     */
    public int rotatedDigits_DP(int N) {
        int[] dp = new int[N + 1];
        int count = 0;
        for(int i = 0; i <= N; i++){
            if(i < 10){
                if(i == 0 || i == 1 || i == 8) {
                    dp[i] = 1;
                }
                else if(i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;
                    count++;
                }
            } else {
                int a = dp[i / 10];
                int b = dp[i % 10];
                if(a == 1 && b == 1) {
                    dp[i] = 1;
                }
                else if(a >= 1 && b >= 1){
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return count;
    }
}
