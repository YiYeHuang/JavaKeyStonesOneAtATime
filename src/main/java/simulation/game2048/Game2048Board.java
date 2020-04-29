package simulation.game2048;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game2048Board {

  private int[][] gameBoard = new int[4][4];

  // location map to block
  private Set<String> location = new HashSet<>();

  private int score;
  private int availableSlot;

  public Game2048Board() {
    initBoard();
    score = 0;
    availableSlot = 16;
    initGame();
    printBoard();
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
    String l1 = getRandomBlock();
    String l2 = getRandomBlock();

    String[] m = l1.split(",");
    String[] n = l2.split(",");

    gameBoard[Integer.parseInt(m[0])][Integer.parseInt(m[1])] = 2;
    gameBoard[Integer.parseInt(n[0])][Integer.parseInt(n[1])] = 2;
  }

  public void randNext() {
    String l1 = getRandomBlock();
    String[] m = l1.split(",");
    gameBoard[Integer.parseInt(m[0])][Integer.parseInt(m[1])] = 2;
  }

  public void printBoard() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
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

  public void moveDown() {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 4; j++) {
        int next = gameBoard[i + 1][j];
        if (gameBoard[i][j] != 0) {
          if (next == 0) {
            // move down empty
            gameBoard[i + 1][j] = gameBoard[i][j];
            gameBoard[i][j] = 0;

            String indexKey = i + "," + j;
            String remove = (i + 1) + "," + j;
            location.remove(remove);
            location.add(indexKey);
          } else {
            // merge
            if ( next ==  gameBoard[i][j] &&  next < 2048) {
              gameBoard[i + 1][j] += gameBoard[i][j];
              gameBoard[i][j] = 0;
              availableSlot++;

              String indexKey = i + "," + j;
              location.add(indexKey);
            }
          }
        }
      }
    }
    updateScore();
  }

  public void moveUp() {
    for (int i = 3; i >= 1; i--) {
      for (int j = 0; j < 4; j++) {
        int next = gameBoard[i - 1][j];
        if (gameBoard[i][j] != 0) {
          if (next == 0) {
            // move down empty
            gameBoard[i - 1][j] = gameBoard[i][j];
            gameBoard[i][j] = 0;

            String indexKey = i + "," + j;
            String remove = (i - 1) + "," + j;
            location.remove(remove);
            location.add(indexKey);
          } else {
            // merge
            if ( next ==  gameBoard[i][j] &&  next < 2048) {
              gameBoard[i - 1][j] += gameBoard[i][j];
              gameBoard[i][j] = 0;
              availableSlot++;

              String indexKey = i + "," + j;
              location.add(indexKey);
            }
          }
        }
      }
    }
    updateScore();
  }

  public void moveRight() {
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        int next = gameBoard[i][j + 1];
        if (gameBoard[i][j] != 0) {
          if (next == 0) {
            // move down empty
            gameBoard[i][j + 1] = gameBoard[i][j];
            gameBoard[i][j] = 0;

            String indexKey = i + "," + j;
            String remove =  i + "," + (j + 1);
            location.remove(remove);
            location.add(indexKey);
          } else {
            // merge
            if ( next ==  gameBoard[i][j] &&  next < 2048) {
              gameBoard[i][j + 1] += gameBoard[i][j];
              gameBoard[i][j] = 0;
              availableSlot++;

              String indexKey = i + "," + j;
              location.add(indexKey);
            }
          }
        }
      }
    }
    updateScore();
  }

  public void moveLeft() {
    for (int i = 0; i < 4; i++) {
      for (int j = 3; j >= 1; j--) {
        int next = gameBoard[i][j - 1];
        if (gameBoard[i][j] != 0) {
          if (next == 0) {
            // move down empty
            gameBoard[i][j - 1] = gameBoard[i][j];
            gameBoard[i][j] = 0;

            String indexKey = i + "," + j;
            String remove =  i + "," + (j - 1);
            location.remove(remove);
            location.add(indexKey);
          } else {
            // merge
            if ( next ==  gameBoard[i][j] &&  next < 2048) {
              gameBoard[i][j - 1] += gameBoard[i][j];
              gameBoard[i][j] = 0;
              availableSlot++;

              String indexKey = i + "," + j;
              location.add(indexKey);
            }
          }
        }
      }
    }
    updateScore();
  }

  private void updateScore() {
    score = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        if (gameBoard[i][j] > 2) {
          score += gameBoard[i][j];
        }
      }
    }
  }

  public boolean ifWin() {
    return availableSlot == 0;
  }

  private String getRandomBlock() {
    Object[] list = location.toArray();
    int randomIndex = (int) (Math.random() * list.length);

    String index = (String) list[randomIndex];
    location.remove(index);

    availableSlot--;
    return index;
  }

  public static void main(String[] args) {

    System.out.println("NEW GAME++++++++++++++++++++++++++++");
    Game2048Board board = new Game2048Board();
    Scanner sc = new Scanner(System.in);
    System.out.println("Input the moving direction (a/s/d/w):");
    while(sc.hasNextLine()) {
      String move = sc.nextLine();
      if (move.equals("s")) {
        System.out.println("moving down");
        board.moveDown();
        board.printBoard();
      }

      if (move.equals("w")) {
        System.out.println("moving up");
        board.moveUp();
        board.printBoard();
      }

      if (move.equals("d")) {
        System.out.println("moving right");
        board.moveRight();
        board.printBoard();
      }

      if (move.equals("a")) {
        System.out.println("moving left");
        board.moveLeft();
        board.printBoard();
      }

      System.out.println("next round");
      board.randNext();
      board.printBoard();
    }
  }
}
