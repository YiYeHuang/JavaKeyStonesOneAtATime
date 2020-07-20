package simulation.canvas;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Rectangle {

  private Character color;
  private int id;
  private Point topLeft, topRight, bottomLeft, bottomRight;

  public void update(int deltaX, int deltaY) {
    topLeft.update(deltaX, deltaY);
    topRight.update(deltaX, deltaY);
    bottomLeft.update(deltaX, deltaY);
    bottomRight.update(deltaX, deltaY);
  }

  public String getKey() {
    return color + "," + id;
  }
}
