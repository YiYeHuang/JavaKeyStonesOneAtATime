package leetcode.twopointer;

import leetcode.tag.level.Medium;
import leetcode.tag.type.TwoPointer;

/*
251. Flatten 2D Vector

Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.

Example:

Vector2D iterator = new Vector2D([[1,2],[3],[4]]);

iterator.next(); // return 1
iterator.next(); // return 2
iterator.next(); // return 3
iterator.hasNext(); // return true
iterator.hasNext(); // return true
iterator.next(); // return 4
iterator.hasNext(); // return false


Notes:

Please remember to RESET your class variables declared in Vector2D,
as static/class variables are persisted across multiple test cases. Please see here for more details.
You may assume that next() call will always be valid, that is,
there will be at least a next element in the 2d vector when next() is called.

Follow up:

As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */

@Medium
@TwoPointer
public class Flattern2DVector {

  int row = 0;
  int col = 0;
  int[][] refV;

  public Flattern2DVector(int[][] v) {
    this.refV = v;
  }

  public int next() {
    skipEmptyRows();
    return refV[row][col++];
  }

  public boolean hasNext() {
    skipEmptyRows();

    return row < refV.length;
  }

  private void skipEmptyRows() {
    while(row < refV.length && col == refV[row].length){
      row++;
      col = 0;
    }
  }

}
