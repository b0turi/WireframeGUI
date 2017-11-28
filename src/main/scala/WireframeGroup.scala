import java.awt.{Graphics, Point}
class WireframeGroup extends WireframeObject {
  override def draw(g: Graphics): Unit = {}

  override def interact(xDiff: Int, yDiff: Int): Unit = {}

  override def calculateMouseTarget(mouse: Point): Unit = {}
}
