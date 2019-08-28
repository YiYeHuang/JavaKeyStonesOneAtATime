package dataStructure.gen;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.concurrent.Callable;

public class GenTask implements Callable<Long> {

  private final int[][] fullHistory;
  private final int id;
  private final int size;

  public GenTask(int[][] fullHistory, int id, int size) {
    this.fullHistory = fullHistory;
    this.id = id;
    this.size = size;
  }

  @Override
  public Long call() {

    Instant start = Instant.now();
    Long result = 0L;
    for (int i = 0; i < size; i++) {
      result += genTimes(fullHistory[i], 0);
    }

    Duration duration = Duration.between(start, Instant.now());
    System.out.print("*** Thread " + id + " takes " + result + " draw to finish in "
        + duration.toMinutes() + " minutes --- Luck draw: ");
    int[] lucky = rand();
    for (int i = 0; i < 7; i ++) {
			System.out.print(lucky[i] + " ");
		}
		System.out.println();
    return result;
  }

  private long genTimes(int[] winning, int limit) {
    boolean notWin = true;
    long counter = 0;
    while (notWin) {
      boolean same = isSame(winning, rand());

      if (same) {
        break;
      } else {
        counter++;
      }
    }

    return counter;
  }

  private int[] rand() {
    int[] myLottyNumber = new int[7];

    for (int i = 0; i < myLottyNumber.length; i ++) {
      myLottyNumber[i] = 1 + (int)(Math.random() * 49 );

      boolean randomNumberAgain = true;

      if ( i >= 1) {
        while(randomNumberAgain) {
          int counter = i;
          for (int j = i - 1; j >=0; j-- ) {
            if (myLottyNumber[i] == myLottyNumber[j]) {
              myLottyNumber[i] = 1 + (int)(Math.random() * ((49 - 1) + 1));
              break;
            } else {
              counter--;
            }
          }
          if (counter == 0) {
            randomNumberAgain = false;
          }
        }
      }
    }

    Arrays.sort(myLottyNumber);
//		for (int i = 0; i < myLottyNumber.length; i ++) {
//			System.out.print(myLottyNumber[i] + " ");
//		}
//		System.out.println();
    return myLottyNumber;
  }

  private boolean isSame(int[] win, int[] my) {
    for (int i = 0; i < win.length; i++) {
      if (win[i] != my[i]) {
        return false;
      }
    }
    return true;
  }
}
