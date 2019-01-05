## Linked List Notes

## Basic trick and template:

### Java delete a node
    - node = node.next.next

### Dummy Head
    - create dummy node with value 0 as a new list

#### 2 Add Two Numbers
Dummy head

#### 21 Merge Two Sorted List
- Merge L1 and L2 at the same time
- Check L1 remaining 
- Check L2 remaining

    
### Faster slower walker
    - faster node walks twice as fast as slow node, used to split the linked list or detected cycle

#### 876 Middle of A LinkedList
- Faster & Slower Walker
- when node number is odd, will return the middle
- When even number, slow will always return the second one, for first one cache a node before

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

### reverse linked list
    - create previous walker as null
    - Temp walker 'next' jump to next node, to store the next node while break the current connection
    - point current head to previous node (when doing first step, point to null)
    - point previous node to current head to iterate to next
    - move current head to next node.

#### 206 Reverse a singly linked list.
basic reverse linked list question

### Combined Questions

#### 92 Reverse Linked List from m to n
Dummy head + reverse linked list




#### Sort Linked List
- Merge Sort & Merge Two Sorted List & Faster/Slower Walker
- Split use Faster/Slower Walker: beware when even number, slow will always return the second one, cache one node before
    - stop when head or head.next is null
    - split head
    - split slow (the new second half tail)
- Merge Two Sorted List

#### Reorder Linked List
- Faster/Slower Walker split linked list in half
- reverse linkedlist part two
- Merge Two LinkedList


    



