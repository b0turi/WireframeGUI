import java.awt.{Graphics, Point}
class WireframeGroup extends WireframeObject {
  override def draw(g: Graphics): Unit = {}

  override def scale(xDiff: Int, yDiff: Int): Unit = {}

  override def establishSelectionPoint(mouse: Point): Unit = {}
}
