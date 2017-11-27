import java.awt.Graphics
class Image (width:Int, height:Int) extends ScalableObject {
  _width = width
  _height = height

  override def draw(g: Graphics): Unit = {
    g.drawRect(xPosition, yPosition, _width, _height)
    g.drawLine(xPosition, yPosition, xPosition + _width, yPosition + _height)
    g.drawLine(xPosition, yPosition + _height, xPosition + _width, yPosition)

    super.draw(g)
  }
}
