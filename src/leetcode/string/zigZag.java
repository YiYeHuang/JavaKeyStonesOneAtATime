package leetcode.string;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows:
 * string convert(string text, int nRows);
 * 
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 */
public class zigZag
{
    public static String convert(String s, int numRows)
    {
        if (numRows <= 1)
        {
            return s;
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            while (numRows != 0)
            {
                for (int i = 0; i < s.length(); i++)
                {
                    if ((i+1)%numRows == 1)
                    {
                        sb.append(s.charAt(i));
                    }
                }
                numRows --;
            }
            return sb.toString();
        }
       
    }

    public static void main(String[] args)
    {
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}
