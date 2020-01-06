package leetcode.string;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Mathematics;
import leetcode.tag.type.StringTag;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 6. ZigZag Conversion

 The string "PAYPALISHIRING" is written in a zigzag pattern
 on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

 P   A   H   N
 A P L S I I G
 Y   I   R
 And then read line by line: "PAHNAPLSIIGYIR"

 Write the code that will take a string and make this conversion given a number of rows:

 string convert(string s, int numRows);
 Example 1:

 Input: s = "PAYPALISHIRING", numRows = 3
 Output: "PAHNAPLSIIGYIR"
 Example 2:

 Input: s = "PAYPALISHIRING", numRows = 4
 Output: "PINALSIGYAHRPI"
 Explanation:

 P     I    N
 A   L S  I G
 Y A   H R
 P     I
 Accepted
 */

@Medium
@StringTag
@Mathematics
public class zigZag {

    /**
     * /*n=numRows
     * Δ=2n-2    1                           2n-1                         4n-3
     * Δ=        2                     2n-2  2n                    4n-4   4n-2
     * Δ=        3               2n-3        2n+1              4n-5       .
     * Δ=        .           .               .               .            .
     * Δ=        .       n+2                 .           3n               .
     * Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
     * Δ=2n-2    n                           3n-2                         5n-4
     * */

    public static String convert(String s, int numRows)
    {
        if(numRows == 1) return s;
        // for each element on the delta, the difference is 2n - 2
        int delta = 2 * numRows - 2;
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for(int row = 0; row < numRows; row++) {
            int pos = row;

            while (pos < n) {
                // be aware of 0 delta
                if (delta != 0) {
                    sb.append(s.charAt(pos));
                    System.out.println("row " + row + " pos " + pos + " delta " + delta + " current result: " + sb.toString());
                }
                pos += delta;

                if (row != 0 && pos < n) {
                    sb.append(s.charAt(pos));
                    System.out.println("row " + row + " pos " + pos + " delta " + delta + " current result: " + sb.toString());
                }

                pos += 2 * row;
            }

            delta -= 2;
        }

        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(convert("PAYPALISHIRING", 4));

    }
}
