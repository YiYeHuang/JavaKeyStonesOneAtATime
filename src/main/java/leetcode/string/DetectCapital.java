package leetcode.string;

/*
520. Detect Capital

Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
 */

import leetcode.tag.level.Easy;
import leetcode.tag.type.StringTag;

@Easy
@StringTag
public class DetectCapital {

    // focus on the entire string
    public boolean detectCapitalUse(String word) {
        if (word.isEmpty()) return true;
        boolean firstLetterCap = Character.isUpperCase(word.charAt(0));
        String substr = word.substring(1);
        if (!firstLetterCap) {
            return allCharsLower(substr);
        } else {
            return allCharsUpper(substr) || allCharsLower(substr);
        }
    }

    private boolean allCharsLower(String s) {
        for(int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) return false;
        }
        return true;
    }

    private boolean allCharsUpper(String s) {
        for(int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) return false;
        }
        return true;
    }
}

