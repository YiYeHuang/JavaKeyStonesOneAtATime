package leetcode.math;


import leetcode.tag.level.Easy;
import leetcode.tag.type.Mathematics;

/*
441. Arranging Coins

You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.
 */
@Mathematics
@Easy
public class ArrangingCoins {

    public static int arrangeCoins(int n) {

        if (n < 1) return 0;

        int result = 1;
        while (n > 0) {
            n -= result;

            if (n < 0) {
                break;
            }

            result ++;
        }


        return result - 1;
    }

    public int arrangeCoins_BS(int n) {

//        Our target is to search for a factor k where k * (k+1) / 2 <= n.
//        This is referencing Gauss' formula on summation of consecutive numbers.
//        Using binary search for such purpose gives you O(log n) time.
        //convert int to long to prevent integer overflow
        long nLong = (long)n;

        long st = 0;
        long ed = nLong;

        long mid = 0;

        while (st <= ed){
            mid = st + (ed - st) / 2;

            if (mid * (mid + 1) <= 2 * nLong){
                st = mid + 1;
            }else{
                ed = mid - 1;
            }
        }

        return (int)(st - 1);
    }

    public static void main(String[] args) {
        System.out.println(arrangeCoins(2));
    }
}
