package simulation.canvas;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Canvas {

  private Map<String, Rectangle> characterRectangleMap;
  private CanvasPosition[][] canvas;
  private int currentId;

  private final int CANVAS_HEIGHT;
  private final int CANVAS_WIDTH;


  public Canvas(int canvasHeight, int canvasWidth) {
    this.characterRectangleMap = new HashMap<>();
    this.canvas = new CanvasPosition[canvasHeight][canvasWidth];
    this.CANVAS_HEIGHT = canvasHeight;
    this.currentId = 0;
    // init canvas board
    this.CANVAS_WIDTH = canvasWidth;
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        canvas[i][j] = new CanvasPosition();
      }
    }
  }

  public void draw(int startX, int startY, int endX, int endY, Character color) {
    Rectangle newRectangle = resolveAndCreateRectangle(startX, startY, endX, endY, color);
    goThroughCanvas(newRectangle, persist);
    characterRectangleMap.put(newRectangle.getKey(), newRectangle);
    currentId++;
  }

  public void drag(int startX, int startY, int endX, int endY) {
    selectPoint(startX,startY).ifPresent(
        rectangle -> {
          int deltaX = endX - startX;
          int deltaY = endY - startY;

          goThroughCanvas(rectangle, delete);
          rectangle.update(deltaX, deltaY);
          goThroughCanvas(rectangle, persist);
        }
    );
  }

  public void moveToTop(int touchPointX, int touchPointY) {
    selectPoint(touchPointX,touchPointY).ifPresent(
        rectangle -> goThroughCanvas(rectangle, moveToTop)
    );
  }

  public void delete(int touchPointX, int touchPointY) {
    selectPoint(touchPointX,touchPointY).ifPresent(rectangle -> {
      goThroughCanvas(rectangle, delete);
      characterRectangleMap.remove(rectangle.getKey());
    });
  }

  public void print() {
    System.out.println("=========================== current board ===========================");

    for (int i = 0; i < canvas.length; i++) {
      if (i % 2 == 0) {
        System.out.print(" ");
      } else {
        System.out.print("-");
      }
    }
    System.out.println();
    for (int i = 0; i < canvas.length; i++) {
      if (i % 2 == 0) {
        System.out.print("|");
      } else {
        System.out.print(" ");
      }
      for (int j = 0; j < canvas[i].length; j++) {
        LinkedHashSet<String> colorLayer = canvas[i][j].getColorLayer();
        if (colorLayer.isEmpty() || colorLayer.size() > 1) {
          printOverlap();
        } else {
          printColor(colorLayer);
        }
      }
      if (i % 2 == 0) {
        System.out.print("|");
      } else {
        System.out.print(" ");
      }
      System.out.println();
    }
    for (int i = 0; i < canvas.length; i++) {
      if (i % 2 == 0) {
        System.out.print(" ");
      } else {
        System.out.print("-");
      }
    }
    System.out.println();
  }

  /**
   * Private helper ===========
   */

  private BiConsumer<int[], Rectangle> delete = (index, rectangle) ->
      canvas[index[0]][index[1]].getColorLayer().remove(rectangle.getKey());

  private BiConsumer<int[], Rectangle> moveToTop = (index, rectangle) -> {
    LinkedHashSet<String> currentSpotColorList = canvas[index[0]][index[1]].getColorLayer();
    currentSpotColorList.remove(rectangle.getKey());
    currentSpotColorList.add(rectangle.getKey());
  };

  private BiConsumer<int[], Rectangle> persist = (index, rectangle) -> {
    canvas[index[0]][index[1]].getColorLayer().add(rectangle.getKey());
  };

  private void goThroughCanvas(Rectangle deleteRect, BiConsumer<int[], Rectangle> action) {

    int startCol = adjustPointX(deleteRect.getTopLeft().getX());
    int endCol = adjustPointX(deleteRect.getBottomRight().getX());
    int startRow = adjustPointY(deleteRect.getTopLeft().getY());
    int endRow = adjustPointY(deleteRect.getBottomRight().getY());

    for (int row = startRow; row <= endRow && row < CANVAS_HEIGHT; row++) {
      for (int col = startCol; col <= endCol && col < CANVAS_WIDTH; col++) {
        action.accept(new int[]{row, col}, deleteRect);
      }
    }
  }

  private Optional<Rectangle> selectPoint(int touchPointX, int touchPointY) {
    LinkedHashSet<String> currentSpotColorList = canvas[touchPointX][touchPointY].getColorLayer();
    if (!currentSpotColorList.isEmpty()) {

      String key = (String) currentSpotColorList.toArray()[currentSpotColorList.size() - 1];
      System.out.println("Selected " + key);
      return Optional.of(characterRectangleMap.get(key));
    } else {
      return Optional.empty();
    }
  }

  private Rectangle resolveAndCreateRectangle(int startX, int startY, int endX, int endY, Character color) {
    // TODO support topLeft to bottomRight draw only
    Point topLeft = new Point(startX, startY);
    Point topRight = new Point(endX, startY);
    Point bottomLeft = new Point(startX, endY);
    Point bottomRight = new Point(endX, endY);
    return new Rectangle(color, currentId, topLeft, topRight, bottomLeft, bottomRight);
  }

  private int adjustPointX(int point) {
    int result = point;
    if (result < 0) {
      result = 0;
    }

    if (result >= CANVAS_WIDTH) {
      result = CANVAS_WIDTH;
    }

    return result;
  }

  private int adjustPointY(int point) {
    int result = point;
    if (result < 0) {
      result = 0;
    }

    if (result >= CANVAS_HEIGHT) {
      result = CANVAS_HEIGHT;
    }

    return result;
  }

  private void printColor(LinkedHashSet<String> colorLayer) {
    System.out.print(((String) colorLayer.toArray()[colorLayer.size() - 1]).charAt(0));
  }

  private void printOverlap() {
    System.out.print(" ");
  }

  public static void main(String[] args) {
    Canvas newCanvas = new Canvas(20, 20);
    newCanvas.draw(1, 1, 6, 5, 'r');
    newCanvas.print();
    // newCanvas.draw(4, 4, 8, 8, 'r');
    // newCanvas.draw(2, 2, 16, 4, 'b');
    // newCanvas.print();
    // newCanvas.moveToTop(1,1);
    // newCanvas.delete(3, 3);
    // newCanvas.delete(3, 3);
     newCanvas.drag(4, 4, 0, 4);
    // newCanvas.moveToTop(2, 2);
    newCanvas.print();

    newCanvas.drag(1, 1, 35, 1);
    newCanvas.print();

    newCanvas.draw(1, 1, 6, 5, 'g');
    newCanvas.draw(4, 4, 8, 8, 'r');
    newCanvas.print();
    newCanvas.moveToTop(2, 2);
    newCanvas.print();
    newCanvas.delete(4, 4);
    newCanvas.print();
  }

  private void doPresist(int startX, int startY, int endX, int endY, Rectangle newRectangle) {
    for (int row = startY; row <= endY; row++) {
      for (int col = startX; col <= endX; col++) {
        canvas[row][col].getColorLayer().add(newRectangle.getKey());
      }
    }

    characterRectangleMap.put(newRectangle.getKey(), newRectangle);
  }

  private boolean verifyMove(Rectangle targetRectangle, int deltaX, int deltaY) {
    // TODO debug more edge case
    return verifyPoints(targetRectangle.getTopLeft(), deltaX, deltaY) &&
        verifyPoints(targetRectangle.getTopRight(), deltaX, deltaY) &&
        verifyPoints(targetRectangle.getBottomLeft(), deltaX, deltaY) &&
        verifyPoints(targetRectangle.getBottomRight(), deltaX, deltaY);
  }

  private boolean verifyPoints(Point targetPoint, int deltaX, int deltaY) {
    int newX = targetPoint.getX() + deltaX;
    int newY = targetPoint.getY() + deltaY;

    return newX >= 0 && newX <= CANVAS_WIDTH && newY >= 0 && newY <= CANVAS_HEIGHT;
  }






  public int[][] computeAreaJoin(int A, int B, int C, int D, int E, int F, int G, int H) {
    int hTop = Math.min(D, H);
    int hBot = Math.max(B, F);
    int wTop = Math.min(C, G);
    int wBot = Math.max(A, E);

    /*
     * new point will be (wTop, hBot) (wBot, hTop);
     */
    if (hTop < hBot || wTop < wBot) {
      return new int[0][0];
    } else {
      return new int[][]{{wTop, hBot}, {wBot, hTop}};
    }
  }

}
