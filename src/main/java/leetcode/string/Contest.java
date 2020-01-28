package leetcode.string;

public class Contest {

    public static int removePalindromeSub(String s) {

        int left = 0;
        int right = s.length() - 1;
        int count = 0;

        while (left < right) {
            if (isPalindrome(s.substring(left, right + 1), left, right)) {
                left = right + 1;
                right = s.length() - 1;
                count++;
            } else {
                right--;
            }
        }
        return count;
    }


    private static boolean isPalindrome(String input, int left, int right)
    {
        while (left < right)
        {
            if (input.charAt(left) != input.charAt(right)) return false;
            ++left;
            --right;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(removePalindromeSub("ababa"));
    }
}
