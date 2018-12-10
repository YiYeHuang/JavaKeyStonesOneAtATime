package leetcode.hashtable;

import leetcode.tag.level.Easy;
import leetcode.tag.type.HashTableTag;

import java.util.HashMap;

/**
 * 290. Word Pattern
 *
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 * Example 2:
 *
 * Input:pattern = "abba", str = "dog cat cat fish"
 * Output: false
 * Example 3:
 *
 * Input: pattern = "aaaa", str = "dog cat cat dog"
 * Output: false
 * Example 4:
 *
 * Input: pattern = "abba", str = "dog dog dog dog"
 * Output: false
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */

@Easy
@HashTableTag
public class WordPattern {
    /**
     * improved version of IsomorphicStrings, instead of comparing just char hash,
     * this one needs to mapping the char to the entire word, use hashmap to solve
     */
    public static boolean wordPattern(String pattern, String str) {
        String[] patterns= str.split(" ");
        HashMap<Character, String> map = new HashMap<Character, String>();

        if(patterns.length!= pattern.length())
            return false;

        for(int i=0; i<patterns.length; i++){
            char c = pattern.charAt(i);
            if(map.containsKey(c)){
                if(!map.get(c).equals(patterns[i]))
                    return false;
            }else{
                if(map.containsValue(patterns[i]))
                    return false;
                map.put(c, patterns[i]);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String test = "dog cat cat dog";
        wordPattern("abba", test);
    }
}
