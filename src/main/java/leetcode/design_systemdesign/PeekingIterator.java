package leetcode.design_systemdesign;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import leetcode.tag.level.Medium;
import leetcode.tag.type.Design;

/*
284. Peeking Iterator

Given an Iterator class interface with methods: next() and hasNext(),
design and implement a PeekingIterator that support the peek() operation --
it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element.
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */

@Design
@Medium
public class PeekingIterator implements Iterator<Integer> {

  private Integer next = null;
  private Iterator<Integer> iter;

  public PeekingIterator(Iterator<Integer> iterator) {
    // initialize any member here.
    iter = iterator;
    if (iter.hasNext())
      next = iter.next();
  }

  // Returns the next element in the iteration without advancing the iterator.
  public Integer peek() {
    return next;
  }

  // hasNext() and next() should behave the same as in the Iterator interface.
  // Override them if needed.
  @Override
  public Integer next() {
    Integer res = next;
    // update next
    next = iter.hasNext() ? iter.next() : null;
    return res;
  }

  @Override
  public boolean hasNext() {
    return next != null;
  }

  public static void main(String[] args) {
    List<Integer> test = Arrays.asList(1, 2, 3);
    PeekingIterator pkitr = new PeekingIterator(test.iterator());
    System.out.println(pkitr.next());
    System.out.println(pkitr.peek());
  }
}
