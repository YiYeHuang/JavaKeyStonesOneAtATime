package simulation.canvas;

import java.util.LinkedHashSet;
import lombok.Getter;

public class CanvasPosition {

  /**
   * key = color,id
   */
  @Getter
  private LinkedHashSet<String> colorLayer;

  public CanvasPosition() {
    this.colorLayer = new LinkedHashSet<>();
  }
}
