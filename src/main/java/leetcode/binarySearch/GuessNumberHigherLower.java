package leetcode.binarySearch;

import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.BinarySearch;

/**
 * We are playing the Guess Game. The game is as follows:
 *
 * I pick a number from 1 to n. You have to guess which number I picked.
 *
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 *
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 *
 * -1 : My number is lower
 *  1 : My number is higher
 *  0 : Congrats! You got it!
 * Example :
 *
 * Input: n = 10, pick = 6
 * Output: 6
 */
@Google

@Easy
@BinarySearch
public class GuessNumberHigherLower {

    public int guessNumber(int n) {
        int low = 1;
        int high = n;

        while (low < high) {

            // Always be careful about overflow
            int mid = low + (high - low) /2 ;

            if (guess(mid) == 1) {
                low = mid + 1;
            } else if (guess(mid) == -1) {
                high = mid - 1;
            } else if (guess(mid) == 0) {
                return mid;
            }
        }

        return low;
    }

    public int guess(int num) {
        return 1;
    }

}
