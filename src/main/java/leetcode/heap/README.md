## Heap LeetCode Notes 全是骚题


- Heap keeps max/min items on the top, used for problems that needs dynamic sorting
- O(logn) or add, offer operation, for heapify proess
- O(n) or remove
- O(1) for poll and peek.


## Basic trick and template:

### Min Heap: priorityQueue is by default min heap
```java
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((x, y) -> x.getValue() - y.getValue());
```

#### 215. Kth Largest Element in an Array
min heap 

keeps min item on top, therefore, when heap contains k element, the top is the kth largest 
- go through the list push the item into queue
- if queue size is larger than k, pop the top
- return the last top
* Space O(k)
* Time O((n - k) * logk)


#### 253. Meeting Rooms II: return the number of meeting room
min heap + sort
- sort the meeting interval by the start time, so the meeting can be check with time order
- only push the ending time into the queue, 
- go through the interval 
	- since the smallest ending time is on the top, if start time is less than end means another meeting rooms need 
	to be taken
	- if the start time is larger than the end time, poll the meeting room from the queue. act as "reuse"

* Space: O(n)
* Time: O(nlog(n))

#### 264. Ugly Number II: output the nth ugly number (factors only 2 3 5)
min heap

key of this question is to find min(min(factor(2), factor(3)), factor(5)), use heap to auto sort
- create the min heap and push 1, use long to avoid overflow
- go though the list and poll the result and next value
	- there is a trick here, since queue is not skipping repeat value, skip and poll
	 ```java
	 while(!minHeap.isEmpty() && minHeap.peek()==val) val = minHeap.poll();
	```
- keep push the (next value) * 2, (next value) * 3, (next value) * 5 to queue
- return poll

#### 313. Super Ugly Number: output the nth ugly number (factors given prime list) (hard)
min heap

very like ugle number II, but more factor needs to be tracked,
- build a dp like result array
- build a object with [factor result, #, initial prime] # as index in result array
- build a state
- build min and push 1 as initial value
- go through the result array and keep qush new result * prime into min heap
```java
	while (pq.peek().val == ugly[i]) {
				Num next = pq.poll();
				pq.add(new Num(next.prime * ugly[next.idx], next.idx + 1, next.prime));
}
``` 
- return array[n - 1];

### Max Heap
```java
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
```

#### 239. Sliding Window Maximum (hard)
Max heap to keep the top at top
- create the result list n - k + 1
- go through the list from beginning
	- push each item into max heap
	- if within window size, do nothing
	- (i - k >= 0) remove the out of the window item
	- (i - k + 1 >= 0) peek the max


### Max Heap + Min Heap to dynamically keep the median of a stream

```java
public class MedianfromDataStream {
		// length always n/2
		private PriorityQueue<Integer> minHeap;
		// length always n/2 or n/2 + 1
		private PriorityQueue<Integer> maxHeap;
		private boolean even = true;

		/** initialize your data structure here. */
		public MedianfromDataStream() {
			minHeap = new PriorityQueue<Integer>((a, b)-> a - b);
			maxHeap = new PriorityQueue<Integer>((a, b)-> b - a);
		}
	
		// EVEN: new number sort by max heap, then min heap get max heap pop value, min heap become odd
		// ODD: min heap sort the new number, then max heap get min heap pop value, both even
		public void addNum(int num) {
			if (even) {
				maxHeap.offer(num);
				minHeap.offer(maxHeap.poll());
			} else {
				minHeap.offer(num);
				maxHeap.offer(minHeap.poll());
			}
			even = !even;
		}
	
		public double findMedian() {
			if (even)
				return (minHeap.peek() + maxHeap.peek()) / 2.0;
			else
				return minHeap.peek();
		}
}
```

#### 480. Sliding Window Median
min + max heap + sliding window

Key: remove is the hard part
- create max heap and min heap, create result list n - k + 1
- go through the list from beginning
	- within k range do nothing
	- removing out size k range value
	- calculate median

```java
	// EVEN: min heap get value
	// ODD: max heap get value
	public void addNum(int num) {
		if (minHeap.isEmpty()) {
			minHeap.offer(num);
			return;
		} else if (minHeap.peek() <= num) {
			minHeap.offer(num);
		} else {
			maxHeap.offer(num);
		}
		rebalance();
	}

	public void remove(int n) {
		// min heap, min is at top, n is in min heap
		if (minHeap.peek() <= n) {
			// means max part needs to cut of one
			minHeap.remove(n);
		} else {
			maxHeap.remove(n);
		}
		rebalance();
	}

	// if min heap > max heap and size not same, max get min heap poll
	// otherwize, min get max poll
	public void rebalance() {
		if (minHeap.size() > maxHeap.size() + 1) {
			maxHeap.offer(minHeap.poll());
		} else if (maxHeap.size() > minHeap.size()) {
			minHeap.offer(maxHeap.poll());
		}
	}
```

### Combined Questions

#### 23. Merge k Sorted Lists
min heap + linked list dummy head + walker

use min heap to sort dynamic sort the next node
- push the current list head into the min heap, heap is sort with value, smallest value is at top
- start polling from heap to empty
    - walker pointing to next poll
    - repush poll node to heap if it has next 
    - keep the process

#### 347. Top K Frequent Elements
HashMap + Max Heap
- push all the item into hashmap to group up
- push map entry<key, count> to MaxHeap, sort by count, so top frequent is at top
- poll one by one entry from max heap to result list until hit 10 

#### 642. Design Search Autocomplete System (hard)
Trie + Kth most frequent (max heap)
Based one trie tree, As the input is dynamic, bucket sort cannot be used

- in the case when a new input is tried multiple times, the bucket will experience out of bounce
  Special character might also used, an array of ascII table each node is very expensive
- need to use dynamic data structure

Special TrieNode is used here
```java
	class AutoCompTrieNode {
		Map<Character, AutoCompTrieNode> children;
		Map<String, Integer> counts;
		boolean isWord;
		public AutoCompTrieNode() {
			children = new HashMap<Character, AutoCompTrieNode>();
			counts = new HashMap<String, Integer>();
			isWord = false;
		}
	}
```

- Add (trie tree node will contain map instead of array, and a count nmap)
	- build the trie tree, while build the trie tree, build the count map
	- adding word count map at each layer of the node, for later sorting
	- mark at the end of the node with isWord
- Search: trick is to keep a global prefix for continuous search
 	- *** for each input, if hit #, add the current prefix into trie, clean the prefix and return empty 
 	- *** non #, append the search to the prefix
    - *** Based on each search (with each char from prefix) in trie, build find the root in current trie tree
    for all current layer root build pair
```java
	private class Pair {
		String word;
		int count;
		public Pair(String w, int c) {
			this.word = w;
			this.count = c;
		}
	}
```
   - *** push all the key value pair built pair from root to the max heap, sort by count
   - *** build the result of top 3


### The keep distinct group as long as possible problem:
Max Heap work with map entry: sort the entry, if count same sort by alphabetically, otherwise sort with count 
```java
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? 
                b.getValue() - a.getValue() : a.getKey() - b.getKey()
        );
```

#### 358. Rearrange String k Distance Apart: keep letter apart
HashMap + max Heap + Queue + greedy
- grouping the letter count into hash map <letter, count>
- pushing all entries into min heap
- starting to poll from heap
    - final result append each poll, the entry reduce count by 1
    - push the polled entry to a wait queue
        - wait queue is here to store the possible next iteration order
        - for the break case, is that one specific case frequency is down to 0, can't form k apart and will not be back to heap 
        - so the remaining item exist in the wait queue, and heap will be empty.
    - wait queue size < k, continue to form k apart
    - already k apart, checking wait queue
        - queue poll count if > 0 push to min heap again
        - otherwise the item will stuck in wait queue, and when heap is empty, the result will be shorter than input

#### 621. Task Scheduler: schedule CPU task with idle, A -> B -> idle -> A -> B -> idle -> A -> B.
HashMap + max Heap + Queue + greedy
- grouping the letter(task) count into hash map <task, count>
- pushing all entries into min heap
- starting to poll from heap
    - k apart = n + 1
    - inner while loop 
        - polling from min heap, reduce count by 1 and push to queue
        - reduce k apart increment result count
        - until heap is empty
    - check queue
        - if entry count is still > 0, added back to min heap
    - if min heap is empty at this point, stop checking
    - increment the count by remaining k (k is the number of cpu idle).
- return result count

#### Kth problem solution: use 973. K Closest Points to Origin as example

I. The very naive and simple solution is sorting the all points by their distance to the origin point directly, 
then get the top k closest points. We can use the sort function and the code is very short.

Theoretically, the time complexity is O(NlogN), pratically, the real time it takes on leetcode is 104ms.

The advantages of this solution are short, intuitive and easy to implement.
The disadvatages of this solution are not very efficient and have to know all of the points previously, 
nd it is unable to deal with real-time(online) case, it is an off-line solution.

The short code shows as follows:
```java
public int[][] kClosest(int[][] points, int K) {
    Arrays.sort(points, (p1, p2) -> p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1]);
    return Arrays.copyOfRange(points, 0, K);
}
```


II. The second solution is based on the first one. We don't have to sort all points.
Instead, we can maintain a max-heap with size K. Then for each point, we add it to the heap. Once the size of the heap is greater than K, 
we are supposed to extract one from the max heap to ensure the size of the heap is always K. Thus, 
the max heap is always maintain top K smallest elements from the first one to crruent one. Once the size of the heap is over its maximum capacity, 
it will exclude the maximum element in it, since it can not be the proper candidate anymore.

Theoretically, the time complexity is O(NlogK), but pratically, the real time it takes on leetcode is 134ms.

The advantage of this solution is it can deal with real-time(online) stream data. It does not have to know the size of the data previously.
The disadvatage of this solution is it is not the most efficient solution.

The short code shows as follows:

```java
public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
    for (int[] p : points) {
        pq.offer(p);
        if (pq.size() > K) {
            pq.poll();
        }
    }
    int[][] res = new int[K][2];
    while (K > 0) {
        res[--K] = pq.poll();
    }
    return res;
}
```

III. The last solution is based on quick sort, we can also call it quick select. In the quick sort, 
we will always choose a pivot to compare with other elements. After one iteration, 
we will get an array that all elements smaller than the pivot are on the left side of the pivot and all elements greater than the pivot are on the right side of the pviot (assuming we sort the array in ascending order).
So, inspired from this, each iteration, we choose a pivot and then find the position p the pivot should be. 
Then we compare p with the K, if the p is smaller than the K, meaning the all element on the left of the pivot are all proper candidates but it is not adequate, we have to do the same thing on right side, and vice versa. 
If the p is exactly equal to the K, meaning that we've found the K-th position. Therefore, we just return the first K elements, since they are not greater than the pivot.

Theoretically, the average time complexity is O(N) , but just like quick sort, in the worst case, this solution would be degenerated to O(N^2), and pratically, the real time it takes on leetcode is 15ms.

The advantage of this solution is it is very efficient.
The disadvatage of this solution are it is neither an online solution nor a stable one. And the K elements closest are not sorted in ascending order.

The short code shows as follows:

```java
public int[][] kClosest(int[][] points, int K) {
    int len =  points.length, l = 0, r = len - 1;
    while (l <= r) {
        int mid = helper(points, l, r);
        if (mid == K) break;
        if (mid < K) {
            l = mid + 1;
        } else {
            r = mid - 1;
        }
    }
    return Arrays.copyOfRange(points, 0, K);
}

private int helper(int[][] A, int l, int r) {
    int[] pivot = A[l];
    while (l < r) {
        while (l < r && compare(A[r], pivot) >= 0) r--;
        A[l] = A[r];
        while (l < r && compare(A[l], pivot) <= 0) l++;
        A[r] = A[l];
    }
    A[l] = pivot;
    return l;
}

private int compare(int[] p1, int[] p2) {
    return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1];
}
```