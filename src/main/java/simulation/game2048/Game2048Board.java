package simulation.game2048;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Game2048Board {

  private int[][] gameBoard = new int[BOARD_SIZE][BOARD_SIZE];
  private static final int MAX_SCORE = 2048;
  private static final int BOARD_SIZE = 4;

  // location map to block
  private Set<String> location = new HashSet<>();

  private int score;
  private int availableSlot;
  private boolean ifWin;
  private boolean hasNextMove;

  public Game2048Board() {
    this.score = 0;
    this.availableSlot = BOARD_SIZE * BOARD_SIZE;
    this.initBoard();
    this.initGame();
    this.renderBoard();
    this.hasNextMove = true;
  }

  public boolean ifWin() {
    return ifWin;
  }

  public boolean continueGame() {
    return hasNextMove;
  }

  public void randNext(int value) {
    Optional<String> nextBlock = getRandomBlock();
    if (nextBlock.isPresent()) {
      String[] m = nextBlock.get().split(",");
      gameBoard[Integer.parseInt(m[0])][Integer.parseInt(m[1])] = value;
    } else {
      hasNextMove = false;
    }
  }

  public void moveUp() {
    int boundary = 1;

    for (int row = 1; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {

        // moving in the up direction
        for (int movingRow = row; movingRow >= boundary; movingRow--) {
          int nextSlotValue = gameBoard[movingRow - 1][col];
          if (gameBoard[movingRow][col] != 0) {
            if (nextSlotValue == 0) {
              // move up empty
              move(movingRow, col, movingRow - 1, col);
            } else {
              // merge
              if ( nextSlotValue == gameBoard[movingRow][col] && nextSlotValue < MAX_SCORE) {
                moveAndMerge(movingRow, col, movingRow - 1, col);
              }
            }
          }
        }
      }
    }
  }

  public void moveDown() {
    int startingLine = BOARD_SIZE - 2;
    int boundary = BOARD_SIZE - 1;

    for (int row = startingLine; row >= 0; row--) {
      for (int col = 0; col < BOARD_SIZE; col++) {

        // moving in the down direction
        for (int movingRow = row; movingRow < boundary; movingRow++) {
          int nextSlotValue = gameBoard[movingRow + 1][col];
          // if has value
          if (gameBoard[movingRow][col] != 0) {
            if (nextSlotValue == 0) {
              // move down no merge
              move(movingRow, col, movingRow + 1, col);
            } else {
              // move down and merge
              if ( nextSlotValue ==  gameBoard[movingRow][col] &&  nextSlotValue < MAX_SCORE) {
                if (moveAndMerge(movingRow, col, movingRow + 1, col) == MAX_SCORE) ifWin = true;
              }
            }
          }
        }
      }
    }
  }

  public void moveRight() {

    int startingLine = BOARD_SIZE - 2;
    int boundary = BOARD_SIZE - 1;

    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = startingLine; col >= 0; col--) {

        for (int movingCol = col; movingCol < boundary; movingCol++) {
          int nextSlotValue = gameBoard[row][movingCol + 1];
          if (gameBoard[row][movingCol] != 0) {
            // move right no merge
            move(row, movingCol,  row, movingCol + 1);
          } else {
            // move right and merge
            if ( nextSlotValue ==  gameBoard[row][movingCol] &&  nextSlotValue < MAX_SCORE) {
              if (moveAndMerge(row, movingCol, row, movingCol + 1) == MAX_SCORE) ifWin = true;
            }
          }
        }
      }
    }
  }

  public void moveLeft() {
    int startingLine = 1;
    int boundary = BOARD_SIZE - 1;

    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = startingLine; col < BOARD_SIZE; col++) {

        for (int movingCol = col; movingCol > 0; movingCol--) {
          int nextSlotValue = gameBoard[row][movingCol - 1];
          if (gameBoard[row][movingCol] != 0) {
            // move right no merge
            move(row, movingCol,  row, movingCol -1);
          } else {
            // move right and merge
            if ( nextSlotValue ==  gameBoard[row][movingCol] &&  nextSlotValue < MAX_SCORE) {
              if (moveAndMerge(row, movingCol, row, movingCol -1) == MAX_SCORE) ifWin = true;
            }
          }
        }
      }
    }
  }

  public void renderBoard() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (gameBoard[i][j] == 0) {
          System.out.print("      |");
        } else {
          if (gameBoard[i][j] < 10) System.out.print("   " + gameBoard[i][j] + "  |");
          else if (gameBoard[i][j] < 100) System.out.print("  " + gameBoard[i][j] + "  |");
          else if (gameBoard[i][j] < 1000) System.out.print(" " + gameBoard[i][j] + "  |");
          else if (gameBoard[i][j] < 2049) System.out.print(" " + gameBoard[i][j] + " |");
        }
      }
      System.out.println();
    }
    System.out.println("Current points: " + score + " available spot: " + availableSlot);
  }

  private void initBoard() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        String indexKey = i + "," + j;
        location.add(indexKey);
      }
    }
  }

  private void initGame() {
    Optional<String> l1 = getRandomBlock();
    Optional<String> l2 = getRandomBlock();

    String[] m = l1.get().split(",");
    String[] n = l2.get().split(",");

    gameBoard[Integer.parseInt(m[0])][Integer.parseInt(m[1])] = 2;
    gameBoard[Integer.parseInt(n[0])][Integer.parseInt(n[1])] = 2;
  }

  private void move(int fromX, int fromY, int toX, int toY) {
    gameBoard[toX][toY] = gameBoard[fromX][fromY];
    gameBoard[fromX][fromY] = 0;

    resolveRemainingSlot(fromX, fromY, toX, toY);
  }

  private int moveAndMerge(int fromX, int fromY, int toX, int toY) {
    gameBoard[toX][toY] += gameBoard[fromX][fromY];
    gameBoard[fromX][fromY] = 0;
    availableSlot++;
    score += gameBoard[toX][toY];

    resolveRemainingSlot(fromX, fromY, toX, toY);
    return gameBoard[toX][toY];
  }

  private void resolveRemainingSlot(int fromX, int fromY, int toX, int toY) {
    String indexKey = fromX + "," + fromY;
    String removeKey = toX + "," + toY;
    location.remove(removeKey);
    location.add(indexKey);
  }

  private Optional<String> getRandomBlock() {
    Object[] list = location.toArray();

    if (list.length > 0) {
      int randomIndex = (int) (Math.random() * list.length);

      String index = (String) list[randomIndex];
      location.remove(index);

      availableSlot--;
      return Optional.of(index);
    } else {
      return Optional.empty();
    }
  }

}
