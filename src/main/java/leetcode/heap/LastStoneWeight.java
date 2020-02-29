package leetcode.heap;

import leetcode.tag.level.Easy;
import leetcode.tag.level.Hard;
import leetcode.tag.type.Greedy;
import leetcode.tag.type.Heap;

import java.util.PriorityQueue;

/**
 * 1046. Last Stone Weight
 *
 * We have a collection of rocks, each rock has a positive integer weight.
 *
 * Each turn, we choose the two heaviest rocks and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 *
 *
 */

@Easy
@Heap
@Greedy
public class LastStoneWeight {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);

        for (int weight : stones) {
            maxHeap.offer(weight);
        }

        for (int i = 0; i < stones.length - 1; ++i) {
            maxHeap.offer(maxHeap.poll() - maxHeap.poll());
        }

        return maxHeap.poll();
    }
}
