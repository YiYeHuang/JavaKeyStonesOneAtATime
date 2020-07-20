package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import leetcode.tag.type.Mathematics;

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

    public static boolean checkIfExist(int[] arr) {
        if (arr == null || arr.length == 0) return false;

        Set<Integer> lookup = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (lookup.contains(arr[i] * 2) || (arr[i] %2 ==0 && lookup.contains(arr[i] / 2))) {
                return true;
            }
            lookup.add(arr[i]);
        }

        return false;
    }

    public static int minSteps(String s, String t) {
        int[] range = new int[26];

        for (int i =0; i < s.length(); i++) {
            range[s.charAt(i) - 'a']++;
        }

        for (int i =0; i < t.length(); i++) {
            range[t.charAt(i) - 'a']--;
        }

        int result = 0;
        for (int i =0; i < range.length; i++) {
            if (range[i] < 0) {
                result += Math.abs(range[i]);
            }
        }

        return result;
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



    public static int daysBetweenDates(String date1, String date2) {
        return Math.abs(dayOfYear(date1) - dayOfYear(date2));
    }

    public static int dayOfYear(String S) {
        String[] s = S.split("-");
        int year = Integer.parseInt(s[0]);
        int month = Integer.parseInt(s[1]);
        int date = Integer.parseInt(s[2]);
        boolean isLeap = checkYear(year);
        int noOfDays = 0;
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        for (int i = 0; i < month - 1; i++) {
            if (isLeap && i == 1) {
                noOfDays += days[i] + 1;
                continue;
            }
            noOfDays += days[i];
        }
        return noOfDays + date + getYear(year);
    }

    static boolean  checkYear(int year) {
        if (year % 400 == 0)
            return true;
        if (year % 100 == 0)
            return false;
        if (year % 4 == 0)
            return true;
        return false;
    }

    public static int getYear(int year) {
        int diff = year - 1971;
        int sum = 0;
        for (int i = 1971; i < 1971 + diff; i++) {
            if (checkYear(i)) sum += 366;
            else sum += 365;
        }

        return sum;
    }

    static class Node{
        int index;
        boolean visited;
        int left;
        int right;
        Node leftNode;
        Node rightNode;

        public Node(int index, int left, int right) {
            this.left = left;
            this.right = right;
            this.index = index;
            visited = false;
        }

    }

    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        if (n==1) return true;

        Node[] nodes = new Node[n];
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i< n; i++) {
            int left = leftChild[i];
            int right = rightChild[i];

            nodes[i] = new Node(i, left, right);
            nodeMap.put(i, nodes[i]);
        }

        nodes[0].visited = true;
        for (int i = 0; i< n; i++) {
            if (nodeMap.containsKey(nodes[i].left)) {
                if (nodeMap.get(nodes[i].left).visited) {
                    return false;
                } else {
                    nodes[i].leftNode = nodeMap.get(nodes[i].left);
                    nodeMap.get(nodes[i].left).visited = true;
                }
            }

            if (nodeMap.containsKey(nodes[i].right)) {
                if (nodeMap.get(nodes[i].right).visited) {
                    return false;
                } else {
                    nodes[i].rightNode = nodeMap.get(nodes[i].right);
                    nodeMap.get(nodes[i].right).visited = true;
                }
            }
        }

        int size = 0;
        Stack<Node> stack = new Stack<>();
        Node current = nodes[0];

        while (!stack.isEmpty() || current != null) {

            if (current != null) {
                stack.push(current);
                current = current.leftNode;
            } else {
                Node node = stack.pop();
                size ++;
                current = node.rightNode;
            }

        }

        if (size < n) {
            return false;
        }
        return true;
    }

    public static String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n%2 == 0) {
            int count = n - 1;
            for (int i = 1; i <= n - 1; i++) {
                sb.append('a');
            }
            sb.append('b');
        } else {
            for (int i = 1; i <= n; i++) {
                sb.append('a');
            }
        }

        return sb.toString();
    }




    public static int numTimesAllBlue(int[] light) {
        int result = 0;
        int largestUp = 0;
        int lastBlue = 0;

        boolean[] up = new boolean[light.length];
        for (int i = 0; i < light.length; i++) {
            up[light[i] - 1] = true;

            if ((light[i] - 1) > largestUp) largestUp = light[i] - 1;
            boolean isAllUp = true;
            for (int j = largestUp; j >= lastBlue; j--) {
                isAllUp &= up[j];
            }
            if (isAllUp) {
                result ++;
                lastBlue = light[i] - 1;
            }
        }

        return result;
    }

    public static int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            list.add(index[i], nums[i]);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int sumFourDivisors(int[] nums) {
        int[] dp = new int[100002];

        for (int i = 2; i < 21474; i++) {
            if (dp[i] == 0) {
                for (int j = i + 1; j < 21474; j++) {
                    if (dp[j]== 0 && i * j <= 100000) {
                        if (dp[i*j + 1] == 0) {
                            dp[i*j + 1] = 1 + i + j + (i*j);
                        } else {
                            dp[i*j + 1] = 0;
                        }
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i]+1;
            if (dp[key] != 0) {
                sum += dp[key];
            }
        }
        return sum;
    }



    public static void main(String[] args) {
        System.out.println("box apple test".compareTo("box apples test"));
    }


}


