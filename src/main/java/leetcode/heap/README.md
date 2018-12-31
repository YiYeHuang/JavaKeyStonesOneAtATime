## Heap LeetCode Notes

Max Heap
```java
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
```

Min Heap: although priorityQueue is by default min heap
```java
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((x, y) -> x.getValue() - y.getValue());
```

Max Heap work with map and keep alphabetical order
```java
        PriorityQueue<Map.Entry<Character, Integer>> heap = new PriorityQueue<>(
                (a,b) -> a.getValue() != b.getValue() ? 
                b.getValue() - a.getValue() : a.getKey() - b.getKey()
        );
```