package simulation.game2048;

import java.util.Scanner;

public class GameFlow {

  public static void main(String[] args) {
    launchGame();
  }

  public static void launchGame() {
    System.out.println("NEW GAME++++++++++++++++++++++++++++");
    Game2048Board board = new Game2048Board();
    Scanner sc = new Scanner(System.in);
    System.out.println("Input the moving direction (a/s/d/w):");
    while(sc.hasNextLine()) {
      String move = sc.nextLine();
      if (move.equals("s")) {
        System.out.println("moving down");
        board.moveDown();
        board.renderBoard();
      }

      if (move.equals("w")) {
        System.out.println("moving up");
        board.moveUp();
        board.renderBoard();
      }

      if (move.equals("d")) {
        System.out.println("moving right");
        board.moveRight();
        board.renderBoard();
      }

      if (move.equals("a")) {
        System.out.println("moving left");
        board.moveLeft();
        board.renderBoard();
      }

      if (board.ifWin() || !board.continueGame()) {
        break;
      } else {
        System.out.println("next round");
        //board.randNext();
        board.renderBoard();
      }
    }

    if (board.ifWin()) System.out.println("you win");

    if (!board.continueGame()) System.out.println("you lose");
  }
}
