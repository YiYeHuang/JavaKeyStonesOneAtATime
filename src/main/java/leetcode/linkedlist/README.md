## Linked List Notes

## Basic trick and template:

### Java delete a node
    - node = node.next.next

#### 83. Remove Duplicates from Sorted List: duplicate only once
- cache the last check value
- if duplicate delete trick
- stop if walker next is null

#### 82. Remove Duplicates from Sorted List II: duplicate more than once
- cache the last check value and last checked node
- Outer while check ending walker next not null
    - inner while check if walker next is same
- if hit pre node cache points to walker next
- update last check update walker 

### Dummy Head + walker
    - create dummy node with value 0 as a new list
    - used to mark the head for return
    - use walker to iterate the list

#### 2 Add Two Numbers
- create dummy head and walker
- while loop check node1 or node2 not null
    - if one of the node is null treat the value as 0
    - add the number to new node, modify the carry over
- check again with the carry over
- return dummy.next

#### 21 Merge Two Sorted List
- while loop checking L1 and L2 both not null at the same time
    - dummy walker.next assign to the smaller one
- Check L1 remaining 
    - assign walker.next
- Check L2 remaining
    - assign walker.next
- return dummy.next

recursive solution
```java
    public ListNode mergeTwolistsRecur(ListNode l1, ListNode l2)
    {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val)
        {
            l1.next = mergeTwolists(l1.next, l2);
            return l1;
        }
        else
        {
            l2.next = mergeTwolists(l1, l2.next);
            return l2;
        }
    }
```

    
### Faster slower walker
    - faster node walks twice as fast as slow node, used to split the linked list or detected cycle

#### 19. Remove Nth Node From End of List
A bit changes question for faster and slower walker
- faster pointer walks N step first
- slow and faster point walks together
- delete nth node

#### 141 LinkedList cycle
- since there is a cycle, two walker will eventually meet

#### 142 LinkedList cycleII: return the entry point
- since there is a cycle, two walker will eventually meet
- Return meeting point
    - L1 as the distance head to the cycle entry point
    - L2 as the distance meeting point to the entry point
    - C as the cycle length
        - Slow moved L1 + L2 when meet
        - Fast moved L1 + L2 + n * C when meet
        - Since Fast speed = slow speed * 2
            - 2 * (L1 + L2) = L1 + L2 + n * C
            - -> L1 + L2 = n*C
            - -> L1 = (n - 1) C + (C - L2)
            - -> L1 = C - L2
- When hit the meeting point, head to entry is the meeting point to entry point


#### 369. Plus One Linked List
5 -> 6 -> 8 -> 9 -> 9 -> 9 -> 9

another way of thinking fast node and slow node
- in this question, only the non 9 last node is the key, slow node cache that fast node goes to end (walker next not null)
- if faster node is not 9, just ++
- if is, slow node ++, and update all the following with value 0

#### 876 Middle of A LinkedList
- Faster & Slower Walker
- when node number is odd, will return the middle
- When even number, slow will always return the second one, for first one cache a node before


### reverse linked list
    - create previous walker as null
    - Temp walker 'next' jump to next node, to store the next node while break the current connection
    - point current head to previous node (when doing first step, point to null)
    - point previous node to current head to iterate to next
    - move current head to next node.
```java
    public static ListNode reverseListInplace(ListNode head)
    {
        ListNode prev = null;
        while(head != null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
```

#### 206 Reverse a singly linked list.
basic reverse linked list question

### reverse linkedlist from a begin to an end
```java
    public static ListNode reverse(ListNode begin, ListNode end) {
        //where first will be doomed "last"
        ListNode prev = begin;
        ListNode head = begin.next;
        ListNode walker = head.next;

        while(walker != end){
            // break the reverse target
            head.next = walker.next;
            // link the break target with the reverse list head
            ListNode currentHead = prev.next;
            walker.next = currentHead;
            // linked the fix begin with the break target
            prev.next = walker;
            // move walker to next
            walker = head.next;
        }

        return head;
    }
```

#### 25. Reverse Nodes in k-Group (hard)
Divide and conquer question +  reverse linked list from a begin to end + dummy head

- if the distance between begin and end is k, do it
    - otherwise don't do it
- iterator the linked list and find interval K
    - if i can mod k, do reverse (make sure reverse return the tail of reversed list)
    - if not, head = head.next

#### 92 Reverse Linked List from m to n
Dummy head + reverse linked list from begin o an end trick

### Combined Questions

#### 61 Rotate List
- find the length of the linkedlist
- link tail and head
- k = k % len to get the rotate steps
- tail = tail next for len - k steps
- head is new tail next
- break the loop

#### 148 Sort Linked List
Merge Sort & Merge Two Sorted List & Faster/Slower Walker
- Split use Faster/Slower Walker: beware when even number, slow will always return the second one, cache one node before
    - stop when head or head.next is null
    - split head
    - split slow (the new second half tail)
    - break left tail
    - left = sortList(head)
    - right = sortList(slow walker)
- 21 Merge Two Sorted List

#### 143 Reorder Linked List
- Faster/Slower Walker split linked list in half
- Merge Two LinkedList
    - cache head 1 and head 2 next
    - head2 next points to head1 next
    - head1 next points to head2
    - head1 move on
    - head2 move on

#### 147. Insertion Sort List
dummy head + insert sort
- using two walker for locate insert location
- while loop to control the order
- nest while 
    - temp walker always go from dummy head to find value that is smaller than current walker
- if find swap and update location

#### 328. Odd Even Linked List
```java
    while (even != null && even.next != null) {
        odd.next = odd.next.next;
        even.next = odd.next.next;
        // move to next;
        odd = odd.next;
        even = even.next;
    }
```

#### 445. Add Two Numbers II
Dummy head + stack
- push all value from two linked list to two stack
- create dummy head
- pop if not null
- update sum and carry one
- create temp node to store the value
- point dummy next to temp node
- return dummy or dummy next depends on the carry one



