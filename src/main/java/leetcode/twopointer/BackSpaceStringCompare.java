package leetcode.twopointer;

import leetcode.tag.company.Facebook;
import leetcode.tag.company.Google;
import leetcode.tag.level.Easy;
import leetcode.tag.type.StackTag;
import leetcode.tag.type.TwoPointer;

import java.util.Stack;

/**
 * 844. Backspace String Compare
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 * Example 2:
 *
 * Input: S = "ab##", T = "c#d#"
 * Output: true
 * Explanation: Both S and T become "".
 * Example 3:
 *
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 * Example 4:
 *
 * Input: S = "a#c", T = "b"
 * Output: false
 * Explanation: S becomes "c" while T becomes "b".
 * Note:
 *
 * 1 <= S.length <= 200
 * 1 <= T.length <= 200
 * S and T only contain lowercase letters and '#' characters.
 * Follow up:
 *
 * Can you solve it in O(N) time and O(1) space?
 */

@Google
@Facebook

@Easy
@TwoPointer
@StackTag
public class BackSpaceStringCompare {

    /**
     * very typical two pointer way of thinking,
     * backtrack the string, recording the number of # and doing the skip
     *
     * Compare the index
     */
    public static boolean backspaceCompare(String S, String T) {
        int Tptr = T.length() - 1;
        int Sptr = S.length() - 1;
        int Tdec = 0;
        int Sdec = 0;

        while ( Tptr >= 0 || Sptr >= 0) {

            while (Sptr >= 0 && (Sdec > 0 || S.charAt(Sptr) == '#')) {
                if (S.charAt(Sptr) == '#') Sdec++;
                else Sdec--;
                Sptr--;
            }
            char right = Sptr < 0 ? '@' : S.charAt(Sptr);


            while(Tptr >= 0 && (Tdec > 0 || T.charAt(Tptr) == '#')) {
                if (T.charAt(Tptr) == '#') Tdec++;
                else Tdec--;
                Tptr--;
            }
            char left = Tptr < 0 ? '@' : T.charAt(Tptr);


            if (left != right) return false;
            Tptr--;
            Sptr--;
        }

        return true;
    }

    /**
     * forward thinking, when meeting the #, pop the top one
     */
    public static boolean backspaceCompareStack(String S, String T) {
        String newS = resolve(S);
        String newT = resolve(T);

        return newS.equals(newT);
    }
    public static String resolve(String str) {
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        int i = 0;
        while(i < str.length())
        {
            if(str.charAt(i) == '#')
            {
                if(!stack.isEmpty())stack.pop();
            }
            else
            {
                stack.push(str.charAt(i));
            }
            i++;
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        backspaceCompare("bbbextm", "bbb#extm");
    }
}
