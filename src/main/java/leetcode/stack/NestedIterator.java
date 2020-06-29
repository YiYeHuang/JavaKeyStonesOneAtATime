package leetcode.stack;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import leetcode.stack.basic.NestedInteger;
import leetcode.tag.level.Medium;
import leetcode.tag.type.StackTag;

/*
341. Flatten Nested List Iterator

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,4,6].
 */
@Medium
@StackTag
public class NestedIterator implements Iterator<Integer> {

  private Stack<NestedInteger> stack;

  // flatten all initial NestedInteger into stack
  public NestedIterator(List<NestedInteger> nestedList) {
    stack = new Stack<>();
    flattenList(nestedList);
  }

  @Override
  public Integer next() {
    return hasNext() ? stack.pop().getInteger() : null;
  }

  @Override
  public boolean hasNext() {
    while (!stack.isEmpty()) {
      if (stack.peek().isInteger()) return true;
      flattenList(stack.pop().getList());
    }
    return false;
  }

  private void flattenList(List<NestedInteger> list) {
    for (int i = list.size() - 1; i >= 0; i--) {
      stack.push(list.get(i));
    }
  }
}