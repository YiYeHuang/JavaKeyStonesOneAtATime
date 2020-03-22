package leetcode.math;

import leetcode.tag.level.Easy;
import leetcode.tag.type.Mathematics;

/*
1390. Four Divisors

Given an integer array nums, return the sum of divisors of the integers in that array that have exactly four divisors.

If there is no such integer in the array, return 0.

Example 1:

Input: nums = [21,4,7]
Output: 32
Explanation:
21 has 4 divisors: 1, 3, 7, 21
4 has 3 divisors: 1, 2, 4
7 has 2 divisors: 1, 7
The answer is the sum of divisors of 21 only.


Constraints:

1 <= nums.length <= 10^4
1 <= nums[i] <= 10^5
 */

@Mathematics
@Easy
public class FourDivision {
    int res = 0;
    public int sumFourDivisors(int[] nums) {
        for(int n : nums)
            helper(n);

        return res;
    }

    public void helper(int n){
        int cnt = 0, sum = 0;
        for(int i = 1; i * i <= n && cnt < 5; i++){
            if(n % i == 0){
                cnt += 2;
                sum += i;
                if(n / i == i){
                    cnt--;
                }else{
                    sum += (n / i);
                }
            }
        }
        if(cnt == 4){
            res += sum;
        }
    }
}
