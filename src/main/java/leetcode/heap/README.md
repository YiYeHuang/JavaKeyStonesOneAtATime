## Heap LeetCode Notes 全是骚题

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

### Max Heap work with map and keep alphabetical order
```java
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? 
                b.getValue() - a.getValue() : a.getKey() - b.getKey()
        );
```
#### 239. Sliding Window Maximum (hard)
Max heap to keep the top at top
- create the result list n - k + 1
- go through the list from beginning
	- push each item into max heap
	- if within window size, do nothing
	- (i - k >= 0) remove the out of the window item
	- (i - k + 1 >= 0) peek the max






	


