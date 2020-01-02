package leetcode.hashtable;

import leetcode.tag.level.Medium;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Heap;

import java.util.*;

/**
 * 347. Top K Frequent Elements
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 * Note:
 *
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */

@Medium
@HashTableTag
@Heap
public class TopKFrequentElements {

    /**
     * just read from questions, a typical max heap model
     *
     * PriorityQueue is a min heap, to turn into max heap do
     *
     *  PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
     *                 new PriorityQueue<>(10, Collections.reverseOrder());
     *  or
     *  PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);
     *  or
     *  PriorityQueue<Integer> pq = new PriorityQueue<Integer>(defaultSize, new Comparator<Integer>() {
     *     public int compare(Integer lhs, Integer rhs) {
     *         if (lhs < rhs) return +1;
     *         if (lhs.equals(rhs)) return 0;
     *         return -1;
     *     }
     * });
     *
     *  - Map frequency
     *  - push to ordering data structure
     *  - get result
     *
     */
    public List<Integer> topKFrequentHEAP(int[] nums, int k) {
        // Mapping number to frequency
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();

        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }

        return res;
    }

    /**
     * insert the frequency to treemap, and poll from last entry
     */
    public List<Integer> topKFrequentTREEMAP(int[] nums, int k) {
        // Mapping number to frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
            // map frequency to all input number
            int freq = map.get(num);
            if(!freqMap.containsKey(freq)){
                freqMap.put(freq, new LinkedList<>());
            }
            freqMap.get(freq).add(num);
        }

        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

    /**
     * bucket sort
     */
    public static List<Integer> topKFrequentBUCKET(int[] nums, int k) {
        // Mapping number to frequency
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }

        // corner case: if there is only one number in nums, we need the bucket has index 1.
        List<Integer>[] bucket = new List[nums.length+1];

        for(int n:map.keySet()){
            int freq = map.get(n);
            if(bucket[freq]==null)
                bucket[freq] = new LinkedList<>();
            bucket[freq].add(n);
        }

        List<Integer> res = new ArrayList<>();
        // bucket is from 0 to
        for(int i=bucket.length-1; i>0 && k>0; --i){
            if(bucket[i]!=null){
                List<Integer> list = bucket[i];
                res.addAll(list);
                k-= list.size();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] test = {1,1,2,2,2,3,4,4,};
        topKFrequentBUCKET(test, 3);
    }

}
