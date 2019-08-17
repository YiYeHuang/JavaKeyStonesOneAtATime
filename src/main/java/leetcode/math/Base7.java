package leetcode.math;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Mathematics;

/**
 * 504. Base 7
 *
 * Given an integer, return its base 7 string representation.
 *
 * Example 1:
 * Input: 100
 * Output: "202"
 * Example 2:
 * Input: -7
 * Output: "-10"
 */

@Mathematics
@Easy
public class Base7 {

    public String convertToBase7(int num) {

        if (num == 0) return "0";

        StringBuilder sb = new StringBuilder();
        boolean negative = num < 0;

        while (num != 0) {
            sb.append(Math.abs(num%7));
            num /= 7;
        }

        if (negative) {
            sb.append("-");
        }

        return sb.reverse().toString();
    }
}
