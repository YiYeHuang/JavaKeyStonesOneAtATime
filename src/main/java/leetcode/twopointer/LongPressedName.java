package leetcode.twopointer;

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;
import leetcode.tag.type.TwoPointer;

/*
925. Long Pressed Name

Your friend is typing his name into a keyboard.
Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.

You examine the typed characters of the keyboard.
Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.



Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pressed.
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
Example 3:

Input: name = "leelee", typed = "lleeelee"
Output: true
Example 4:

Input: name = "laiden", typed = "laiden"
Output: true
Explanation: It's not necessary to long press any character
 */

@Easy
@StringTag
@TwoPointer
public class LongPressedName {

    public static boolean isLongPressedName(String name, String typed) {
        int namePtr = 0;
        int typedPtr = 0;

        while (namePtr < name.length() && typedPtr < typed.length()) {
            if (name.charAt(namePtr) == typed.charAt(typedPtr)) {
                namePtr++;
                typedPtr++;
            }
            else if (typedPtr > 0 && typed.charAt(typedPtr) == typed.charAt(typedPtr-1)) {
                typedPtr++;
            }
            else {
                // nothing equal
                return false;
            }
        }

        // go through the remaining length, if anything typed not equal, not equal.
        while (typedPtr < typed.length()) {
            if (typed.charAt(typedPtr) != typed.charAt(typedPtr-1)) {
                return false;
            }

            typedPtr++;
        }

        return namePtr == name.length();
    }
}
