package simulation;

import java.util.HashMap;
import java.util.Map;

public class Game2048Board {

  Block[][] gameBoard = new Block[4][4];
  boolean[][] visited = new boolean[4][4];

  // location map to block
  Map<String, Block> indexBlockMap = new HashMap<>();

  private int score;
  private int availableSlot;

  public Game2048Board() {
  }


  public Block getRandomBlock() {
    indexBlockMap.values().toArray();
  }


}
