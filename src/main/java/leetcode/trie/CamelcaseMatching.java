package leetcode.trie;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Trie;
import leetcode.trie.basicObj.TrieNodeFlex;

import java.util.ArrayList;
import java.util.List;

/*
1023. Camelcase Matching

A query word matches a given pattern if we can insert lowercase letters to the pattern word so that it equals the query.
(We may insert each character at any position, and may insert 0 characters.)

Given a list of queries, and a pattern, return an answer list of booleans, where answer[i] is true if and only if queries[i] matches the pattern.

Example 1:

Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
Output: [true,false,true,true,false]
Explanation:
"FooBar" can be generated like this "F" + "oo" + "B" + "ar".
"FootBall" can be generated like this "F" + "oot" + "B" + "all".
"FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".
Example 2:

Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
Output: [true,false,true,false,false]
Explanation:
"FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
"FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".
Example 3:

Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
Output: [false,true,false,false,false]
Explanation:
"FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".


Note:

1 <= queries.length <= 100
1 <= queries[i].length <= 100
1 <= pattern.length <= 100
All strings consists only of lower and upper case English letters.
 */

@Medium
@Trie
public class CamelcaseMatching {

    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        TrieNodeFlex root = new TrieNodeFlex();
        List<Boolean> res = new ArrayList<>();
        TrieNodeFlex node = root;

        // build Trie with pattern, since a is > A, creating child size 128
        for (int i = 0; i < pattern.length(); i++) {
            TrieNodeFlex temp = new TrieNodeFlex();
            node.children.put(pattern.charAt(i), temp);
            node = temp;
        }
        node.wordEndHere = true;

        for(String query : queries) {
            TrieNodeFlex walker = root;
            boolean flag = true;

            for (int i = 0; i < query.length(); i++) {
                int charCode = (int) query.charAt(i);
                // not matching
                if(walker.children.get(query.charAt(i)) == null && charCode >= 65 && charCode <= 90) {
                    flag = false;
                    break;
                }
                if (walker.children.get(query.charAt(i)) != null) {
                    walker = walker.children.get(query.charAt(i));
                }
                // lower case + not match will just skip
            }

            if(flag && walker.wordEndHere)
                res.add(true);
            else
                res.add(false);
        }

        return res;
    }

    public static void main(String[] args) {
        String[] query = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
        String patter = "FB";
        camelMatch(query, patter);
    }
}
