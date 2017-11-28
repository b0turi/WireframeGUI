import java.awt.{Graphics, Point, Polygon}
class ComboBox(choices:List[String]) extends WireframeObject {

  final val COMBO_BOX_WIDTH = 100
  final val COMBO_BOX_HEIGHT = 24

  _width = COMBO_BOX_WIDTH
  _height = COMBO_BOX_HEIGHT


  var open = false
  var openHeight = choices.size * _height

  var current = 0

  val padding = 5
  val charSize = 10

  val triangleWidth = 10
  val triangleHeight = 14

  override def select():Unit = {
    super.select()
    open = true
  }

  override def deselect():Unit = {
    super.deselect()
    open = false
    current = mouseTarget
  }

  override def draw(g: Graphics): Unit = {

    val selectionLength = choices(current).length * charSize

    g.fillPolygon(Array[Int](xPosition + _width - padding - triangleWidth,
                            xPosition + _width - padding,
                            xPosition + _width - padding - triangleWidth / 2,
                            xPosition + _width - padding - triangleWidth),
                  Array[Int](yPosition + padding,
                            yPosition + padding,
                            yPosition + padding + triangleHeight,
                            yPosition + padding), 3)

    g.drawRoundRect(xPosition, yPosition, _width, _height, _height/4, _height/4)
    g.fillRect(xPosition + padding, yPosition + padding, selectionLength, charSize)
    if(open) {
      g.drawRoundRect(xPosition, yPosition + _height, _width, openHeight, _height/4, _height/4)
      for(a <- choices.indices) {
        g.fillRect(xPosition + padding, yPosition + (a+1) * _height + padding, choices(a).length * charSize, charSize)
      }
    }
    if(selected) {
      g.drawOval(xPosition + _width/2 - controlRadius, yPosition + (_height + openHeight)/2 - controlRadius, controlRadius * 2, controlRadius * 2)
    }
  }


  override def interact(xDiff: Int, yDiff: Int): Unit = {}

  override def calculateMouseTarget(mouse: Point): Unit = {
    for(a <- choices.indices) {
      if(pointInRange(xPosition + padding,
        xPosition + padding + choices.length * charSize,
        yPosition + (a + 1) * _height + padding,
        yPosition + (a + 1) * _height + padding + charSize, mouse)) {
        mouseTarget = a
      }
    }
  }
}
