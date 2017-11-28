import java.awt.{Color, Graphics, Point}

abstract class WireframeObject {
  protected var xPosition: Int = Window.WIDTH/2
  protected var yPosition: Int = Window.HEIGHT/2

  protected var _width = 0
  protected var _height = 0

  protected var triangleWidth = 0
  protected var triangleHeight = 0

  private var locked = false
  protected var selected = false

  private var annotations:Set[Annotation] = Set()

  protected val controlRadius = 5

  var mouseTarget = 0

  def move(xDiff:Int, yDiff:Int):Unit = {
      xPosition += xDiff
      yPosition += yDiff
  }

  def draw(g:Graphics):Unit = {
    g.setColor(Color.BLACK)
    if(selected) {
      g.drawOval(xPosition + _width/2 - controlRadius, yPosition + _height/2 - controlRadius, controlRadius * 2, controlRadius * 2)
    }
  }

  def mouseInArea(mouse:Point):Boolean = pointInRange(xPosition, xPosition + _width, yPosition, yPosition + _height, mouse)
  def isSelected:Boolean = selected

  def select():Unit = {
    if(!locked) {
      selected = true
    }
  }

  def deselect():Unit = {
    selected = false
  }


  def interact(xDiff:Int, yDiff:Int):Unit
  def calculateMouseTarget(mouse:Point):Unit = { mouseTarget = 0 }

  def pointInRange(x1:Int, x2:Int, y1:Int, y2:Int, pt:Point):Boolean = (x1 to x2 contains pt.x) && (y1 to y2 contains pt.y)

  def drawTriangle(g:Graphics, padding:Int, facingUp:Boolean, relativePosition: Point):Unit = {
    if (facingUp) {
      g.fillPolygon(Array[Int](xPosition + relativePosition.x,
        xPosition + relativePosition.x + triangleWidth,
        xPosition + relativePosition.x + triangleWidth / 2,
        xPosition + relativePosition.x),
        Array[Int](yPosition + relativePosition.y + triangleHeight,
          yPosition + relativePosition.y + triangleHeight,
          yPosition + relativePosition.y,
          yPosition + relativePosition.y + triangleHeight), 3)
    } else {
      g.fillPolygon(Array[Int](xPosition + relativePosition.x,
        xPosition + relativePosition.x + triangleWidth,
        xPosition + relativePosition.x + triangleWidth / 2,
        xPosition + relativePosition.x),
        Array[Int](yPosition + relativePosition.y,
          yPosition + relativePosition.y,
          yPosition + relativePosition.y + triangleHeight,
          yPosition + relativePosition.y), 3)
    }
  }

  def lock():Unit = { locked = true }
  def unlock():Unit = {
    locked = false
    selected = true
  }

  def center:Point = new Point(xPosition + _width/2, yPosition + _height/2)
}
