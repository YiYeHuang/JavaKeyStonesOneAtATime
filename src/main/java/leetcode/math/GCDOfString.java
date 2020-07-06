package leetcode.math;

/*
1071. Greatest Common Divisor of Strings

For strings S and T, we say "T divides S" if and only if
S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.

Example 1:

Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
Example 2:

Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"
Example 3:

Input: str1 = "LEET", str2 = "CODE"
Output: ""


Note:

1 <= str1.length <= 1000
1 <= str2.length <= 1000
str1[i] and str2[i] are English uppercase letters.
 */
public class GCDOfString {

  // cutting off string from each other
  public String gcdOfStrings(String str1, String str2) {
    if (str1.length() < str2.length()) { // make sure str1 is not shorter than str2.
      return gcdOfStrings(str2, str1);
    }else if (!str1.startsWith(str2)) { // shorter string is not common prefix.
      return "";
    }else if (str2.isEmpty()) { // gcd string found.
      return str1;
    }else { // cut off the common prefix part of str1.
      return gcdOfStrings(str1.substring(str2.length()), str2);
    }
  }
}
