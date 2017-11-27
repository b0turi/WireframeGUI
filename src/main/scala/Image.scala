import java.awt.Graphics
class Image (width:Int, height:Int) extends ScalableObject {
  _width = width
  _height = height

  override def draw(g: Graphics): Unit = {
    g.drawRect(xPosition, yPosition, width, height)
    g.drawLine(xPosition, yPosition, xPosition + width, yPosition + height)
    g.drawLine(xPosition, yPosition + height, xPosition + width, yPosition)

    super.draw(g)
  }
}
