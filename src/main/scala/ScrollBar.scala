import java.awt.{Graphics, Point}
class ScrollBar(height:Int, textObj:TextObject) extends ScalableObject{
  val SCROLLBAR_WIDTH = 24
  _width = SCROLLBAR_WIDTH
  _height = height

  triangleWidth = SCROLLBAR_WIDTH/2
  triangleHeight = SCROLLBAR_WIDTH/2

  xScalable = false

  var progress = 0
  val padding = _width/4

  def scrollUp():Unit = {
    textObj.offset += 1
    if(textObj.contentHeight - (textObj.offset * textObj.fontSize) < _height) {
      textObj.offset -= 1
    }
  }

  def scrollDown():Unit = {
    textObj.offset -= 1
    if(textObj.offset < 0) {
      textObj.offset = 0
    }
  }

  override def draw(g: Graphics): Unit = {
    g.drawRoundRect(xPosition, yPosition, _width, _height, padding, padding)
    drawTriangle(g, padding/2, true, new Point(padding, padding))
    drawTriangle(g, padding/2, false, new Point(_width/4, _height - padding - triangleHeight))

    super.draw(g)
  }

  override def calculateMouseTarget(mouse: Point): Unit = {
    super.calculateMouseTarget(mouse)
    if(pointInRange(xPosition, xPosition + _width, yPosition, yPosition + padding + triangleHeight, mouse)) {
      mouseTarget = 1
    }
    if(pointInRange(xPosition, xPosition + _width, yPosition + _height - padding - triangleHeight, yPosition + _height, mouse)) {
      mouseTarget = 2
    }
  }

  override def interact(xAmount: Int, yAmount: Int): Unit = {
    if(mouseTarget == 2) {
      scrollUp()
    }
    if(mouseTarget == 1) {
      scrollDown()
    }
  }
}
