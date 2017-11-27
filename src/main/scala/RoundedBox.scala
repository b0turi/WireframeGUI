import java.awt.Graphics

class RoundedBox (width:Int, height:Int, radius:Int) extends ScalableObject {
  _width = width
  _height = height

  val cornerRadius = radius
  override def draw(g: Graphics): Unit = {
    g.drawRoundRect(xPosition, yPosition, _width, _height, cornerRadius, cornerRadius)
    super.draw(g)
  }
}
