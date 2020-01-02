package leetcode.hashtable;


import leetcode.tag.level.Easy;
import leetcode.tag.type.*;

import java.lang.String;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 169. Majority Element

 Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

 You may assume that the array is non-empty and the majority element always exist in the array.

 Example 1:

 Input: [3,2,3]
 Output: 3
 Example 2:

 Input: [2,2,1,1,1,2,2]
 Output: 2
 */

@Easy
@Hash
@Sorting
@DivideConquer
public class MajorityItem
{

    /**
     * Time Complexity O(n)
     * Space Complexity O(n)
     */
    public static int majorityElementHashMap(int[] nums)
    {
        if (nums.length == 1)
        {
            return nums[0];
        }

        Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
        for (int num : nums)
        {
            if (!resultMap.containsKey(num))
            {
                resultMap.put(num, 1);
            }
            else
            {
                resultMap.put(num, resultMap.get(num) + 1);
                if (resultMap.get(num) > nums.length / 2)
                {
                    return num;
                }
            }
        }
        return -1;
    }


    /**
     * If the elements are sorted in monotonically increasing (or decreasing) order, the majority element can
     * be found at index n/2 or n/2 + 1
     *
     * Time Complexity: O(nlog(n))
     * Space Complexity: O(1)
     */
    public static int majorityElementSort(int[] nums)
    {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }


    /**
     * Intuition
     *
     * If we had some way of counting instances of the majority element as +1+1 and instances of any other element as -1−1,
     * summing them would make it obvious that the majority element is indeed the majority element.
     *
     * Algorithm
     * Essentially, what Boyer-Moore does is look for a suffix sufsuf of nums where suf[0]suf[0]
     * is the majority element in that suffix. To do this, we maintain a count, which is incremented whenever we see
     * an instance of our current candidate for majority element and decremented whenever we see anything else.
     * Whenever count equals 0, we effectively forget about everything in nums up to the current index and consider
     * the current number as the candidate for majority element. It is not immediately obvious why
     * we can get away with forgetting prefixes of nums - consider the following examples
     * (pipes are inserted to separate runs of nonzero count).
     *
     * [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
     *
     * Here, the 7 at index 0 is selected to be the first candidate for majority element.
     * count will eventually reach 0 after index 5 is processed, so the 5 at index 6 will be the next candidate.
     * In this case, 7 is the true majority element, so by disregarding this prefix,
     * we are ignoring an equal number of majority and minority elements - therefore,
     * 7 will still be the majority element in the suffix formed by throwing away the first prefix.
     *
     * [7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 5, 5, 5, 5]
     *
     * Now, the majority element is 5 (we changed the last run of the array from 7s to 5s),
     * but our first candidate is still 7. In this case, our candidate is not the true majority element,
     * but we still cannot discard more majority elements than minority elements
     * (this would imply that count could reach -1 before we reassign candidate, which is obviously false).
     *
     * Therefore, given that it is impossible (in both cases) to discard more majority elements than minority elements,
     * we are safe in discarding the prefix and attempting to recursively solve the majority element problem for
     * the suffix. Eventually, a suffix will be found for which count does not hit 0, and the majority element of
     * that suffix will necessarily be the same as the majority element of the overall array.
     */
    public static int majorityElementBoyerMooreVoting(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args)
    {
        int[] test =
        { 3,2,3 };
    }
}
