package leetcode.string;

public class addString
{
    public static String addStrings(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        StringBuilder sb = new StringBuilder();
        if (n1 >= n2)
        {
            return sumHelp(num1, num2);
        }
        else
        {
            return sumHelp(num2, num1);
        }

        
    }

    public static String sumHelp(String longer, String shorter)
    {
        int n1 = longer.length(), n2 = shorter.length();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (n2 != 0)
        {
            int d1 = longer.charAt(n1-1) - '0';
            int d2 = shorter.charAt(n2-1) - '0';
            int sum = (d1 + d2 + carry)%10;
            carry = (d1 + d2 + carry)/10;
            n1--;
            n2--;
            sb.append(sum);
        }
        while (n1 != 0)
        {
            int d1 = longer.charAt(n1-1) - '0';
            int sum = (d1 + carry)%10;
            carry = (d1 + carry)/10;
            n1--;
            sb.append(sum);
        }
        if (carry != 0)
        {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    public String addStringsAnswer(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        char[] num1Array = num1.toCharArray();
        char[] num2Array = num2.toCharArray();
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            int a = i >= 0 ? (num1Array[i--] - '0') : 0;
            int b = j >= 0 ? (num2Array[j--] - '0') : 0;
            int sum = a + b + carry;
            sb.insert(0, sum % 10);
            carry = sum / 10;
        }
        return sb.toString();
    }

    public static void main(String[] args)
    {
        System.out.println(addStrings("1", "99"));
    }
}
