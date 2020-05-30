package leetcode.trie;

import leetcode.tag.level.Medium;
import leetcode.tag.type.Trie;
import leetcode.trie.basicObj.TrieBinary;
import leetcode.trie.basicObj.TrieNode;

/*
421. Maximum XOR of Two Numbers in an Array

Given a non-empty array of numbers, a0, a1, a2, � , an-1, where 0 ? ai < 2^31.
Find the maximum result of ai XOR aj, where 0 ? i, j < n.
Could you do this in O(n) runtime?

Example:
Input: [3, 10, 5, 25, 2, 8]

Output: 28
Explanation: The maximum result is 5 ^ 25 = 28.
 */

@Medium
@Trie
public class MaximumXORTwoNumbersArray {

    TrieBinary root;

    // 31 n
    public void insert(int num) {
        TrieBinary cur = root;
        /*
         * 1 << 30 is equal to 1,073,741,824
         * it's two's complement binary integer is 1000000000000000000000000000000.
         */
        int j = 1 << 30;
        for (int i = 0; i < 31; i++) {
            // determine whether number's highest digit is 1 or 0
            int bit = (j & num) == 0 ? 0 : 1;
            if (bit == 0 && cur.zero == null) {
                cur.zero = new TrieBinary();
            }
            if (bit == 1 && cur.one == null) {
                cur.one = new TrieBinary();
            }
            cur = bit == 0 ? cur.zero : cur.one;
            // j shift to right one position
            j >>= 1;
        }
        cur.isEnd = true;
        cur.val = num;
    }

    public int findMaximumXOR(int[] nums) {
        root = new TrieBinary();
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        for (int n : nums) {
            insert(n);
        }
        TrieBinary cur = root;
        // as number can be pretty small, get to the hightest root that has both 1 and 0 kid
        while (cur.one == null || cur.zero == null) {
            cur = cur.zero != null ? cur.zero : cur.one;
        }

        return traversal(cur.one, cur.zero);
    }

    private int traversal(TrieBinary one, TrieBinary zero) {
        if (one.isEnd && zero.isEnd) {
            return one.val ^ zero.val;
        }
        if (one.zero == null) {
            return traversal(one.one, zero.zero == null ? zero.one : zero.zero);
        } else if (one.one == null) {
            return traversal(one.zero, zero.one == null ? zero.zero : zero.one);
        } else if (zero.zero == null) {
            return traversal(one.zero, zero.one);
        } else if (zero.one == null) {
            return traversal(one.one, zero.zero);
        } else {
            return Math.max(traversal(one.one, zero.zero), traversal(one.zero, zero.one));
        }
    }
}
